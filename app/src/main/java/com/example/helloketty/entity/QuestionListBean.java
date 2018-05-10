package com.example.helloketty.entity;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by HelloKetty on 2018/5/6.
 */

public class QuestionListBean {
    private ArrayList<Quesition> question_list;

    public ArrayList<Quesition> getList() {
        return question_list;
    }

    public void setList(ArrayList<Quesition> list) {
        this.question_list = question_list;
    }
}
