package com.example.helloketty.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.helloketty.entity.ElavenUser;


/**
 * Created by HelloKetty on 2018/5/10.
 */

public class DataSqlite {

    /**
     * 编辑帖子页面的草稿标示
     */
    public final static int TEXT_TAG_POST = 1;
    /**
     * 发帖子界面的草稿标示
     */
    public final static int TEXT_TAG_EDIT = 2;

    private DatabaseHelper dbOpenHelper;

    public void DataSqlite(Context context) {
        dbOpenHelper = DatabaseHelper.Instance(context);
        while (dbOpenHelper.getWritableDatabase().isDbLockedByOtherThreads()) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 查找userInfo
     *
     * @return
     * @throws Exception
     */
    public synchronized ElavenUser findUserInfoByTag() {
        SQLiteDatabase database = null;
        Cursor cursor = null;
        try {
            database = dbOpenHelper.getReadableDatabase();
            ElavenUser user = new ElavenUser();
            cursor = database.rawQuery("select * from text_userinfo", new String[]{});

            if (cursor.moveToFirst()) {
                user.setUser_id(cursor.getString(cursor.getColumnIndex("uid")));
            }
            return user;
        } catch (Exception ex) {
            throw ex;
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            if (database != null) {
                database.close();
            }
        }
    }


    /**
     * 插入用户信息
     * @param user
     */
    public synchronized void insertUser(ElavenUser user) {
        SQLiteDatabase database = null;
        try {
            database = dbOpenHelper.getWritableDatabase();
            database.beginTransaction();
            String sql = "insert into text_userinfo(name,user_id,gender,email,phone_number,address) values(?,?,?,?,?,?)";
            database.execSQL(sql, new Object[]{user.getName(), user.getUser_id(), user.getGender(),user.getEmail(), user.getPhone_number(), user.getAddress()});
            database.setTransactionSuccessful();
            database.endTransaction();
        } catch (Exception ex) {
            throw ex;
        } finally {
            if (database != null) {
                database.close();
            }
        }
    }

    /**
     * 更新用户信息
     * @param user
     */
    public void updateUserInfoByTagFid(ElavenUser user) {
        SQLiteDatabase database = null;
        try {
            database = dbOpenHelper.getWritableDatabase();
            database.beginTransaction();
            database.execSQL("update text_userinfo set name=? ,user_id=?， gender=? ,email=?，phone_number=? ,address=? where user_id=?",
                    new Object[]{user.getName(), user.getUser_id(), user.getGender(),user.getEmail(), user.getPhone_number(), user.getAddress(), user.getUser_id()});
            database.setTransactionSuccessful();
            database.endTransaction();
        } catch (Exception ex) {
            throw ex;
        } finally {
            if (database != null) {
                database.close();
            }
        }

    }


    /**
     * 根据userid 删除
     *
     * @param userID
     * @return
     * @throws Exception
     */
    public synchronized void deleteUser(String userID) {
        SQLiteDatabase database = null;
        try {
            database = dbOpenHelper.getWritableDatabase();
            database.beginTransaction();
            database.execSQL("delete from text_draft where user_id=?", new Object[]{ userID });
            database.setTransactionSuccessful();
            database.endTransaction();
        } catch (Exception ex) {
            throw ex;
        } finally {
            if (database != null) {
                database.close();
            }
        }
    }


}
