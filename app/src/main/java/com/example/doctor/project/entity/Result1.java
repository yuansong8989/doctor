package com.example.doctor.project.entity;

import java.io.Serializable;
import java.util.List;

public class Result1 implements Serializable {
    private Integer grade;
    private List<JieGuo> per;


    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public List<JieGuo> getPer() {
        return per;
    }

    public void setPer(List<JieGuo> per) {
        this.per = per;
    }
}
