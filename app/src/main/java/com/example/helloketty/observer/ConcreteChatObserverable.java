package com.example.helloketty.observer;

import org.elastos.carrier.UserInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wei on 2018/5/10.
 */

public class ConcreteChatObserverable implements IChatObserverable {


    public List<IChatObserver> data = new ArrayList<>();

    @Override
    public void addChatObject(IChatObserver chatObserver) {
        if (chatObserver != null) {
            data.add(chatObserver);
        }

    }

    @Override
    public void removeChatObject(IChatObserver chatObserver) {
        if (chatObserver != null) {
            data.remove(chatObserver);
        }

    }

    @Override
    public void onMyFriendMessage(String fromId, String message) {
        for (IChatObserver iChatObserver : data) {
            iChatObserver.receiveFriendMessage(fromId, message);
        }

    }

    @Override
    public void onMyFriendRequest(String fromId, UserInfo info, String hello) {
        for (IChatObserver iChatObserver : data) {
            iChatObserver.receiveFriendRequest(fromId, info, hello);
        }
    }
}
