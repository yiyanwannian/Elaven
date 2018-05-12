package com.example.helloketty.observer;

import org.elastos.carrier.UserInfo;

/**
 * Created by wei on 2018/5/10.
 */

public interface IChatObserverable {

    void addChatObject(IChatObserver chatObserver);
    void removeChatObject(IChatObserver chatObserver);
    void onMyFriendMessage(String fromId, String message);
    void onMyFriendRequest(String fromId, UserInfo info, String hello);
}
