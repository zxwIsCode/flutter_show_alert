package com.example.flutter_show_alert;

import android.app.Application;
import android.content.Context;

import io.flutter.app.FlutterApplication;

public class NowApplication  extends FlutterApplication {
    private static Context context;
    private static NowApplication sApplication;
    @Override
    public void onCreate() {
        super.onCreate();
        sApplication = this;
        context = getApplicationContext();
    }

    public static Context app_getContext(){
        return context;
    }

    public static NowApplication getApplication()
    {
        return sApplication;
    }



}
