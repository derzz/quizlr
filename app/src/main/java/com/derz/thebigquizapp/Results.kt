package com.derz.thebigquizapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button

class Results : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        setContentView(R.layout.results)

        val tryAgain = findViewById<Button>(R.id.resultsTryAgainButton)
        tryAgain.setOnClickListener {

        }

        val backToTopics = findViewById<Button>(R.id.resultsBackToTopicsButton)
        backToTopics.setOnClickListener {
            finish()
        }

        val backToMenu = findViewById<Button>(R.id.resultsBackToMenuButton)
        backToMenu.setOnClickListener {
            finish()
        }
    }
}