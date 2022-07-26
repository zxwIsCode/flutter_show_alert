package com.example.flutter_show_alert;

import androidx.annotation.RequiresApi;
//import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import io.flutter.embedding.android.FlutterActivity;

public class MainActivity extends FlutterActivity {
    private TextView mian_txt;
    private static final int REQUEST_CREATE_APPWIDGET = 5;
    private static final int REQUEST_PICK_APPWIDGET = 9;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mian_txt=findViewById(R.id.mian_txt);
        Context thsi=MainActivity.this;
        mian_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(thsi,CjActivity.class));

            }
        });



    }

}