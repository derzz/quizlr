package com.derz.thebigquizapp

import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class QuizView : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        setContentView(R.layout.quizview)

        var questionManager = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("questionManager", QuestionManager::class.java)
        } else {
            intent.getParcelableExtra("questionManager")
        }

        val option1Button = findViewById<Button>(R.id.quizOption1Button)
        option1Button.setOnClickListener {

        }

        val option2Button = findViewById<Button>(R.id.quizOption2Button)
        option2Button.setOnClickListener {

        }

        val option3Button = findViewById<Button>(R.id.quizOption3Button)
        option3Button.setOnClickListener {

        }

        val option4Button = findViewById<Button>(R.id.quizOption4Button)
        option4Button.setOnClickListener {

        }
    }
}