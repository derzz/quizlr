package com.derz.thebigquizapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import java.io.InputStreamReader

class Topics: AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        setContentView(R.layout.topics)

        val uwButton = findViewById<Button>(R.id.topicsUWaterlooButton)
        uwButton.setOnClickListener(this)

        val videoGamesButton = findViewById<Button>(R.id.topicsVideoGamesButton)
        videoGamesButton.setOnClickListener(this)

        val historyButton = findViewById<Button>(R.id.topicsHistoryButton)
        historyButton.setOnClickListener(this)

        val sportsButton = findViewById<Button>(R.id.topicsSportsButton)
        sportsButton.setOnClickListener(this)

        val backButton = findViewById<Button>(R.id.topicsBackButton)
        backButton.setOnClickListener {
            finish()
        }
    }

    override fun onClick(v: View?) {
        var questionManager = QuestionManager()

        var path = when (v?.id) {
            R.id.topicsUWaterlooButton -> "University of Waterloo.tsv"
            R.id.topicsVideoGamesButton -> "Video Games.tsv"
            R.id.topicsHistoryButton -> "History.tsv"
            R.id.topicsSportsButton -> "Sports.tsv"
            else -> throw Exception("Not a valid topic")
        }

        var inputStreamReader = InputStreamReader(assets.open(path))
        questionManager.fillQuestionList(inputStreamReader)
        val intent = Intent(this, QuizView::class.java)
        startActivity(intent)
    }
}