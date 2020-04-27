package com.example.drugi_zadatak

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_update_person.*
import kotlinx.android.synthetic.main.fragment_inspiring_person.*
import android.util.Log.w as w1


class UpdatePerson : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_person)

        var getid = intent?.getStringExtra("id")
        var person : InspiringPerson = PeopleRepository.persons.get((getid)!!.toInt()-1)
        fillEditTexts(person)
        onClickAdd(getid)
    }

    private fun fillEditTexts(person: InspiringPerson) {
        var statements:String =""

        for(i in 0 until person.statements.size){
            if(i == 0)
                statements+=person.statements[i]
            else
                statements+=","+person.statements[i]
        }
        edFullname.setText(person.fullName)
        edBirth.setText(person.birth)
        edDescription.setText(person.descripton)
        edLinkImage.setText(person.image)
        edStatements.setText(statements)
    }

    private fun onClickAdd(getid:String){
        btnAdd.setOnClickListener{createPerson(getid)}
    }
    private fun createPerson(getid:String){
        val idString : String = getid
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

        PeopleRepository.update(person)
        val addnewIntent = Intent(this, MainActivity::class.java)
        startActivity(addnewIntent)
    }
}
