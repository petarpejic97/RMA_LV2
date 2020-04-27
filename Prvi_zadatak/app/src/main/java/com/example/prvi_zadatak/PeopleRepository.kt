package com.example.prvi_zadatak

import android.util.Log

object PeopleRepository {
    val persons: MutableList<InspiringPerson>
    init {
        persons = retrievePeople()
    }
    private fun retrievePeople(): MutableList<InspiringPerson> {
        val billStatements = arrayOf("Don’t compare yourself with anyone in this world…if you do so, you are insulting yourself.",
            "I choose a lazy person to do a hard job. Because a lazy person will find an easy way to do it.",
            "Success is a lousy teacher. It seduces smart people into thinking they can’t lose.",
            "If you are born poor it’s not your mistake, but if you die poor it’s your mistake.",
            "We all need people who will give us feedback. That’s how we improve.")
        val jeffStatements = arrayOf("Life’s too short to hang out with people who aren’t resourceful.",
            "A brand for a company is like a reputation for a person. You earn reputation by trying to do hard things well.",
            "One of the only ways to get out of a tight box is to invent your way out.",
            "Your margin is my opportunity.",
            "“If you don’t understand the details of your business you are going to fail.”")
        val linusStatements = arrayOf("Talk is cheap. Show me the code.",
            "Bad programmers worry about the code. Good programmers worry about data structures and their relationships.",
            "Software is like sex : it's better when it's free..",
            "I like offending people, because I think people who get offended should be offended.",
            "Theory and practice sometimes clash. And when that happens, theory loses.")
        return mutableListOf(
            InspiringPerson(1, "https://www.thefamouspeople.com/profiles/thumbs/jeff-bezos-2.jpg", "Jeff Bezos",
                "January 12, 1964","Jeff Bezos is an American technology entrepreneur and founder of e-commerce giant Amazon.com. " +
                        "Born to Jacklyn Gise and Ted Jorgensen, he was adopted by Miguel Bezos, a Cuban immigrant, after his mother married him.",jeffStatements),
            InspiringPerson(2, "https://www.thefamouspeople.com/profiles/thumbs/bill-gates-4.jpg", "Bill Gates",
                "October 28, 1955","Bill Gates is an American business magnate and computer programmer who is the co-founder of Microsoft," +
                      " the world’s largest PC software company. Since the company’s formation in 1975, Gates has held several positions including those of " +
                        "the chairman, CEO and chief software architect.",billStatements),
            InspiringPerson(3, "https://www.thefamouspeople.com/profiles/thumbs/linus-torvalds-4.jpg", "Linus Torvalds",
                "December 28, 1969","Named by Time magazine as the ‘one of the most influential people in the world’, Linus Torvalds is the " +
                        "creative genius behind the Linux kernel operating system. He is listed 17th on the Time magazines poll of ‘Time 100: " +
                        "The Most Important People of the Century'.",linusStatements)

        )
    }
    fun remove(id: Int) = persons.removeAll{ peop -> peop.id == id }
    fun get(id: Int): InspiringPerson? = persons.find { person -> person.id == id }
    fun add(person: InspiringPerson) {
        persons.add(person)
    }
}