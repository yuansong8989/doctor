package com.example.doctor.project.entity;

import com.example.doctor.project.Adapter.TitleAdapter;

public class TitEvent {

private String message[];
    public TitEvent(String b[]) {

        message=b;
    }


    public String[] getMessage() {
        return message;
    }

    public void setMessage(String[] message) {
        this.message = message;
    }
}
