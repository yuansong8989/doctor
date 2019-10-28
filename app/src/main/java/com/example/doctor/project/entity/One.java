package com.example.doctor.project.entity;

import java.util.List;

public class One {
        private int id;
        private String classifyname;
        private int alltite;
        private List<Two> twos;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClassifyname() {
        return classifyname;
    }

    public void setClassifyname(String classifyname) {
        this.classifyname = classifyname;
    }

    public int getAlltite() {
        return alltite;
    }

    public void setAlltite(int alltite) {
        this.alltite = alltite;
    }

    public List<Two> getTwos() {
        return twos;
    }

    public void setTwos(List<Two> twos) {
        this.twos = twos;
    }
}
