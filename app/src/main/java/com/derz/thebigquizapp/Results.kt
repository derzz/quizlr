package com.derz.thebigquizapp

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Results : AppCompatActivity() {
    private lateinit var right: TextView
    private lateinit var wrong: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        setContentView(R.layout.results)

        right = findViewById<TextView>(R.id.resultsRightText)
        wrong = findViewById<TextView>(R.id.resultsWrongText)

        var mIntent = getIntent();
        var wrongInt = mIntent.getIntExtra("wrong", 0);

        right.setText("Right: " + (10 - wrongInt).toString())
        wrong.setText("Wrong: " + wrongInt.toString())


        val tryAgain = findViewById<Button>(R.id.resultsTryAgainButton)
        tryAgain.setOnClickListener {
            finish()
            // TODO: "Reset quiz here"
        }

        val backToTopics = findViewById<Button>(R.id.resultsBackToTopicsButton)
        backToTopics.setOnClickListener {
            val intent = Intent(this, Topics::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }

        val backToMenu = findViewById<Button>(R.id.resultsBackToMenuButton)
        backToMenu.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }
    }
}