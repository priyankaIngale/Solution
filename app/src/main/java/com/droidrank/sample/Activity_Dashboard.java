package com.droidrank.sample;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.droidrank.sample.broadcast.MYBroadcast;
import com.droidrank.sample.volley.Volley_Demo;

import java.sql.Time;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class Activity_Dashboard extends AppCompatActivity {

    WebView web_view_global;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        BroadcastReceiver br = new MYBroadcast();
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        filter.addAction(Intent.ACTION_BOOT_COMPLETED);
        this.registerReceiver(br, filter);

        SharedPreferences sp=getSharedPreferences("INTELLI",getApplicationContext().MODE_PRIVATE);
        String val=sp.getString("login","false");

        Log.e("TAG",""+val);
        web_view_global=(WebView)findViewById(R.id.web_view_global);
        web_view_global.setWebViewClient(new WebViewClient());
        web_view_global.loadUrl("http://www.agiliztech.com/about-us/");

       if (val.equals("true")){
           final Handler handler = new Handler();
           Timer timer = new Timer();
           TimerTask doAsynchronousTask = new TimerTask() {
               @Override
               public void run() {
                   handler.post(new Runnable() {
                       public void run() {
                           try {
                               Intent i = new Intent(Activity_Dashboard.this, Volley_Demo.class);
                               startActivity(i);
                               createNotification();
                           } catch (Exception e) {
                           }
                       }
                   });
               }
           };
           timer.schedule(doAsynchronousTask, 0, 900000);
       }else
       {
           Toast.makeText(this, "Notification not received...", Toast.LENGTH_SHORT).show();
       }

    }
    public void createNotification() {

        Intent intent = new Intent(this, Activity_Dashboard.class);
        PendingIntent pIntent = PendingIntent.getActivity(this, (int) System.currentTimeMillis(), intent, 0);
        Notification noti = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
            noti = new Notification.Builder(this)
                    .setContentTitle("AgilizTech test")
                    .setContentText("Hi AgilizTech Current time is:" + Calendar.getInstance().getTime())
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentIntent(pIntent).build();

        }
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        noti.flags |= Notification.FLAG_AUTO_CANCEL;
        notificationManager.notify(0, noti);

    }
}
