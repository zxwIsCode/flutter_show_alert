package com.example.flutter_show_alert;

//import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.RequiresApi;

import io.flutter.embedding.android.FlutterActivity;

public class CjActivity extends FlutterActivity {
    private Button dj_cj;
    private PendingIntent successCallback;
  //  private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cj);
        dj_cj = findViewById(R.id.dj_cj);

//        dj_cj.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
//        Cj_AppWeight();

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public  static void Cj_AppWeight() {
        PendingIntent successCallback;
        Context  context = NowApplication.app_getContext();
        AppWidgetManager appWidgetManager = null;
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            appWidgetManager = context.getSystemService(AppWidgetManager.class);
//        }
        ComponentName myProvider = new ComponentName(context, MyAppWidgetProvider.class);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (appWidgetManager.isRequestPinAppWidgetSupported()) {
                // AppWidget创建成功回调
                Intent pinnedWidgetCallbackIntent = new Intent(context, MyAppWidgetProvider.class);
//
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                    successCallback = PendingIntent.getBroadcast(context, 0, pinnedWidgetCallbackIntent, PendingIntent.FLAG_IMMUTABLE);
                    Log.d("创建回调", "onClick: " + successCallback.toString());
                } else {
                    successCallback = PendingIntent.getBroadcast(context, 0, pinnedWidgetCallbackIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                }


                // successCallback = PendingIntent.getBroadcast(context, 0, pinnedWidgetCallbackIntent,PendingIntent.FLAG_IMMUTABLE);
                //如果不需要创建成功回调，requestPinAppWidget的第三个参数可以为null
                appWidgetManager.requestPinAppWidget(myProvider, null, successCallback);
            }
        }
    }


}