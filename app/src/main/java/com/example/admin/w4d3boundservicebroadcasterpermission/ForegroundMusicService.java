package com.example.admin.w4d3boundservicebroadcasterpermission;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.provider.MediaStore;
import android.support.annotation.IntDef;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;
import android.view.View;
import android.widget.RemoteViews;

import static android.R.attr.action;

public class ForegroundMusicService extends Service {

    MediaPlayer mMeadiaPlayer = null;

    public ForegroundMusicService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if (mMeadiaPlayer == null) {
            mMeadiaPlayer = MediaPlayer.create(this, R.raw.tokyoghoulunravel);
            mMeadiaPlayer.setLooping(true);
        }

//ONE ATTEMPT
        /*String ns = Context.NOTIFICATION_SERVICE;
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(ns);

        Notification notification = new Notification(R.drawable.shotgunsamurai, null,
                System.currentTimeMillis());

        RemoteViews notificationView = new RemoteViews(getPackageName(),
                R.layout.custom_notification);

        //the intent that is started when the notification is clicked (works)
        Intent notificationIntent = new Intent(this, MusicPlayerReciver.class);
        PendingIntent pendingNotificationIntent = PendingIntent.getActivity(this, 0,
                notificationIntent, 0);

        notification.contentView = notificationView;
        notification.contentIntent = pendingNotificationIntent;
        notification.flags |= Notification.FLAG_NO_CLEAR;

        //this is the intent that is supposed to be called when the
        //button is clicked
        Intent switchIntent = new Intent(this, MusicPlayerReciver.class);
        PendingIntent pendingSwitchIntent = PendingIntent.getBroadcast(this, 0,
                switchIntent, 0);

        notificationView.setOnClickPendingIntent(R.id.btnPlay,
                pendingSwitchIntent);

        notificationManager.notify(1, notification);*/

//ATEMPT TWO
        if (intent.getAction().equals("START")) {
            Intent notificationIntent = new Intent(this, MainActivity.class);
            //notificationIntent.setAction("Player");
            notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                    | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            notificationIntent.setAction("com.example.admin.w4d3boundservicebroadcasterpermission.main");
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                    notificationIntent, 0);

            /*Intent musicIntent = new Intent(this, MusicPlayerReciver.class);
            PendingIntent pendingMusicIntent = PendingIntent.getBroadcast(this, 0,
                    notificationIntent, 0);*/
            //RemoteViews rv = new RemoteViews(getPackageName(), R.layout.custom_notification);
            //rv.setOnClickPendingIntent(R.id.btnPlay, pendingMusicIntent);
            //rv.setOnClickPendingIntent(R.id.btnPause, pendingMusicIntent);
            //rv.setOnClickPendingIntent(R.id.btnStop, pendingMusicIntent);

            Intent playIntent = new Intent(this, ForegroundMusicService.class);
            playIntent.setAction("PLAY");
            PendingIntent pplayIntent = PendingIntent.getService(this, 0, playIntent, 0);

            Intent pauseIntent = new Intent(this, ForegroundMusicService.class);
            pauseIntent.setAction("PAUSE");
            PendingIntent ppauseIntent = PendingIntent.getService(this, 0,
                    pauseIntent, 0);

            Intent stopIntent = new Intent(this, ForegroundMusicService.class);
            stopIntent.setAction("STOP");
            PendingIntent pstopIntent = PendingIntent.getService(this, 0,
                    stopIntent, 0);

            Notification notification = new NotificationCompat.Builder(this)
                    .setSmallIcon(R.drawable.shotgunsamurai)
                    .setContentTitle("Music Player")
                    .setContentText("Tokyo Ghoul")
                    //.setCustomBigContentView(rv)
                    .setContentIntent(pendingIntent)
                    .addAction(R.drawable.play, "Play", pplayIntent)
                    .addAction(R.drawable.pause, "Pause", ppauseIntent)
                    .addAction(R.drawable.play, "Stop", pstopIntent)
                    .setOngoing(true)
                    .build();
            startForeground(10, notification);
        } else if (intent.getAction().equals("PLAY")) {
            Log.d("TAG", "Clicked Play");
            mMeadiaPlayer.start();
        } else if (intent.getAction().equals("PAUSE")) {
            Log.d("TAG", "Clicked Pause");
            mMeadiaPlayer.pause();
        } else if (intent.getAction().equals("STOP")) {
            Log.d("TAG", "Clicked Stop");
            mMeadiaPlayer.stop();
            mMeadiaPlayer = MediaPlayer.create(this, R.raw.tokyoghoulunravel);
        } else if (intent.getAction().equals(
                "STOP_SERVICE")) {
            Log.d("TAG", "Received Stop Foreground Intent");
            stopForeground(true);
            stopSelf();
        }


        return START_STICKY;
        //return super.onStartCommand(intent, flags, startId);
    }

}
