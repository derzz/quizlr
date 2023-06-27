package com.derz.thebigquizapp

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class QuizView : AppCompatActivity(), View.OnClickListener {

    private lateinit var question: TextView
    private lateinit var option1Button: Button
    private lateinit var option2Button: Button
    private lateinit var option3Button: Button
    private lateinit var option4Button: Button
    private lateinit var questionManager: QuestionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        setContentView(R.layout.quizview)

        question = findViewById<TextView>(R.id.quizQuestionText)
        option1Button = findViewById<Button>(R.id.quizOption1Button)
        option2Button = findViewById<Button>(R.id.quizOption2Button)
        option3Button = findViewById<Button>(R.id.quizOption3Button)
        option4Button = findViewById<Button>(R.id.quizOption4Button)

        option1Button.setOnClickListener(this)
        option2Button.setOnClickListener(this)
        option3Button.setOnClickListener(this)
        option4Button.setOnClickListener(this)

        questionManager = intent.getParcelableExtra<QuestionManager>("questionManager")!!
        questionManager?.pushQuestionsIntoQueue()
        var questionQueue = questionManager.getQuestionQueue()

        var tempQuestion = questionQueue?.peek()
        question.text = tempQuestion?.getQuestion()
        if (tempQuestion != null) {
            option1Button.text = tempQuestion.getAnswers()[0].getAnswer()
        }
        if (tempQuestion != null) {
            option2Button.text = tempQuestion.getAnswers()[1].getAnswer()
        }
        if (tempQuestion != null) {
            option3Button.text = tempQuestion.getAnswers()[2].getAnswer()
        }
        if (tempQuestion != null) {
            option4Button.text = tempQuestion.getAnswers()[3].getAnswer()
        }

    }

    override fun onClick(v: View?) {
        TODO("Not yet implemented")
    }
}