package com.example.drugi_zadatak.Fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.util.Log
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

class FragmentAddNew : Fragment(),MainActivity.OnLongTouch {
    protected lateinit var rootView: View
    lateinit var btnAdd: Button
    private var mMain: MainActivity? = null
    var updateFlag = false
    var updatePersonId = 0

    override fun setFieldsForUpdate(id:Int){
        fillFields(id)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_add_new, container, false)
        onClickAdd()
        mMain?.setOnUpdating(this)
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
            val fullname : String = rootView.edFullname.text.toString()
            val birth : String = rootView.edBirth.text.toString()
            val descripton : String = rootView.edDescription.text.toString()
            val imglink : String = rootView.edLinkImage.text.toString()
            val statements = rootView.edStatements.text.toString().split("|").toTypedArray()


            if(updateFlag){
                val person = InspiringPerson(updatePersonId, imglink, fullname, birth, descripton, statements)
                addPersonInList(person)
                updatePersonId = 0
            }
            else{
                val id : Int = PeopleRepository.persons.size+1
                val person = InspiringPerson(id, imglink, fullname, birth, descripton, statements)
                addPersonInList(person)
            }

        }
    }

    private fun fillFields(id : Int){
        val person = PeopleRepository.get(id)
        updatePersonId = id
        Log.w("ID",id.toString())
        if(id != 0){
            var statements = ""
            for(i in 0 until person?.statements?.size!!){
                if(i == 0)
                    statements+=person.statements[i]
                else
                    statements+=","+person.statements[i]
            }
            rootView.edFullname.text = Editable.Factory.getInstance().newEditable(person.fullName)
            rootView.edBirth.text = Editable.Factory.getInstance().newEditable(person.birth)
            rootView.edDescription.text = Editable.Factory.getInstance().newEditable(person.descripton)
            rootView.edLinkImage.text = Editable.Factory.getInstance().newEditable(person.image)
            rootView.edStatements.text = Editable.Factory.getInstance().newEditable(statements)

            updateFlag = true
        }
        else return
    }
    private fun addPersonInList(person: InspiringPerson) {

        if(!updateFlag)
            PeopleRepository.add(person)
        else {
            updateFlag = false
            PeopleRepository.update(person)
        }
        mMain?.refreshData()
    }


    override fun onAttach(a: Context) {
        super.onAttach(a)
        mMain = a as MainActivity
    }

    override fun onDetach() {
        super.onDetach()
    }

}
