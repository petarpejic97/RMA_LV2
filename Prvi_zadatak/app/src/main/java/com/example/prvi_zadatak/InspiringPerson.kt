package com.example.prvi_zadatak

import java.lang.Math.random

data class InspiringPerson(
    val id : Int = 0,
    val image : String,
    val fullName : String,
    val birth : String,
    val descripton : String,
    val statements : Array<String>){

    fun printInfo() : String{
        val length = statements.size-1
        val number= (0..length).random()
        return statements[number]
    }
}