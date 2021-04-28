package com.example.proba

abstract class User {
    init {
       fart()
    }

    lateinit var name: String

    abstract fun fart()

    fun whatIsYourName(): String {
        fart()
        return name
    }

    fun howMuchLettersInYourName(): Int {
        return name.length
    }

}