package com.derz.thebigquizapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.Queue

class QuizView : AppCompatActivity(), View.OnClickListener {

    private lateinit var question: TextView
    private lateinit var option1Button: Button
    private lateinit var option2Button: Button
    private lateinit var option3Button: Button
    private lateinit var option4Button: Button
    private lateinit var nextButton: Button
    private lateinit var answerCounter: TextView
    private lateinit var questionManager: QuestionManager
    private lateinit var questionQueue: Queue<Question>
    private var wrong = 0
    private var counter = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        setContentView(R.layout.quizview)

        question = findViewById<TextView>(R.id.quizQuestionText)
        option1Button = findViewById<Button>(R.id.quizOption1Button)
        option2Button = findViewById<Button>(R.id.quizOption2Button)
        option3Button = findViewById<Button>(R.id.quizOption3Button)
        option4Button = findViewById<Button>(R.id.quizOption4Button)
        nextButton = findViewById<Button>(R.id.quizNextButton)
        nextButton.visibility = View.INVISIBLE

        // Sets the click listener for the 4 option buttons
        option1Button.setOnClickListener(this)
        option2Button.setOnClickListener(this)
        option3Button.setOnClickListener(this)
        option4Button.setOnClickListener(this)

        // Loads the questions in from the queue
        questionManager = intent.getParcelableExtra<QuestionManager>("questionManager")!!
        questionQueue = questionManager.getQuestionQueue()

        // Takes the first question in the queue
        val tempQuestion = questionQueue.peek()

        // Sets the text for the question text field and the option button text fields
        if (tempQuestion != null) {
            question.text = tempQuestion.getQuestion()
            option1Button.text = tempQuestion.getAnswers()[0].getAnswer()
            option2Button.text = tempQuestion.getAnswers()[1].getAnswer()
            option3Button.text = tempQuestion.getAnswers()[2].getAnswer()
            option4Button.text = tempQuestion.getAnswers()[3].getAnswer()
        }
    }


    fun nextQuestion(v: View?) {
        counter++

        if (counter <= 10) {
            answerCounter = findViewById<TextView>(R.id.quizAnswerCounter)
            answerCounter.text = "$counter/10"
            nextButton.visibility = View.INVISIBLE
            questionQueue.remove()

            val tempQuestion = questionQueue.peek()
            question.text = tempQuestion?.getQuestion()
            if (tempQuestion != null) {
                option1Button.text = tempQuestion.getAnswers()[0].getAnswer()
                option2Button.text = tempQuestion.getAnswers()[1].getAnswer()
                option3Button.text = tempQuestion.getAnswers()[2].getAnswer()
                option4Button.text = tempQuestion.getAnswers()[3].getAnswer()

                enableOptionButtons()
            }
        } else {
            val results = Intent(this, Results::class.java)
            results.putExtra("wrong", wrong)
            startActivity(results)
        }
    }

    override fun onClick(v: View?) {
        var correct = false
        var clicked = false
        nextButton.visibility = View.VISIBLE

        val option = v?.id

        if (option == R.id.quizOption1Button || option == R.id.quizOption2Button || option == R.id.quizOption3Button || option == R.id.quizOption4Button) {
            disableOptionButtons()
        } else if (option == R.id.quizNextButton) {
            enableOptionButtons()
        }
    }

    private fun enableOptionButtons() {
        option1Button.isEnabled = true
        option2Button.isEnabled = true
        option3Button.isEnabled = true
        option4Button.isEnabled = true
    }

    private fun disableOptionButtons() {
        option1Button.isEnabled = false
        option2Button.isEnabled = false
        option3Button.isEnabled = false
        option4Button.isEnabled = false
    }
}

