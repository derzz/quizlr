package com.derz.thebigquizapp

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class Instructions : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.instructions)

        val buttonClick = findViewById<Button>(R.id.instructionsBackButton)
        buttonClick.setOnClickListener{
            finish()
        }
    }
}