package com.example.admin.w4d3boundservicebroadcasterpermission;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class CustomReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        String s = intent.getStringExtra("data");
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }
}
