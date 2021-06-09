package com.mariana.minhaagenda

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.PhoneNumberFormattingTextWatcher
import android.text.InputType
import android.view.View
import android.widget.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private var edtName : EditText? = null
    private var edtPhoneNumber : EditText? = null
    private lateinit var rdgContactType : RadioGroup
    private var edtContactType : EditText? = null
    private lateinit var btnSave : Button
    private var edtSearch : EditText? = null
    private lateinit var btnSearch : Button
    private lateinit var btnShowAllContacts : Button
    private lateinit var txtOutput : TextView

    private var contactList = mutableListOf<Agenda>()

    private var selectedOptionOnRdg : Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bindViews()

        btnSave.setOnClickListener {
            onButtonSaveClick()
        }

        btnSearch.setOnClickListener {
            onButtonSearchClick()
        }

        btnShowAllContacts.setOnClickListener {
            onButtonShowAllContactsClick()
        }
    }

    private fun bindViews() {
        edtName = findViewById(R.id.edtName)
        edtPhoneNumber = findViewById(R.id.edtPhoneNumber)
        edtPhoneNumber?.addTextChangedListener(PhoneNumberFormattingTextWatcher("+55"))

        rdgContactType = findViewById(R.id.rdgContactType)

        edtContactType = findViewById(R.id.edtContactType)
        btnSave = findViewById(R.id.btnSave)

        edtSearch = findViewById(R.id.edtSearch)
        btnSearch = findViewById(R.id.btnSearch)

        btnShowAllContacts = findViewById(R.id.btnShowAllContacts)
        txtOutput = findViewById(R.id.txtOutput)
    }

    fun onRadioButtonClick(view: View) {
        if (view is RadioButton){

            val isChecked = view.isChecked

            when (view.id) {
                R.id.rdPersonal ->
                    if (isChecked) {
                        edtContactType?.inputType = InputType.TYPE_CLASS_TEXT
                        edtContactType?.hint = "Referência"
                        selectedOptionOnRdg = 1
                    }
                R.id.rdWork ->
                    if (isChecked) {
                        edtContactType?.inputType = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
                        edtContactType?.hint = "E-mail"
                        selectedOptionOnRdg = 2
                    }
            }
        }
    }

    private fun onButtonSaveClick() {
        val strEdtName = edtName?.text.toString()
        val strEdtPhoneNumber = edtPhoneNumber?.text.toString()
        val strContactType = edtContactType?.text.toString()

        if (selectedOptionOnRdg == 1 && strEdtName.isNotEmpty() && strEdtPhoneNumber.isNotEmpty() && strContactType.isNotEmpty()) {
            val personalReference = Personal(strEdtName, strEdtPhoneNumber, strContactType)
            contactList.add(personalReference)
            contactList.sortBy { it.name }
            showContacts()
        }

        else if (selectedOptionOnRdg == 2 && strEdtName.isNotEmpty() && strEdtPhoneNumber.isNotEmpty() && strContactType.isNotEmpty()) {
            val workEmail = Work(strEdtName, strEdtPhoneNumber, strContactType)
            contactList.add(workEmail)
            contactList.sortBy { it.name }
            showContacts()
        }

        else {
            Toast.makeText(this, "Por favor, preencha todos os campos.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun onButtonSearchClick() {
        val strEdtSearch = edtSearch?.text.toString()

        showFilteredContacts(strEdtSearch)
    }

    private fun onButtonShowAllContactsClick() {
        showContacts()
    }

    private fun showContacts() {
        var showContactList = ""

        contactList.forEach {
            if (it is Personal)
                showContactList += it.name + "\n" + it.phoneNumber + "\n" + it.reference + "\n\n"

            if (it is Work)
                showContactList += it.name + "\n" + it.phoneNumber + "\n" + it.email + "\n\n"
        }
        txtOutput.text = showContactList
    }

    private fun showFilteredContacts(strEdtSearch: String) {
        if (strEdtSearch.isNotEmpty()) {
            val contactFound = contactList.filter { it.name.toLowerCase(Locale.ROOT).contains(strEdtSearch) }

            if (contactFound.isNotEmpty()) {
                var showContact = ""

                contactFound.forEach {
                    if (it is Personal)
                        showContact += it.name + "\n" + it.phoneNumber + "\n" + it.reference + "\n\n"

                    if (it is Work)
                        showContact += it.name + "\n" + it.phoneNumber + "\n" + it.email + "\n\n"
                }

                txtOutput.text = showContact

            } else {
                txtOutput.text = ""
                Toast.makeText(this, "Registro não encontrado!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}