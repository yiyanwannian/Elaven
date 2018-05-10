package com.example.helloketty.entity;

/**
 * Created by HelloKetty on 2018/5/1.
 */

public class Answer {
    private boolean isCheck;
    //是否被选中
    private String questionId;
    //答案id
    private String answerId;
    //答案主体
    private String answer_content;

    public boolean isCheck() { return isCheck; }

    public void setCheck(boolean check) { isCheck = check;}

    public String getQuestionId() { return questionId; }

    public void setQuestionId(String questionId) { this.questionId = questionId; }

    public String getAnswerId() {
        return answerId;
    }

    public void setAnswerId(String answerId) {
        this.answerId = answerId;
    }

    public String getAnswer_content() { return answer_content; }

    public void setAnswer_content(String answer_content) {
        this.answer_content = answer_content;
    }
}
