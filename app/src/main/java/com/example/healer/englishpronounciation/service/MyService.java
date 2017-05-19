package com.example.healer.englishpronounciation.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import java.io.File;


public class MyService extends Service {
    MediaPlayer mp;
    public static String PATHSOUND = "android.resource://com.example.healer.englishpronounciation/";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Let it continue running until it is stopped.
        // Chơi nhạc.
        //initUI();
        String songIndex = intent.getExtras().getString("pathSound");
        if (songIndex != null) {
            mp = MediaPlayer.create(getApplicationContext(),Uri.parse(PATHSOUND + songIndex));
            if(mp != null){
                mp.start();
                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    public void onCompletion(MediaPlayer mp) {
                        // TODO Auto-generated method stub
                        mp.release();
                    }
                });
            }
        }
        else{
            Toast.makeText(getApplicationContext(),"File does'nt exists",Toast.LENGTH_SHORT).show();
        }

        super.onStart(intent, startId);
        return START_STICKY;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("Player Service", "Player Service Stopped");
        if (mp != null) {
            if (mp.isPlaying()) {
                mp.stop();
            }
            mp.release();
        }
    }

}
