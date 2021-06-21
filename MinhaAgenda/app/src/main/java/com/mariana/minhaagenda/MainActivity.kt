package com.mariana.minhaagenda

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var btnRegister : Button
    private var edtSearch : EditText? = null
    private lateinit var btnSearch : Button
    private lateinit var btnShowAllContacts : Button
    private lateinit var txtOutput : TextView

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
        txtOutput = findViewById(R.id.txtOutput)
    }

    private fun onButtonSearchClick() {
        val strEdtSearch = edtSearch?.text.toString()

        showFilteredContacts(strEdtSearch)
    }

    private fun onButtonShowAllContactsClick() {
        showContacts()
    }

    private fun showContacts() {
        var strAllContacts = ""

        contactList.forEach {
            if (it is Personal)
                strAllContacts += it.showPersonalContacts()
            if (it is Work)
                strAllContacts += it.showWorkContacts()
        }
        txtOutput.text = strAllContacts
    }

    private fun showFilteredContacts(strEdtSearch: String) {
        if (strEdtSearch.isNotEmpty()) {
            val contactFound = contactList.filter { it.name.toLowerCase(Locale.ROOT).contains(strEdtSearch) }

            if (contactFound.isNotEmpty()) {
                var showContact = ""

                contactFound.forEach {
                    if (it is Personal)
                        showContact += it.showPersonalContacts()

                    if (it is Work)
                        showContact += it.showWorkContacts()
                }

                txtOutput.text = showContact

            } else {
                txtOutput.text = ""
                Toast.makeText(this, "Registro não encontrado!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}