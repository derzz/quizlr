package com.derz.thebigquizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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