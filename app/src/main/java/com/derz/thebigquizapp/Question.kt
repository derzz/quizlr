package com.derz.thebigquizapp

import android.os.Parcel
import android.os.Parcelable

data class Question(
    private val question: String,
    private var answers: MutableList<Answer>,
    private val correctAnswer: String,
    private var isAnswered: Boolean
    ) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        mutableListOf<Answer>().apply {
            parcel.readTypedList(this, Answer.CREATOR)
        },
        parcel.readString() ?: "",
        parcel.readByte() != 0.toByte()
    ) {
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

    fun getIsAnswered(): Boolean {
        return this.isAnswered
    }

    fun setIsAnswered(isAnswered: Boolean) {
        this.isAnswered = isAnswered
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(question)
        parcel.writeTypedList(answers)
        parcel.writeString(correctAnswer)
        parcel.writeByte(if (isAnswered) 1 else 0)
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