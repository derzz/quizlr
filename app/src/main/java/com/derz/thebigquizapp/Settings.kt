package com.derz.thebigquizapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.Button
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import android.content.SharedPreferences
import android.preference.PreferenceManager



class Settings : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        setContentView(R.layout.settings)

        val backButton = findViewById<Button>(R.id.settingsBackButton)
        backButton.setOnClickListener {
            finish()
        }

        val musicSwitch = findViewById<SwitchCompat>(R.id.musicSwitch)
        musicSwitch.isChecked = app_class.getMusicSwitch();

        val sfxSwitch = findViewById<SwitchCompat>(R.id.sfxSwitch)

        val intent = Intent("status")

        musicSwitch.setOnCheckedChangeListener { _: CompoundButton, isChecked: Boolean ->
            val editor = sharedPreferences.edit()
            editor.putBoolean("musicState", isChecked)
            editor.apply()

            if (isChecked) {
                Intent(this, Music::class.java).also { music ->
                    Log.d("TEST", "Starting music service!")
                    intent.putExtra("status", "1")
                    LocalBroadcastManager.getInstance(this).sendBroadcast(intent)
                    app_class.setMusicSwitch(true);
                }
            } else {
                Intent(this, Music::class.java).also { music ->
                    Log.d("TEST", "Stopping music service!")
                    intent.putExtra("status", "0")
                    LocalBroadcastManager.getInstance(this).sendBroadcast(intent)
                    app_class.setMusicSwitch(false);
                }
            }
        }

    }

}