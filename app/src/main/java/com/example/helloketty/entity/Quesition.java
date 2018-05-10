package com.example.helloketty.entity;

import com.example.helloketty.entityutil.QuestionType;

import java.util.ArrayList;

/**
 * Created by HelloKetty on 2018/5/1.
 */

public class Quesition {
    public static int QUS_STATE_OFF = 0;
    public static int QUS_STATE_ON = 1;
    //题目id
    private String quesitionId;
    //题目内容
    private String title;
    //单选多选标识
    private int type;//0 单选 1 多选
    //题目
    private String content;
    //选项
    private ArrayList<Answer> answers;
    //是否解答
    private int que_state ;

    public Quesition(String quesitionId, int type, String content, ArrayList<Answer> answers) {
        this.quesitionId = quesitionId;
        this.type = type;
        this.content = content;
        this.answers = answers;
        this.que_state = QUS_STATE_ON;
    }

    public Quesition(String quesitionId, String title, int type, String content, ArrayList<Answer> answers) {
        this.quesitionId = quesitionId;
        this.title = title;
        this.type = type;
        this.content = content;
        this.answers = answers;
        this.que_state = QUS_STATE_ON;
    }

    public Quesition(String quesitionId, int type, String content) {
        this.quesitionId = quesitionId;
        this.type = type;
        this.content = content;
        this.que_state = QUS_STATE_OFF;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public int getQue_state() {
        return que_state;
    }

    public void setQue_state(int que_state) {
        this.que_state = que_state;
    }

    public String getQuesitionId() {
        return quesitionId;
    }

    public void setQuesitionId(String quesitionId) {
        this.quesitionId = quesitionId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ArrayList<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(ArrayList<Answer> answers) {
        this.answers = answers;
    }
}
