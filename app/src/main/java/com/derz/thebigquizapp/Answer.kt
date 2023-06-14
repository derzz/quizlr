package com.derz.thebigquizapp

data class Answer(
    private var answer: String,
    private var isCorrectAnswer: Boolean
) {
    // Getter functions
    fun getAnswer(): String {
        return this.answer
    }

    fun getIsCorrectAnswer(): Boolean {
        return this.isCorrectAnswer
    }
}