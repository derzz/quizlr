package com.derz.thebigquizapp;

import static java.lang.Integer.parseInt;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

public class Music extends Service {
    MediaPlayer player;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private BroadcastReceiver StateReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String status = intent.getStringExtra("status");
            // Pause the music
            if (status != null) {
                int musicStatus = Integer.parseInt(status);

                if (musicStatus == 0) {
                    if (player != null && player.isPlaying()) {
                        player.pause();
                    }
                } else if (musicStatus == 1) {
                    if (player == null) {
                        player = MediaPlayer.create(context, R.raw.quiz_music);
                        player.setLooping(true);
                    }
                    if (!player.isPlaying()) {
                        player.start();
                    }
                }
                else if (musicStatus == 2) {
                    if (player != null && player.isPlaying()) {
                        player.stop();
                        player.release();
                    }
                }
            }
            else if(player != null){
                player.stop();
                player.release();
            }
        }
    };

    public void onCreate() {
        player = MediaPlayer.create(this, R.raw.quiz_music);
        player.setLooping(true);
        Log.d("TEST", "Creating the MediaPlayer!");
        LocalBroadcastManager.getInstance(this).registerReceiver(StateReceiver, new IntentFilter("status"));
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("TEST", "Playing the initial mediaplayer!");
        player.start();
        return Service.START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        // Unregister the broadcast receiver
        LocalBroadcastManager.getInstance(this).unregisterReceiver(StateReceiver);

        // Stop and release the player
        if (player != null) {
            player.stop();
            player.release();
            player = null;
        }

        super.onDestroy();
    }



}