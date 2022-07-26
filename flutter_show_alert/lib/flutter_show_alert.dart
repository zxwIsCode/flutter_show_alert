
import 'dart:async';

import 'package:flutter/services.dart';

typedef void EventCallback(args,args2);
class FlutterShowAlert {
  static const MethodChannel _channel = MethodChannel('flutter_show_alert');
  static EventCallback? _callback;
  StreamSubscription<dynamic>? _eventSubscription;


  FlutterShowAlert(){
    // 初始化事件
    initEvent();
  }


  initEvent() {
    _eventSubscription = (_eventChannelFor()?.receiveBroadcastStream().listen(eventListener,onError: errorListener) ) as StreamSubscription;
  }

  eventListener(dynamic event) {
    final Map<dynamic,dynamic> map = event;
    if(map["event"] == "demoEvent") {
      String? value = map["isSucc"];

      print("demo event data : $value");
      _callback!(value,0);
    }
    else if(map["event"] == "updateEvent") {
      String? value = map["isSucc"];

      print("demo event data : $value");
      _callback!(value,0);
    }
  }

  errorListener(Object obj) {
    final Object e = obj;
    throw e;
  }

  EventChannel? _eventChannelFor(){
    return EventChannel("flutter_show_alert/envent");
  }

  static void listener(EventCallback callback) {
    _callback = callback;
  }

  static Future<String?> get platformVersion async {
    final String? version = await _channel.invokeMethod('getPlatformVersion');
    return version;
  }

  static Future<String?> get testArg async {
    final String? msg = await _channel.invokeMethod('testArg',{"value":"testArg"});
    return msg;
  }

  static Future<String?> get testArgArr async {
    final Map map = await _channel.invokeMethod('testArgArr',{"value":"test Arg Arr"});
    return map["msg"];
  }

  static Future<String?> get clickBtn async {
    final String? content = await _channel.invokeMethod('clickBtn');
    return content;
  }

  static Future<String?> get updateBtn async {
    final String? content = await _channel.invokeMethod('updateBtn');
    return content;
  }
}
