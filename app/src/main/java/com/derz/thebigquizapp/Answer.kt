package com.derz.thebigquizapp

import android.os.Parcel
import android.os.Parcelable

data class Answer(
    private var answer: String,
    private var isCorrectAnswer: Boolean
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readByte() != 0.toByte()
    ) {
    }

    // Getter functions
    fun getAnswer(): String {
        return this.answer
    }

    fun getIsCorrectAnswer(): Boolean {
        return this.isCorrectAnswer
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(answer)
        parcel.writeByte(if (isCorrectAnswer) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Answer> {
        override fun createFromParcel(parcel: Parcel): Answer {
            return Answer(parcel)
        }

        override fun newArray(size: Int): Array<Answer?> {
            return arrayOfNulls(size)
        }
    }
}