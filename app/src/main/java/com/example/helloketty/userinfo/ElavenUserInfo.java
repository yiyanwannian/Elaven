package com.example.helloketty.userinfo;

import org.elastos.carrier.AbstractCarrierHandler;
import org.elastos.carrier.Carrier;
import org.elastos.carrier.ConnectionStatus;
import org.elastos.carrier.UserInfo;

import  org.elastos.carrier.exceptions.ElastosException;
import  com.example.helloketty.util.Synchronizer;
import  com.example.helloketty.util.Utils;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.File;

public class ElavenUserInfo {

    public static String TAG = "Elaven";

    public  String createUserId() {

        //1.初始化实例，获得相关信息
        try {

            //File file=((Context)this).getFilesDir();
            //String path=file.getAbsolutePath();
            //String path = this.getClass().getResource("");
            String  path = "";

            BootstrapOptions options = new BootstrapOptions(path);
            CarrierFriendHandler handler = new CarrierFriendHandler();

            //1.1获得Carrier的实例
            Carrier carrierInst = Carrier.getInstance(options, handler);

            //1.2获得Carrier的地址
            String carrierAddr = carrierInst.getAddress();
            Log.i(TAG,"address: " + carrierAddr);

            //1.3获得Carrier的用户ID
            String carrierUserID = carrierInst.getUserId();
            Log.i(TAG,"userID: " + carrierUserID);

            return carrierAddr;

        } catch (ElastosException e) {
            e.printStackTrace();
        }

        return  "";
    }

    static class CarrierFriendHandler extends AbstractCarrierHandler {
        Synchronizer synch = new Synchronizer();
        String from;
        ConnectionStatus friendStatus;
        String CALLBACK="call back";

        public void onReady(Carrier carrier) {
            synch.wakeup();
        }

        public void onFriendConnection(Carrier carrier, String friendId, ConnectionStatus status) {

            Log.i(CALLBACK,"friendid:" + friendId + "connection changed to: " + status);
            from = friendId;
            friendStatus = status;
            if (friendStatus == ConnectionStatus.Connected)
                synch.wakeup();
        }

        //2.2 通过好友验证
        public void onFriendRequest(Carrier carrier, String userId, UserInfo info, String hello) {
            try {

                if (hello.equals("auto-accepted")) {
                    carrier.AcceptFriend(userId);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        //3.2 接受好友信息
        public void onFriendMessage(Carrier carrier,String fromId, String message) {

            Log.i(CALLBACK,"address:" + fromId + "connection changed to: " + message);
        }

    }
}
