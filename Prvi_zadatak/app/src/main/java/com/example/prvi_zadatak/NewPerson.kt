package com.example.prvi_zadatak

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_new_person.*

class NewPerson : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_person)
        onClickAdd()
    }
    private fun onClickAdd(){
        btnAdd.setOnClickListener{createPerson()}
    }

    private fun createPerson(){
        val idString : String = edPersonId.text.toString()
        val id = idString.toInt()
        val fullname : String = edFullname.text.toString()
        val birth : String = edBirth.text.toString()
        val descripton : String = edDescription.text.toString()
        val imglink : String = edLinkImage.text.toString()
        val statements = edStatements.text.toString().split(",").toTypedArray()

        val person = InspiringPerson(id,imglink,fullname,birth,descripton,statements)

        addPersonInList(person)
    }
    private fun addPersonInList(person : InspiringPerson){

        PeopleRepository.add(person)
        val addnewIntent : Intent = Intent(this, MainActivity::class.java)
        startActivity(addnewIntent)
    }
}
