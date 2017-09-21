package com.example.admin.w4d3boundservicebroadcasterpermission;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {


    DynamicReceiver dynamicReceiver = new DynamicReceiver();
    IntentFilter intentFilter = new IntentFilter();
    Intent intentMusic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {

        intentFilter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        intentFilter.addAction(Intent.ACTION_POWER_CONNECTED);
        intentFilter.addAction(Intent.ACTION_POWER_DISCONNECTED);
        intentFilter.addAction(Intent.ACTION_TIMEZONE_CHANGED);
        intentFilter.addAction(Intent.ACTION_HEADSET_PLUG);
        registerReceiver(dynamicReceiver, intentFilter);

        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();

        unregisterReceiver(dynamicReceiver);
    }

    @Override
    protected void onDestroy() {
        intentMusic.setAction("STOP");

        startService(intentMusic);
        stopService(intentMusic);

        super.onDestroy();
    }

    public void music(View view) {

        intentMusic = new Intent(this, ForegroundMusicService.class);

        switch (view.getId()) {

            case R.id.btnMusic:
                intentMusic.setAction("START");
                startService(intentMusic);
                break;

            case R.id.btnStopMusic:
                intentMusic.setAction("STOP");

                startService(intentMusic);
                stopService(intentMusic);
                break;

        }

    }
}
