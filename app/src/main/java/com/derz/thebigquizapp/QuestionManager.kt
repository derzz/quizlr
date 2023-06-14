package com.derz.thebigquizapp

import java.io.BufferedReader
import java.util.LinkedList
import java.util.Queue
import java.io.InputStreamReader

class QuestionManager {

    private var questionList: MutableList<Question> = arrayListOf()
    private var questionQueue: Queue<Question> = LinkedList<Question>()

    fun fillQuestionList(inputStreamReader: InputStreamReader) {
        // Read TSV file
        val reader = BufferedReader(inputStreamReader)
        var line: String = ""
        while (reader.readLine().also {line = it} != null) {
            val row: List<String> = line.split("\t")
            var answers: MutableList<Answer> = arrayListOf()

            // Add answers to question answer list
            for (i in 1 until 5) {
                var tempAnswer: Answer
                if (i == 1) {
                    tempAnswer = Answer(row[i], true)
                } else {
                    tempAnswer = Answer(row[i], false)
                }
                answers.add(tempAnswer)
            }

            // Creates new question and adds it to list of questions
            var tempQuestion = Question(row[0], answers, row[1], false)
            questionList.add(tempQuestion)
        }
    }

    fun pushQuestionsIntoQueue() {
        val queueSize = 10
        var indexList = IntArray(queueSize)

        // Generate list of distinct indices of length queueSize
        for (i in 0 until queueSize) {
            var randomIndex: Int
            do {
                randomIndex = (0 until this.questionList.size).random()
            } while (indexList.contains(randomIndex))
            indexList[i] = randomIndex
        }

        // Push list of distinct questions into question queue
        for (i in 0 until queueSize) {
            var tempQuestion = this.questionList[indexList[i]]
            this.questionQueue.add(tempQuestion)
        }
    }

    // Clear functions
    fun clearQuestionList() {
        this.questionList.clear()
    }

    fun clearQuestionQueue() {
        while (this.questionQueue.isNotEmpty()) {
            this.questionQueue.remove()
        }
    }

    // Getter functions
    fun getQuestionList(): MutableList<Question> {
        return this.questionList
    }

    fun getQuestionQueue(): Queue<Question> {
        return this.questionQueue
    }
}