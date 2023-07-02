package com.derz.thebigquizapp;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

public class app_class extends Application implements Application.ActivityLifecycleCallbacks {

    public static boolean isActivityVisible() {
        return activityVisible;
    }

    public static void activityResumed() {
        activityVisible = true;
    }

    public static void activityPaused() {
        activityVisible = false;
    }

    private static boolean activityVisible;

    private static boolean isMusicSwitch = true; // Flag to track internal activity switch
    private static boolean isSFXSwitch = true;

    private static String currentActivity;

    @Override
    public void onCreate() {
        super.onCreate();

        // Register the app_class as a callback for activity lifecycle events
        registerActivityLifecycleCallbacks(this);

        // Start the music service
        Intent intent = new Intent(this, Music.class);
        startService(intent);
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        currentActivity = activity.getClass().getSimpleName();
    }

    public static String getCurrentActivity() {
        return currentActivity;
    }

    @Override
    public void onActivityStarted(Activity activity) {
    }

    @Override
    public void onActivityResumed(Activity activity) {
        if (isMusicSwitch) {
            sendStatus(1);
        }
    }

    @Override
    public void onActivityPaused(Activity activity) {
        if (!activityVisible || !isMusicSwitch) {
            sendStatus(0);
        }
    }

    private void sendStatus(int statusCounter) {
        Intent intent = new Intent("status");
        intent.putExtra("status", String.valueOf(statusCounter));
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

    @Override
    public void onActivityStopped(Activity activity) {
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        //sendStatus(2);
    }

    // Method to set the internal switch flag
    public static void setMusicSwitch(boolean internalSwitch) {
        isMusicSwitch = internalSwitch;
    }

    public static boolean getMusicSwitch() {
        return isMusicSwitch;
    }

    public static void setSFXSwitch(boolean internalSwitch) {
        isSFXSwitch = internalSwitch;
    }

    public static boolean getSFXSwitch() {
        return isSFXSwitch;
    }
}

