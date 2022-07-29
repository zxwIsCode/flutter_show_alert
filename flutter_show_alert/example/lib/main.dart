import 'package:flutter/material.dart';
import 'dart:async';

import 'package:flutter/services.dart';
import 'package:flutter_show_alert/flutter_show_alert.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatefulWidget {
  const MyApp({Key? key}) : super(key: key);

  @override
  State<MyApp> createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  String _platformVersion = 'Unknown';
  String? _testArg = "";
  String? _testArgArr = "";
  String? _clickStr = "";
  String? _watchStr = "";

  String? _steps;
  String? _moneys;

  @override
  void initState() {
    super.initState();
    // 初始化dart层类，先监听
    FlutterShowAlert flugin = FlutterShowAlert();
    FlutterShowAlert.listener((args,args2) {
      print("args = $args");
      setState(() {
        _watchStr = args;
      });
    });
    initPlatformState();
  }

  // Platform messages are asynchronous, so we initialize in an async method.
  Future<void> initPlatformState() async {
    String platformVersion;
    String? testArgMsg;
    String? testArgArrMsg;
    // Platform messages may fail, so we use a try/catch PlatformException.
    // We also handle the message potentially returning null.
    try {
      platformVersion =
          await FlutterShowAlert.platformVersion ?? 'Unknown platform version';
      testArgMsg =
          await FlutterShowAlert.testArg;
      // testArgArrMsg =
      // await FlutterShowAlert.testArgArr;
    } on PlatformException {
      platformVersion = 'Failed to get platform version.';
    }

    // If the widget was removed from the tree while the asynchronous platform
    // message was in flight, we want to discard the reply rather than calling
    // setState to update our non-existent appearance.
    if (!mounted) return;

    setState(() {
      _platformVersion = platformVersion;
      _testArg = testArgMsg;
      _testArgArr = testArgArrMsg;
    });
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Plugin example app'),
        ),
        body: Center(
          child: Column(
            children: [
              Text('Running on: $_platformVersion\n'),
              Text('flutter调用安卓传值: $_testArg\n'),
              Text('flutter调用安卓传数组: $_testArgArr\n'),
              Divider(),

              InkWell(
                onTap: () async {
                  print("flutter监听安卓派发");
                  String? clickStr;
                  clickStr = await FlutterShowAlert.clickBtn("100","10");
                  Future.delayed(Duration(seconds: 3), () async {
                    print("首次更新数据");
                    await FlutterShowAlert.updateBtn("100","10");
                  });
                  if(clickStr == "1") {
                    await FlutterShowAlert.updateBtn("100","10");
                  }
                  setState(() {
                    _clickStr = clickStr;
                  });
                },
                child: Text('flutter添加悬浮框: $_clickStr\n'),
              ),
              Text('flutter监听的变化: $_watchStr\n'),

              InkWell(
                onTap: () async {
                  print("更新悬浮框数据");
                  String? clickStr;
                  clickStr = await FlutterShowAlert.updateBtn("200","30");

                  setState(() {
                    _clickStr = clickStr;
                  });
                },
                child: Text('flutter更新悬浮框数据: $_clickStr\n'),
              ),
              Text('更新数据为: --- 步数$_steps----金额$_moneys\n'),



            ],
          ),
        ),
      ),
    );
  }
}
