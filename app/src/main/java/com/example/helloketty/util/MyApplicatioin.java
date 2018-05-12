package com.example.helloketty.util;

import android.app.Activity;
import android.app.Application;

import com.example.helloketty.entity.ElavenUser;
import com.example.helloketty.observer.ConcreteChatObserverable;
import com.example.helloketty.observer.IChatObserver;
import com.example.helloketty.observer.IChatObserverable;

import java.util.ArrayList;
import java.util.List;

public class MyApplicatioin extends Application {
    private String userID = "";
    private ElavenUser user;

    private static MyApplicatioin application = null;

    private IChatObserverable iChatObserverable;

    @Override
    public void onCreate()
    {
        super.onCreate();
        application = this;
        iChatObserverable = new ConcreteChatObserverable();

    }

    public static MyApplicatioin getInstance()
    {
        return application;

    }

    private List<Activity> activities = new ArrayList<>();

    public void addActivity(Activity activity) {
        if (!activities.contains(activity)) {

            activities.add(activity);
        }
    }

    public void finishActivity(){
        for (Activity activity : activities) {
            activity.finish();
        }
    }


    public void addChatObserver (IChatObserver iChatObserver) {
        if (iChatObserverable != null) {
            iChatObserverable.addChatObject(iChatObserver);
        }
    }

    public void removeChatObserver (IChatObserver iChatObserver) {
        if (iChatObserverable != null) {
            iChatObserverable.removeChatObject(iChatObserver);

        }
    }

    public void notifyChatObject (String message) {
        iChatObserverable.notifyChatObject(message);
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public ElavenUser getUser() {
        return user;
    }

    public void setUser(ElavenUser user) {
        this.user = user;
    }
}
