package com.droidrank.sample;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    TextView _tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sp=getSharedPreferences("INTELLI",getApplicationContext().MODE_PRIVATE);
        final String login=sp.getString("login","false");

        _tv = (TextView) findViewById(R.id.text1);
        new CountDownTimer(10000, 1000) { // adjust the milli seconds here

            public void onTick(long millisUntilFinished) {
                _tv.setText("Splash Screen " + "\n" + String.format("%d min, %d sec",
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished),
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
            }

            public void onFinish() {
                _tv.setText("done!");
                if (login.equals("true")) {
                    Intent i = new Intent(MainActivity.this, Activity_Dashboard.class);
                    startActivity(i);
                }else{
                    Intent i = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(i);
                }
            }
        }.start();

    }

}
