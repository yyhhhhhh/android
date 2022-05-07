package com.yyh.crimelntent.entity;

import java.io.Serializable;

public class Crime implements Serializable {

    private static final long serialVersionUID = -4651679128713510684L;

    private String title;

    private Integer id;

    private String date;

    private Boolean isSolved;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Boolean getSolved() {
        return isSolved;
    }

    public void setSolved(Boolean solved) {
        isSolved = solved;
    }
}
