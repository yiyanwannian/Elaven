package com.example.helloketty.observer;

/**
 * Created by wei on 2018/5/10.
 */

public interface IChatObserverable {

    void addChatObject(IChatObserver chatObserver);
    void removeChatObject(IChatObserver chatObserver);
    void notifyChatObject(String message);
}
