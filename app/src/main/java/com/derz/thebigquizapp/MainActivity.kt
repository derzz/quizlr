package com.derz.thebigquizapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        setContentView(R.layout.activity_main)

        val topicClick = findViewById<Button>(R.id.titleStartButton)
        topicClick.setOnClickListener{
            val intent = Intent(this, Topics::class.java)
            startActivity(intent)
        }

        val instructionClick = findViewById<Button>(R.id.titleInstructionButton)
        instructionClick.setOnClickListener{
            val intent = Intent(this, Instructions::class.java)
            startActivity(intent)
        }

        val settingsClick = findViewById<Button>(R.id.titleSettingsButton)
        settingsClick.setOnClickListener{
            val intent = Intent(this, Settings::class.java)
            startActivity(intent)
        }
    }




}