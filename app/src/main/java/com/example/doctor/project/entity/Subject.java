package com.example.doctor.project.entity;


import java.io.Serializable;
import java.util.List;

public class Subject implements Serializable {
    private List<Classify> subject1;
    private List<Classify> subject2;
    private List<Classify> subject3;
    private List<Classify> subject4;
    private List<AllZhangjie> allZhangjies;
    private List<Problem> problems;

    public List<Classify> getSubject1() {
        return subject1;
    }

    public void setSubject1(List<Classify> subject1) {
        this.subject1 = subject1;
    }

    public List<Classify> getSubject2() {
        return subject2;
    }

    public void setSubject2(List<Classify> subject2) {
        this.subject2 = subject2;
    }

    public List<Classify> getSubject3() {
        return subject3;
    }

    public void setSubject3(List<Classify> subject3) {
        this.subject3 = subject3;
    }

    public List<Classify> getSubject4() {
        return subject4;
    }

    public void setSubject4(List<Classify> subject4) {
        this.subject4 = subject4;
    }

    public List<AllZhangjie> getAllZhangjies() {
        return allZhangjies;
    }

    public void setAllZhangjies(List<AllZhangjie> allZhangjies) {
        this.allZhangjies = allZhangjies;
    }

    public List<Problem> getProblems() {
        return problems;
    }

    public void setProblems(List<Problem> problems) {
        this.problems = problems;
    }
}
