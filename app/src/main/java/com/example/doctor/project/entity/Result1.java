package com.example.doctor.project.entity;

import java.io.Serializable;
import java.util.List;

public class Result1 implements Serializable {
    private Integer grade;
    private List<JieGuo> list;


    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public List<JieGuo> getList() {
        return list;
    }

    public void setList(List<JieGuo> list) {
        this.list = list;
    }
}
