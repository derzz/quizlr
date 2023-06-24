package com.derz.thebigquizapp

import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class QuizView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        setContentView(R.layout.quizview)

        /* Checks if the Android SDK version is >= 33, it uses the new getParcelableExtra method.
        If the Android SDK version is < 33, it uses the deprecated version.
        */

        // Code is crashing. Temporarily commented out.

//        var questionManager = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
//            intent.getParcelableExtra("questionManager", QuestionManager::class.java)
//        } else {
//            intent.getParcelableExtra("questionManager")
//        }
//        questionManager?.pushQuestionsIntoQueue()

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
        // Transition between questions in QuizView (in progress)

//        var questionManagerQueue = questionManager?.getQuestionQueue()
//        if (questionManagerQueue != null) {
//            while (questionManagerQueue.isNotEmpty()) {
//                val option1Button = findViewById<Button>(R.id.quizOption1Button)
//                option1Button.setOnClickListener(this)
//
//                val option2Button = findViewById<Button>(R.id.quizOption2Button)
//                option2Button.setOnClickListener(this)
//
//                val option3Button = findViewById<Button>(R.id.quizOption3Button)
//                option3Button.setOnClickListener(this)
//
//                val option4Button = findViewById<Button>(R.id.quizOption4Button)
//                option4Button.setOnClickListener(this)
//
//            }
//        }
    }
}