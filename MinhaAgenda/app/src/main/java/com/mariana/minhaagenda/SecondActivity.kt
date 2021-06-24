package com.mariana.minhaagenda

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.PhoneNumberFormattingTextWatcher
import android.text.InputType
import android.view.View
import android.widget.*

class SecondActivity : AppCompatActivity() {

    private var edtName : EditText? = null
    private var edtPhoneNumber : EditText? = null
    private lateinit var rdgContactType : RadioGroup
    private var edtContactType : EditText? = null
    private lateinit var btnSave : Button
    private lateinit var btnReturn : Button

    private var selectedOptionOnRdg : Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        bindViews()

        btnSave.setOnClickListener {
            onButtonSaveClick()
        }

        btnReturn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }

    private fun bindViews() {
        edtName = findViewById(R.id.edtName)
        edtPhoneNumber = findViewById(R.id.edtPhoneNumber)
        edtPhoneNumber?.addTextChangedListener(PhoneNumberFormattingTextWatcher("+55"))

        rdgContactType = findViewById(R.id.rdgContactType)

        edtContactType = findViewById(R.id.edtContactType)
        btnSave = findViewById(R.id.btnSave)
        btnReturn = findViewById(R.id.btnReturn)
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

    companion object {
        val CONTACT = "Contact"
    }

    private fun onButtonSaveClick() {
        val strEdtName = edtName?.text.toString()
        val strEdtPhoneNumber = edtPhoneNumber?.text.toString()
        val strContactType = edtContactType?.text.toString()

        val intent = Intent(this, MainActivity::class.java)

        if (selectedOptionOnRdg == 1 && strEdtName.isNotEmpty() && strEdtPhoneNumber.isNotEmpty() && strContactType.isNotEmpty()) {
            val personal = Personal(strEdtName, strEdtPhoneNumber, strContactType)
            intent.putExtra(CONTACT, personal)
            Toast.makeText(this, "Cadastro efetuado com sucesso!", Toast.LENGTH_SHORT).show()
            startActivity(intent)
        }

        else if (selectedOptionOnRdg == 2 && strEdtName.isNotEmpty() && strEdtPhoneNumber.isNotEmpty() && strContactType.isNotEmpty()) {
            val work = Work(strEdtName, strEdtPhoneNumber, strContactType)
            intent.putExtra(CONTACT, work)
            Toast.makeText(this, "Cadastro efetuado com sucesso!", Toast.LENGTH_SHORT).show()
            startActivity(intent)
        }

        else {
            Toast.makeText(this, "Por favor, preencha todos os campos.", Toast.LENGTH_SHORT).show()
        }
    }
}

