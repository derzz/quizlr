package com.derz.thebigquizapp

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat


class Settings : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        setContentView(R.layout.settings)

        val backButton = findViewById<Button>(R.id.settingsBackButton)
        backButton.setOnClickListener {
            finish()
        }

        val musicSwitch = findViewById<SwitchCompat>(R.id.musicSwitch)
        val sfxSwitch = findViewById<SwitchCompat>(R.id.sfxSwitch)



    }

}