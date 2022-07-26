package com.example.flutter_show_alert;

import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.IBinder;
import android.widget.RemoteViews;

import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TimeService extends Service {
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Timer timer;
    private int i;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        i = 0;
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                updata();
            }
        },0,2000);
    }

    private void updata() {
        i+=1000;
        //更新的逻辑
      //  String time = sdf.format(new Date());
        RemoteViews remoteViews = new RemoteViews(getPackageName(),R.layout.xbj_xml);
        remoteViews.setTextViewText(R.id.txt_bs,i+"");
        AppWidgetManager manager = AppWidgetManager.getInstance(getApplicationContext());
        ComponentName componentName =new ComponentName(getApplicationContext(),MyAppWidgetProvider.class);
        manager.updateAppWidget(componentName,remoteViews);


    }

}