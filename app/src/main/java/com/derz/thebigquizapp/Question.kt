package com.derz.thebigquizapp

import android.os.Parcel
import android.os.Parcelable

data class Question(
    private val question: String,
    private var answers: MutableList<Answer>,
    private val correctAnswer: String,
    ) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        mutableListOf<Answer>().apply {
            parcel.readTypedList(this, Answer.CREATOR)
        },
        parcel.readString() ?: "") {
    }

    // Randomize answers
    fun randomizeAnswers() {
        this.answers.shuffle()
    }

    // Getter and setter functions
    fun getQuestion(): String {
        return this.question
    }

    fun getAnswers(): MutableList<Answer> {
        return this.answers
    }

    fun getCorrectAnswer(): String {
        return this.correctAnswer
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(question)
        parcel.writeTypedList(answers)
        parcel.writeString(correctAnswer)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Question> {
        override fun createFromParcel(parcel: Parcel): Question {
            return Question(parcel)
        }

        override fun newArray(size: Int): Array<Question?> {
            return arrayOfNulls(size)
        }
    }
}