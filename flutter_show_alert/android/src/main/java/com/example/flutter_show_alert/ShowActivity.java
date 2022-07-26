package com.example.flutter_show_alert;

//import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;

import io.flutter.embedding.android.FlutterActivity;

public class ShowActivity extends FlutterActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
    }
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        Intent intent=new Intent(ShowActivity.this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        startActivity(intent);
        finish();

        return true;

    }
}