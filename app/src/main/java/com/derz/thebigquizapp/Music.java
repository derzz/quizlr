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
            if (parseInt(String.valueOf(status)) == 0) {
                player.pause();  //When app is in background and not killed, we just want to pause the player and not want to lose the current state.
            } else if (parseInt(String.valueOf(status)) == 1) {
                if (player != null)
                    player.start();  // If the player was created and wasn't stopped, it won't be null, and the playback will be resumed from where we left of.
                else {
                    // If the player was stopped, we need to prepare it once again.
                    player = MediaPlayer.create(context, R.raw.quiz_music);
                    player.setLooping(true);
                    player.start();
                }
            } else if(player != null){
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

    public void onDestroy() {
        player.stop();
        player.release();
        stopSelf();
        super.onDestroy();
    }


}