package com.example.helloketty.observer;

import org.elastos.carrier.UserInfo;

/**
 * Created by wei on 2018/5/10.
 */

public interface IChatObserver {

    void receiveFriendMessage(String fromId, String message);
    void receiveFriendRequest(String fromId, UserInfo info, String hello);
}
