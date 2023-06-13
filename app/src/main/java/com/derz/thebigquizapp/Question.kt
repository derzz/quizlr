package com.derz.thebigquizapp

data class Question(
    private val question: String,
    private var answers: MutableList<String>,
    private val correctAnswer: String,
    private var isAnswered: Boolean
    ) {

    // Randomize answers
    fun randomizeAnswers() {
        this.answers.shuffle()
    }

    // Getter and setter functions
    fun getQuestion(): String {
        return this.question
    }

    fun getAnswers(): MutableList<String> {
        return this.answers
    }

    fun getCorrectAnswer(): String {
        return this.correctAnswer
    }

    fun getIsAnswered(): Boolean {
        return this.isAnswered
    }

    fun setIsAnswered(isAnswered: Boolean) {
        this.isAnswered = isAnswered
    }
}