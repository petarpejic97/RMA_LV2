package com.example.prvi_zadatak

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_person.view.*
import java.lang.Exception

class InspiringPersonAdapter(persons: MutableList<InspiringPerson>,
                             personListener : PersonInteractioListener): RecyclerView.Adapter<InspiringPersonHolder>() {

    private val persons: MutableList<InspiringPerson>
    private val personListener : PersonInteractioListener

    init {
        this.persons = mutableListOf()
        this.persons.addAll(persons)
        this.personListener= personListener
    }
    fun refreshData(persons: MutableList<InspiringPerson>) {
        this.persons.clear()
        this.persons.addAll(persons)
        this.notifyDataSetChanged()
    }
    override fun getItemCount(): Int = persons.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InspiringPersonHolder {
        val personView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_person, parent, false)
        val personHolder = InspiringPersonHolder(personView)
        return personHolder
    }
    override fun onBindViewHolder(holder: InspiringPersonHolder, position: Int) {
        val person = persons[position]
        holder.bind(person,personListener)
    }
}
class InspiringPersonHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(
        person: InspiringPerson,
        personListener: PersonInteractioListener
    ) {
        itemView.tvPersonFullname.text = person.fullName
        itemView.tvBirdth.text=person.birth
        itemView.tvDescription.text = person.descripton
        Log.w("SLIKA",person.image)
        Picasso
            .get()
            .load(person.image)
            .into(itemView.imgPerson, object: com.squareup.picasso.Callback {
                override fun onError(e: Exception?) {
                    Log.w("EEEERRRRROOOORRRR",e.toString())
                }

                override fun onSuccess() {
                    //set animations here

                }

            })
        itemView.imgPerson.setOnClickListener{personListener.getStatement(person.id)}

    }
}