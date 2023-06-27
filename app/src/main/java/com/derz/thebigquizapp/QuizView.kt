package com.derz.thebigquizapp

import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import android.util.Log

class QuizView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        setContentView(R.layout.quizview)

        /* Checks if the Android SDK version is >= 33, it uses the new getParcelableExtra method.
        If the Android SDK version is < 33, it uses the deprecated version.
        */

        var questionManager = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("questionManager", QuestionManager::class.java)
        } else {
            intent.getParcelableExtra("questionManager")
        }


    }
}