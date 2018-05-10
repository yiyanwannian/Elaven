package com.example.helloketty.entity;

import android.app.Application;

import java.util.ArrayList;

/**
 * Created by HelloKetty on 2018/5/1.
 */

public class ResearchList {
    //问卷id
    private String researchId;
    //问卷状态
    private String status;
    //问卷主题
    private String title;
    //题目
    private ArrayList<Quesition> quesitions;
    // summary
    private String summary;

    public String getSummary() { return summary; }

    public void setSummary(String summary) { this.summary = summary; }

    public ArrayList<Quesition> getQuesitions() {
        return quesitions;
    }

    public void setQuesitions(ArrayList<Quesition> quesitions) {
        this.quesitions = quesitions;
    }

    public String getResearchId() {
        return researchId;
    }

    public void setResearchId(String pageId) {
        this.researchId = pageId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
