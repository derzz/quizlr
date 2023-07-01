package com.derz.thebigquizapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
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
    private lateinit var tempQuestion: Question
    private var wrongCounter: Int = 0
    private var questionCounter: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        setContentView(R.layout.quizview)

        wrongCounter = 0
        questionCounter = 1

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
        tempQuestion = questionQueue.peek()

        // Sets the text for the question text field and the option button text fields
        question.text = tempQuestion.getQuestion()
        option1Button.text = tempQuestion.getAnswers()[0].getAnswer()
        option2Button.text = tempQuestion.getAnswers()[1].getAnswer()
        option3Button.text = tempQuestion.getAnswers()[2].getAnswer()
        option4Button.text = tempQuestion.getAnswers()[3].getAnswer()
    }


    fun nextQuestion(v: View?) {
        questionCounter++

        if (questionCounter <= 10) {
            answerCounter = findViewById<TextView>(R.id.quizAnswerCounter)
            answerCounter.text = "$questionCounter/10"
            nextButton.visibility = View.INVISIBLE
            questionQueue.remove()

            tempQuestion = questionQueue.peek()
            question.text = tempQuestion?.getQuestion()
            resetButtonColors()

            option1Button.text = tempQuestion.getAnswers()[0].getAnswer()
            option2Button.text = tempQuestion.getAnswers()[1].getAnswer()
            option3Button.text = tempQuestion.getAnswers()[2].getAnswer()
            option4Button.text = tempQuestion.getAnswers()[3].getAnswer()
            enableOptionButtons()
        } else {
            finish()
            val results = Intent(this, Results::class.java)
            results.putExtra("wrong", wrongCounter)
            results.putExtra("topicName", intent.getStringExtra("topicName"))
            startActivity(results)
        }
    }

    override fun onClick(v: View?) {
        val option = v?.id

        if (option == R.id.quizOption1Button || option == R.id.quizOption2Button || option == R.id.quizOption3Button || option == R.id.quizOption4Button) {
            disableOptionButtons()

            setButtonColor(option1Button, tempQuestion.getAnswers()[0].getCorrectAnswer())
            setButtonColor(option2Button, tempQuestion.getAnswers()[1].getCorrectAnswer())
            setButtonColor(option3Button, tempQuestion.getAnswers()[2].getCorrectAnswer())
            setButtonColor(option4Button, tempQuestion.getAnswers()[3].getCorrectAnswer())
            nextButton.visibility = View.VISIBLE
        } else if (option == R.id.quizNextButton) {
            enableOptionButtons()
        }
        updateWrongCounter(v)
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

    private fun setButtonColor(button: Button, isCorrect: Boolean) {
        val colorResId = if (isCorrect) {
            R.color.generalCorrectColour
        } else {
            R.color.generalWrongColour
        }

        val color = ContextCompat.getColor(this, colorResId)
        button.setBackgroundColor(color)
    }

    private fun resetButtonColors() {
        val defaultColor = ContextCompat.getColor(this, R.color.generalDefaultColour)
        option1Button.setBackgroundColor(defaultColor)
        option2Button.setBackgroundColor(defaultColor)
        option3Button.setBackgroundColor(defaultColor)
        option4Button.setBackgroundColor(defaultColor)
    }

    private fun updateWrongCounter(v: View?) {
        val option = v?.id
        val selectedAnswer = tempQuestion.getAnswers().find { it.getCorrectAnswer() }?.getAnswer()
        val clickedAnswer = when (option) {
            R.id.quizOption1Button -> option1Button.text.toString()
            R.id.quizOption2Button -> option2Button.text.toString()
            R.id.quizOption3Button -> option3Button.text.toString()
            R.id.quizOption4Button -> option4Button.text.toString()
            else -> null
        }
        if (selectedAnswer != clickedAnswer) {
            wrongCounter++
        }
    }
}

