package com.example.helloketty.util;

import android.os.Environment;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class FileUtil {

    public static File DEFAULT_FILE_DIRECTORY = Environment.getExternalStorageDirectory();

    public static void saveFile(String str, String fileName) {

        try {

            File file = new File(DEFAULT_FILE_DIRECTORY, fileName);

            if (file.exists()) {
                file.delete();
            }

            file.createNewFile();
            FileOutputStream outStream = new FileOutputStream(file);
            outStream.write(str.getBytes());

            outStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deletefile(String fileName) {
        try {

            File file = new File(DEFAULT_FILE_DIRECTORY, fileName);
            file.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getFile(String fileName) {
        try {

            File file = new File(DEFAULT_FILE_DIRECTORY,fileName);
            FileInputStream fis = new FileInputStream(file);

            byte[] b = new byte[1024];
            int len = 0;

            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            while ((len = fis.read(b)) != -1) {
                baos.write(b, 0, len);
            }

            byte[] data = baos.toByteArray();

            baos.close();
            fis.close();

            return new String(data);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
