package com.mariana.minhaagenda

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import showAgenda
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var btnRegister : Button
    private var edtSearch : EditText? = null
    private lateinit var btnSearch : Button
    private lateinit var btnShowAllContacts : Button
    private lateinit var rvOutput : RecyclerView
    private lateinit var agendaAdapter : AgendaAdapter
//    private lateinit var txtOutput : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bindViews()
        addContact()

        btnSearch.setOnClickListener {
            onButtonSearchClick()
        }

        btnShowAllContacts.setOnClickListener {
            onButtonShowAllContactsClick()
        }

        btnRegister.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }
    }

    companion object {
        var contactList = mutableListOf<Agenda>()
        var strContactList = mutableListOf<String>()
        var strFilteredList = mutableListOf<String>()
    }

    private fun addContact() {

        val contact: Agenda? = intent.extras?.get(SecondActivity.CONTACT) as? Agenda

        contactList?.let {
            if (contact != null) {
                contactList.add(contact)
                contactList.sortBy {it.name}
            }
        }
    }

    private fun bindViews() {

        btnRegister = findViewById(R.id.btnRegister)

        edtSearch = findViewById(R.id.edtSearch)
        btnSearch = findViewById(R.id.btnSearch)

        btnShowAllContacts = findViewById(R.id.btnShowAllContacts)
//        txtOutput = findViewById(R.id.txtOutput)
        rvOutput = findViewById(R.id.rvOutput)

        agendaAdapter = AgendaAdapter(context = this, dataSet = strContactList)
        rvOutput.adapter = agendaAdapter
        rvOutput.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

    }

    private fun onButtonSearchClick() {
        val strEdtSearch = edtSearch?.text.toString()

        if (showFilteredContacts(strEdtSearch))
            agendaAdapter.updateList(strFilteredList)
    }

    private fun onButtonShowAllContactsClick() {
        showContacts()
    }

    private fun showContacts() {
//        var strAllContacts = ""

        contactList.forEach {
            if (it is Personal) {
                strContactList.add(it.showPersonalContacts())
//                strAllContacts += it.showPersonalContacts()
            }
            if (it is Work) {
                strContactList.add(it.showWorkContacts())
//                strAllContacts += it.showWorkContacts()
            }
        }
        agendaAdapter = AgendaAdapter(context = this, dataSet = strContactList)
        rvOutput.adapter = agendaAdapter
        rvOutput.layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
//        txtOutput.text = strAllContacts
    }

    private fun showFilteredContacts(strEdtSearch: String) : Boolean {
        if (strEdtSearch.isNotEmpty()) {
            val contactFound = contactList.filter { it.name.toLowerCase(Locale.ROOT).contains(strEdtSearch) }

            if (contactFound.isNotEmpty()) {
//                var showContact = ""

                contactFound.forEach {
                    if (it is Personal) {
                        strFilteredList.add(it.showPersonalContacts())
//                        showContact += it.showPersonalContacts()
                    }
                    if (it is Work) {
                        strFilteredList.add(it.showWorkContacts())
//                        showContact += it.showWorkContacts()
                    }
                }

                return true
//                txtOutput.text = showContact

            } else {
//                txtOutput.text = ""
                Toast.makeText(this, "Registro não encontrado!", Toast.LENGTH_SHORT).show()
                return false
            }
        }
        return true
    }
}