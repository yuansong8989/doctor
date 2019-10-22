package com.example.doctor.project;

import android.app.Application;

import me.yokeyword.fragmentation.BuildConfig;
import me.yokeyword.fragmentation.Fragmentation;

public class AppApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Fragmentation.builder()
                // show stack view. Mode: BUBBLE, SHAKE, NONE
                .stackViewMode(Fragmentation.BUBBLE)
                .debug(BuildConfig.DEBUG)
                .install();
    }
}
