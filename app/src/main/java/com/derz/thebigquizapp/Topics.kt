package com.derz.thebigquizapp

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class Topics : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.topics)

        val backButton = findViewById<Button>(R.id.topicsBackButton)
        backButton.setOnClickListener {
            finish()
        }
    }
}