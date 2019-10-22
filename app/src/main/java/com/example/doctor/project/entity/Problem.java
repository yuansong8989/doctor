package com.example.doctor.project.entity;


public class Problem {
    private int id;
    private String problemtitle;
    private String analysis;
    private String choicea;
    private String choiceb;
    private String choicec;
    private String choiced;
    private String success;
    private String chinasuccess;
    private String personsuccess;
    private String grade;
    private int belong;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProblemtitle() {
        return problemtitle;
    }

    public void setProblemtitle(String problemtitle) {
        this.problemtitle = problemtitle;
    }

    public String getAnalysis() {
        return analysis;
    }

    public void setAnalysis(String analysis) {
        this.analysis = analysis;
    }

    public String getChoicea() {
        return choicea;
    }

    public void setChoicea(String choicea) {
        this.choicea = choicea;
    }

    public String getChoiceb() {
        return choiceb;
    }

    public void setChoiceb(String choiceb) {
        this.choiceb = choiceb;
    }

    public String getChoicec() {
        return choicec;
    }

    public void setChoicec(String choicec) {
        this.choicec = choicec;
    }

    public String getChoiced() {
        return choiced;
    }

    public void setChoiced(String choiced) {
        this.choiced = choiced;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getChinasuccess() {
        return chinasuccess;
    }

    public void setChinasuccess(String chinasuccess) {
        this.chinasuccess = chinasuccess;
    }

    public String getPersonsuccess() {
        return personsuccess;
    }

    public void setPersonsuccess(String personsuccess) {
        this.personsuccess = personsuccess;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public int getBelong() {
        return belong;
    }

    public void setBelong(int belong) {
        this.belong = belong;
    }
}
