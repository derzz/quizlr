package com.derz.thebigquizapp;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

public class app_class extends Application implements Application.ActivityLifecycleCallbacks {
    private static int resumed;
    private static int paused;

    private static String currentActivity;

    @Override
    public void onCreate() {
        super.onCreate();

        // Register the app_class as a callback for activity lifecycle events
        registerActivityLifecycleCallbacks(this);
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
        send_status(1);
    }

    @Override
    public void onActivityPaused(Activity activity) {
        send_status(0);
    }

    private void send_status(int status_counter) {
        Intent intent = new Intent("status");
        intent.putExtra("status", String.valueOf(status_counter));
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
        send_status(2);
    }
}