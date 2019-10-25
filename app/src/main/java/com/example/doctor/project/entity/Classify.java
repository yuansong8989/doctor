package com.example.doctor.project.entity;


public class Classify {
    private String classifyname;
    private int id;
    private int classifybelong;
    private String success;
    private String fail;
    private int alltitle;
    private String about;
    private String image;

    public String getClassifyname() {
        return classifyname;
    }

    public void setClassifyname(String classifyname) {
        this.classifyname = classifyname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClassifybelong() {
        return classifybelong;
    }

    public void setClassifybelong(int classifybelong) {
        this.classifybelong = classifybelong;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getFail() {
        return fail;
    }

    public void setFail(String fail) {
        this.fail = fail;
    }

    public int getAlltitle() {
        return alltitle;
    }

    public void setAlltitle(int alltitle) {
        this.alltitle = alltitle;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
