package com.example.doctor.project.entity;

import java.io.Serializable;

public  class JieGuo implements Serializable {
    private String daan;
    private Integer id;

    public String getDaan() {
        return daan;
    }

    public void setDaan(String daan) {
        this.daan = daan;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}