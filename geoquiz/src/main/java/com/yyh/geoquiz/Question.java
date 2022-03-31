package com.yyh.geoquiz;

public class Question {
    private Integer textId;
    private Boolean answer;

    public Question() {
    }

    public Question(Integer textId, Boolean answer) {
        this.textId = textId;
        this.answer = answer;
    }

    public Integer getTextId() {
        return textId;
    }

    public void setTextId(Integer textId) {
        this.textId = textId;
    }

    public Boolean getAnswer() {
        return answer;
    }

    public void setAnswer(Boolean answer) {
        this.answer = answer;
    }
}
