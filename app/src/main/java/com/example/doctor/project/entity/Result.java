package com.example.doctor.project.entity;

import java.util.List;

public class Result {
    private List<Problem> res;
    public void setRes(List<Problem> res) {
        this.res = res;
    }
    public List<Problem> getRes() {
        return res;
    }
}
