package com.derz.thebigquizapp

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.io.InputStreamReader

class Results : AppCompatActivity() {
    private lateinit var right: TextView
    private lateinit var wrong: TextView
    private lateinit var questionManager: QuestionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        setContentView(R.layout.results)

        right = findViewById<TextView>(R.id.resultsRightText)
        wrong = findViewById<TextView>(R.id.resultsWrongText)

        val mIntent = intent
        val wrongInt = mIntent.getIntExtra("wrong", 0)
        mIntent.getStringExtra("topicName")

        right.text = "Right: " + (10 - wrongInt).toString()
        wrong.text = "Wrong: " + wrongInt.toString()


        val tryAgain = findViewById<Button>(R.id.resultsTryAgainButton)
        tryAgain.setOnClickListener {
            finish()

            questionManager = QuestionManager()
            questionManager.clearQuestionList()
            questionManager.clearQuestionQueue()

            val path: String? = intent.getStringExtra("topicName")
            val inputStreamReader = InputStreamReader(path?.let { it1 -> assets.open(it1) })
            questionManager.fillQuestionList(inputStreamReader)
            questionManager.pushQuestionsIntoQueue()

            val intent = Intent(this, QuizView::class.java)
            intent.putExtra("questionManager", questionManager)
            intent.putExtra("topicName", path)
            startActivity(intent)
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

    override fun onBackPressed() {
        finish()

        questionManager = QuestionManager()
        questionManager.clearQuestionList()
        questionManager.clearQuestionQueue()

        val path: String? = intent.getStringExtra("topicName")
        val inputStreamReader = InputStreamReader(path?.let { it1 -> assets.open(it1) })
        questionManager.fillQuestionList(inputStreamReader)
        questionManager.pushQuestionsIntoQueue()

        val intent = Intent(this, QuizView::class.java)
        intent.putExtra("questionManager", questionManager)
        intent.putExtra("topicName", path)
        startActivity(intent)
    }
}