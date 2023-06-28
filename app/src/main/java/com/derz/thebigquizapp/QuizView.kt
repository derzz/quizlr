package com.derz.thebigquizapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class QuizView : AppCompatActivity(), View.OnClickListener {

    private lateinit var question: TextView
    private lateinit var option1Button: Button
    private lateinit var option2Button: Button
    private lateinit var option3Button: Button
    private lateinit var option4Button: Button
    private lateinit var questionManager: QuestionManager
    private lateinit var nextButton: Button
    private lateinit var answerCounter: TextView
    var wrong = 0;
    var counter = 0;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        setContentView(R.layout.quizview)

        question = findViewById<TextView>(R.id.quizQuestionText)
        option1Button = findViewById<Button>(R.id.quizOption1Button)
        option2Button = findViewById<Button>(R.id.quizOption2Button)
        option3Button = findViewById<Button>(R.id.quizOption3Button)
        option4Button = findViewById<Button>(R.id.quizOption4Button)
        nextButton = findViewById<Button>(R.id.nextButton)
        nextButton.setVisibility(View.INVISIBLE);

        option1Button.setOnClickListener(this)
        option2Button.setOnClickListener(this)
        option3Button.setOnClickListener(this)
        option4Button.setOnClickListener(this)

        // Loads the questions in from the queue and sets the text for the question and answers
        questionManager = intent.getParcelableExtra<QuestionManager>("questionManager")!!
        questionManager?.pushQuestionsIntoQueue()
        var questionQueue = questionManager.getQuestionQueue()

        var tempQuestion = questionQueue?.peek()
        question.text = tempQuestion?.getQuestion()
        if (tempQuestion != null) {
            option1Button.text = tempQuestion.getAnswers()[0].getAnswer()
            option2Button.text = tempQuestion.getAnswers()[1].getAnswer()
            option3Button.text = tempQuestion.getAnswers()[2].getAnswer()
            option4Button.text = tempQuestion.getAnswers()[3].getAnswer()
        }

    }

    fun nextQuestion(v: View?){
        counter += 1
        if(counter <= 10){
            answerCounter = findViewById<TextView>(R.id.quizAnswerCounter)
            answerCounter.setText(counter.toString() + "/10")
            nextButton.setVisibility(View.INVISIBLE)
            questionManager?.getQuestionQueue()?.remove()
            questionManager = intent.getParcelableExtra<QuestionManager>("questionManager")!!
            questionManager?.pushQuestionsIntoQueue()
            var questionQueue = questionManager.getQuestionQueue()

            var tempQuestion = questionQueue?.peek()
            question.text = tempQuestion?.getQuestion()
            if (tempQuestion != null) {
                option1Button.text = tempQuestion.getAnswers()[0].getAnswer()
                option2Button.text = tempQuestion.getAnswers()[1].getAnswer()
                option3Button.text = tempQuestion.getAnswers()[2].getAnswer()
                option4Button.text = tempQuestion.getAnswers()[3].getAnswer()
            }
        }
        else{
            val results = Intent(this, Results::class.java)
            results.putExtra("wrong", wrong)
            startActivity(results)
        }

    }

    override fun onClick(v: View?) {
        var correct = false;
        var clicked = false;
        nextButton.setVisibility(View.VISIBLE)
        when (v!!.id) {
            /*          R.id.quizOption1Button -> {validateCorrect(option1Button.text)}
                        R.id.quizOption2Button -> {validateCorrect(option2Button.text)}
                        R.id.quizOption3Button -> {validateCorrect(option3Button.text)}
                        R.id.quizOption4Button -> {validateCorrect(option4Button.text)}*/
            else -> {}
        }


    }
}