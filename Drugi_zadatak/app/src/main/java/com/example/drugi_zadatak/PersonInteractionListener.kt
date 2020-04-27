package com.example.drugi_zadatak


interface PersonInteractionListener {
    fun getStatement(id : Int)
    fun removePerson(id : Int)
    fun updatePerson(id : Int)
}