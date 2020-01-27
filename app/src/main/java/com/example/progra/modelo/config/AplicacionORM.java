package com.example.progra.modelo.config;

import android.app.Application;

import com.activeandroid.ActiveAndroid;

public class AplicacionORM extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ActiveAndroid.initialize(this);
    }
}
