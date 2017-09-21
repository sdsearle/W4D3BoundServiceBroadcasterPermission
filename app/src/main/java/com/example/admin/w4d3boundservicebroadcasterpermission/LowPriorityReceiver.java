package com.example.admin.w4d3boundservicebroadcasterpermission;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class LowPriorityReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Low Priority", Toast.LENGTH_SHORT).show();
    }
}
