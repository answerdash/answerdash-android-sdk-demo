package com.answerdash.android.sdk.demo;

import android.app.Application;

import com.answerdash.android.sdk.AnswerDash;

public class MainApplication extends Application {

    @Override
    public void onCreate() {

        super.onCreate();

        AnswerDash.INSTANCE.setSiteID(this, "1061");
    }
}
