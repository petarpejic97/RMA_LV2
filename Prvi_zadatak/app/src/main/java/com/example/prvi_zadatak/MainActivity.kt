package com.example.prvi_zadatak

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.os.persistableBundleOf
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_person.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpUi()
        onButtonClick()
    }
    private fun setUpUi() {
        peopleDisplay.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        //peopleDisplay.itemAnimator = DefaultItemAnimator()
        peopleDisplay.addItemDecoration(DividerItemDecoration(this,RecyclerView.VERTICAL))
        displayData()
    }
    private fun displayData() {
        val personListener = object : PersonInteractioListener{
            override fun getStatement(id: Int) {
                val person = PeopleRepository.get(id)
                if (person != null) {
                    Toast.makeText(applicationContext,person.printInfo(),Toast.LENGTH_LONG).show()
                }
            }

        }
        peopleDisplay.adapter = InspiringPersonAdapter(PeopleRepository.persons,personListener)

    }
    private fun onButtonClick(){
        btnAddNewPerson.setOnClickListener{navigateToAddNew()}
    }

    private fun navigateToAddNew(){
        val addnewIntent : Intent = Intent(this, NewPerson::class.java)
        startActivity(addnewIntent)
    }
}
