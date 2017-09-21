package com.example.admin.w4d3boundservicebroadcasterpermission;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.widget.Toast;

public class DynamicReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        String toastString = "";
        switch (intent.getAction()) {
            case Intent.ACTION_AIRPLANE_MODE_CHANGED:
                boolean isAirplaneModeOn = intent.getBooleanExtra("state", false);

                if (isAirplaneModeOn)
                    toastString = "Airplane On";
                else
                    toastString = "Airplane off";
                break;

            case Intent.ACTION_TIMEZONE_CHANGED:
                toastString = "Time Zone Changed";
                break;

            case Intent.ACTION_HEADSET_PLUG:
                toastString = "headset";
                break;

            case Intent.ACTION_POWER_CONNECTED:
                toastString = "Power Connect";
                break;

            case Intent.ACTION_POWER_DISCONNECTED:
                toastString = "Power Disconnect";
                break;
        }

        Toast.makeText(context, toastString, Toast.LENGTH_SHORT).show();
    }
}
