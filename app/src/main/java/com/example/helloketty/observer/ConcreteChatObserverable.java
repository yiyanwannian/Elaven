package com.example.helloketty.observer;

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
    public void notifyChatObject(String message) {
        for (IChatObserver iChatObserver : data) {
            iChatObserver.receiveMessage(message);
        }

    }
}
