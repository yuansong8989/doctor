package com.example.doctor.project.entity;

import java.io.Serializable;

public  class JieGuo implements Serializable {
    private String check;
    private Integer id;


    public String getCheck() {
        return check;
    }

    public void setCheck(String check) {
        this.check = check;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}