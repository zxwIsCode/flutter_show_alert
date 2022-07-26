package com.example.flutter_show_alert;

import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;

import java.util.HashMap;
import java.util.Map;

import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.plugin.common.EventChannel;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.EventChannel.EventSink;
import io.flutter.plugin.common.MethodChannel.Result;

/** FlutterShowAlertPlugin */
public class FlutterShowAlertPlugin implements FlutterPlugin, MethodCallHandler {
  /// The MethodChannel that will the communication between Flutter and native Android
  ///
  /// This local reference serves to register the plugin with the Flutter Engine and unregister it
  /// when the Flutter Engine is detached from the Activity
  private MethodChannel channel;
  private static Context context;

  // 事件派发对象
  private  EventChannel.EventSink eventSink = null;
  // 事件派发流
  private  EventChannel.StreamHandler streamHandler = new EventChannel.StreamHandler() {
    @Override
    public void onListen(Object arguments, EventSink events) {
      eventSink = events;
    }

    @Override
    public void onCancel(Object arguments) {
    eventSink = null;
    }
  };


  @Override
  public void onAttachedToEngine(@NonNull FlutterPluginBinding flutterPluginBinding) {
    channel = new MethodChannel(flutterPluginBinding.getBinaryMessenger(), "flutter_show_alert");
    channel.setMethodCallHandler(this);

    // 初始化事件
    EventChannel eventChannel = new EventChannel(flutterPluginBinding.getBinaryMessenger(), "flutter_show_alert/envent");
    eventChannel.setStreamHandler(streamHandler);
  }

  @Override
  public void onMethodCall(@NonNull MethodCall call, @NonNull Result result) {
    if (call.method.equals("getPlatformVersion")) {
      result.success("Android " + android.os.Build.VERSION.RELEASE);
    } else if (call.method.equals("testArg")) {
      String paramsStr = call.argument("value");
      result.success("Android :" + paramsStr);
    }
    else if (call.method.equals("testArgArr")) {
      String paramsStr = call.argument("value");

      result.success("android" + "1,3");
    }
    else if (call.method.equals("clickBtn")) {
      System.out.print("android层：clickBtn");

//      if (eventSink != null) {
//        Map map = new HashMap();
//        map.put("event","demoEvent");
//        map.put("value","value is 100");
//        eventSink.success(map);
//      }
//      MainActivity activity = new MainActivity();
//      activity.startShowAlert();
//      MainActivity activity1 = new MainActivity();
//      activity.startShowAlert();
//      CjActivity cjActivity = new  CjActivity();
//      cjActivity.startShowAlert();
//      context = getApplicationContext();
      CjActivity.Cj_AppWeight();
//      result.success("allmsg");
      if (eventSink != null) {
        Map map = new HashMap();
        map.put("event","demoEvent");
        map.put("isSucc","1");
        eventSink.success(map);
      }

    }
    else if (call.method.equals("updateBtn")) {
      System.out.print("android层：updateBtn");
      CjActivity.get_BS("100","30.00");
//      result.success("allmsg");
      if (eventSink != null) {
        Map map = new HashMap();
        map.put("event","updateEvent");
        map.put("isSucc","1");
        eventSink.success(map);
      }
    }
    else {
      result.notImplemented();
    }
  }

  @Override
  public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
    channel.setMethodCallHandler(null);
  }
}
