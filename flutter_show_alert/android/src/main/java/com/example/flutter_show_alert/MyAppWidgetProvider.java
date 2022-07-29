package com.example.flutter_show_alert;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.RemoteViews;



import android.util.Log;
import android.view.View;


/**
 * Implementation of App Widget functionality.
 */
public class MyAppWidgetProvider extends AppWidgetProvider {

    public static String bs,money;
    public static void accept(String a,String b){
         bs=a;
         money=b;
    }

    /*
     * 每次窗口小部件被更新都调用一次该方法
     */
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for(int appwidgetId : appWidgetIds){
            @SuppressLint("RemoteViewLayout") RemoteViews remoteViews = new RemoteViews(context.getPackageName(),R.layout.xbj_xml);

//            int a=  remoteViews.getLayoutId();
//            View view = view.findViewById();
//            view.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Log.d("TAG", "onClick: 小插件点击");
//                }
//            });
            Intent intents = context.getPackageManager().getLaunchIntentForPackage("com.wsdz.sportsgold");
            intents.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            Intent intent = new Intent(context, ShowActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intents, 0);
            remoteViews.setOnClickPendingIntent(R.id.xbj_re, pendingIntent);
            remoteViews.setTextViewText(R.id.txt_bs,bs);
            remoteViews.setTextViewText(R.id.txt_money,money);
            appWidgetManager.updateAppWidget(appwidgetId,remoteViews);
        }

    }
    /*
     * 接收窗口小部件点击时发送的广播
     */
    @Override
    public void onReceive(Context context, Intent intent) {

        super.onReceive(context, intent);
    }
    /*
     * 当小部件从备份恢复时调用该方法
     */
    @Override
    public void onRestored(Context context, int[] oldWidgetIds, int[] newWidgetIds) {
        super.onRestored(context, oldWidgetIds, newWidgetIds);
    }

    /*
     * 每删除一次窗口小部件就调用一次
     */
    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        super.onDeleted(context, appWidgetIds);
    }

    /*
     * 当该窗口小部件第一次添加到桌面时调用该方法
     */
    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
//        context.startService(new Intent(context,TimeService.class) );
    }
    /*
     * 当最后一个该窗口小部件删除时调用该方法
     */
    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
//        context.stopService(new Intent(context,TimeService.class));
    }
    /*
     * 当小部件大小改变时
     */
    @Override
    public void onAppWidgetOptionsChanged(Context context, AppWidgetManager appWidgetManager, int appWidgetId, Bundle newOptions) {
        super.onAppWidgetOptionsChanged(context, appWidgetManager, appWidgetId, newOptions);
    }

}