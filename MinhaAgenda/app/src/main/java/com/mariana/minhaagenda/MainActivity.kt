package com.mariana.minhaagenda

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var fabRegister : FloatingActionButton
    private var edtSearch : EditText? = null
    private lateinit var btnSearch : Button
    private lateinit var btnShowAllContacts : Button
    private lateinit var rvOutput : RecyclerView
    private lateinit var agendaAdapter : AgendaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bindViews()
        addContact()

        btnSearch.setOnClickListener {
            viewFilteredList.clear()
            onButtonSearchClick()
        }

        btnShowAllContacts.setOnClickListener {
            onButtonShowAllContactsClick()
        }

        fabRegister.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }
    }

    companion object {
        var contactList = mutableListOf<Agenda>()
        var viewContactList = mutableListOf<Contact>()
        var viewFilteredList = mutableListOf<Contact>()
    }

    private fun bindViews() {

        fabRegister = findViewById(R.id.fabRegister)

        edtSearch = findViewById(R.id.edtSearch)
        btnSearch = findViewById(R.id.btnSearch)

        btnShowAllContacts = findViewById(R.id.btnShowAllContacts)
        rvOutput = findViewById(R.id.rvOutput)
    }

    private fun addContact() {

        val contact: Agenda? = intent.extras?.get(SecondActivity.CONTACT) as? Agenda

        contactList?.let {
            if (contact != null) {
                contactList.add(contact)
                updateContactListOnView(contact)

                contactList.sortBy {it.name}
            }
        }
    }

    private fun updateContactListOnView(contact: Agenda?) {
        viewContactList.add(addNewContactToView(contact))
        viewContactList.sortBy { it.name }
    }

    private fun addNewContactToView(contact: Agenda?) : Contact {
        val newContact = Contact("", "", "")
        newContact.setNewContactOnViewFromContactList(contact)
        return newContact
    }

    private fun onButtonSearchClick() {
        val strEdtSearch = edtSearch?.text.toString()

        if (showFilteredContacts(strEdtSearch))
            agendaAdapter.updateList(viewFilteredList)
    }

    private fun onButtonShowAllContactsClick() {
        showContacts()
    }

    private fun showContacts() {
        agendaAdapter = AgendaAdapter(context = this, dataSet = viewContactList)
        rvOutput.adapter = agendaAdapter
        rvOutput.layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
    }

    private fun showFilteredContacts(strEdtSearch: String) : Boolean {
        if (strEdtSearch.isNotEmpty()) {
            val contactFound = contactList.filter { it.name.toLowerCase(Locale.ROOT).contains(strEdtSearch.toLowerCase()) }

            if (contactFound.isNotEmpty()) {

                contactFound.forEach {
                    viewFilteredList.add(addNewContactToView(it))
                }
                return true

            } else {
                Toast.makeText(this, "Registro não encontrado!", Toast.LENGTH_SHORT).show()
                return false
            }
        }
        return true
    }
}