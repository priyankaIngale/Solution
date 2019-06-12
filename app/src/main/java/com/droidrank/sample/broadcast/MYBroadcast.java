package com.droidrank.sample.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class MYBroadcast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        Toast.makeText(context, "Calll the police", Toast.LENGTH_SHORT).show();
        Log.e("TAG","BROADCAST SHOW");
    }
}
