package com.example.flutter_show_alert;

import android.app.Application;
import android.content.Context;

public class NowApplication  extends Application {
    private static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context app_getContext(){
        return context;
    }



}
