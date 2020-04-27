package com.example.drugi_zadatak.Fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import kotlinx.android.synthetic.main.fragment_add_new.view.*
import android.widget.TextView
import android.widget.Toast
import com.example.drugi_zadatak.InspiringPerson
import com.example.drugi_zadatak.MainActivity
import com.example.drugi_zadatak.PeopleRepository
import com.example.drugi_zadatak.R

class FragmentAddNew : Fragment() {

    protected lateinit var rootView: View
    lateinit var btnAdd: Button
    private var mMain: MainActivity? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_add_new, container, false)
        onClickAdd()
        return rootView
    }

    private fun onClickAdd() {
        btnAdd = rootView.findViewById(R.id.btnAdd)

            btnAdd.setOnClickListener{createPerson()}
    }

    private fun createPerson() {
        if(rootView.edFullname.text.toString().isEmpty() || rootView.edBirth.text.toString().isEmpty() || rootView.edDescription.text.toString().isEmpty() ||
            rootView.edLinkImage.text.toString().isEmpty() || rootView.edStatements.text.toString().isEmpty()){
            Toast.makeText(activity,"Some fields are empty !",Toast.LENGTH_LONG).show()
        }
        else{
            val id : Int = PeopleRepository.persons.size+1
            val fullname : String = rootView.edFullname.text.toString()
            val birth : String = rootView.edBirth.text.toString()
            val descripton : String = rootView.edDescription.text.toString()
            val imglink : String = rootView.edLinkImage.text.toString()
            val statements = rootView.edStatements.text.toString().split(",").toTypedArray()

            val person = InspiringPerson(id, imglink, fullname, birth, descripton, statements)

            addPersonInList(person)
        }
    }

    private fun addPersonInList(person: InspiringPerson) {

        PeopleRepository.add(person)

        mMain?.buttonClicked("send this to FragmentB.");
        //ovdje ponovno ucitavam activity, promjene se dogode
        //val frag=inspirin_person()
        //val addnewIntent : Intent = Intent(activity, MainActivity::class.java)
        //startActivity(addnewIntent)
    }


    override fun onAttach(a: Context) {
        super.onAttach(a)
        mMain = a as MainActivity
    }

    override fun onDetach() {
        super.onDetach()
    }

}
