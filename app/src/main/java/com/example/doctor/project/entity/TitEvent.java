package com.example.doctor.project.entity;

import com.example.doctor.project.Adapter.TitleAdapter;

import java.util.List;

public class TitEvent {

private List<Classify> list;
    public TitEvent(List<Classify> list1) {

        list=list1;
    }

    public List<Classify> getList() {
        return list;
    }

    public void setList(List<Classify> list) {
        this.list = list;
    }
}
