package com.example.drugi_zadatak.Fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.content.Context.INPUT_METHOD_SERVICE
import android.content.Intent
import androidx.core.content.ContextCompat.getSystemService
import androidx.viewpager.widget.ViewPager
import com.example.drugi_zadatak.*
import kotlinx.android.synthetic.main.item_person.*
import kotlinx.android.synthetic.main.item_person.view.*


class FragmentInspiringPerson : Fragment(), MainActivity.OnButtonClicked {
    protected lateinit var rootView: View
    lateinit var peopleDisplay: RecyclerView
    private var mMain: MainActivity? = null

    override fun submit(s: String?) {
        displayData()
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_inspiring_person, container, false)
        initView()
        mMain = activity as MainActivity?
        mMain?.setOnButtonClicked(this)

        return rootView;
    }
    private fun initView(){
        initializeRecyclerView()
    }

    private fun initializeRecyclerView(){
        peopleDisplay = rootView.findViewById(R.id.peopleDisplay)
        peopleDisplay.layoutManager = LinearLayoutManager(activity)
        peopleDisplay.itemAnimator = DefaultItemAnimator()
        peopleDisplay.addItemDecoration(DividerItemDecoration(activity,RecyclerView.VERTICAL))
        displayData()

    }
    fun displayData() {
        val personListener = object : PersonInteractionListener {
            override fun getStatement(id: Int) {
                val person = PeopleRepository.get(id)
                if (person != null) {
                    Toast.makeText(activity,person.printInfo(), Toast.LENGTH_LONG).show()
                }
            }
            override fun removePerson(id: Int) {
                PeopleRepository.remove(id)
                (peopleDisplay.adapter as InspiringPersonAdapter).refreshData(
                    PeopleRepository.persons
                )
            }

            override fun updatePerson(id: Int) {
                val id = id.toString()
                val addnewIntent : Intent = Intent(activity, UpdatePerson::class.java)
                addnewIntent.putExtra("id", id)
                startActivity(addnewIntent)
            }
        }
        peopleDisplay.adapter = InspiringPersonAdapter(
            PeopleRepository.persons,
            personListener
        )
    }

}
