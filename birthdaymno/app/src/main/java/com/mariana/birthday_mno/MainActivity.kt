package com.mariana.birthday_mno

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
class MainActivity : AppCompatActivity() {

    lateinit var Date : DatePicker

    var edtName : EditText? = null
    var edtGift : EditText? = null

    lateinit var Evaluate : Button
    lateinit var selectedDate : String
    lateinit var Output : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Date = findViewById(R.id.DatePickerDate)

        edtName = findViewById(R.id.edtName)
        edtGift = findViewById(R.id.edtGift)

        Evaluate = findViewById(R.id.btnEvaluate)
        Output = findViewById(R.id.Output)

        getDateOnDatePicker()

        Evaluate.setOnClickListener(::onClickEvaluate)

    }

    private fun getDateOnDatePicker() {
        val today = Calendar.getInstance()
        Date.init(
            today.get(Calendar.YEAR),
            today.get(Calendar.MONTH),
            today.get(Calendar.DAY_OF_MONTH)
        )
        { _, year, month, dayOfMonth ->
            val correctedMonth = month + 1
            selectedDate = String.format("%4d-%02d-%2d", year, correctedMonth, dayOfMonth)
        }
    }

    private fun onClickEvaluate(view: View) {
        val name = edtName?.text.toString()
        val gift = edtGift?.text.toString()

        val strCurrentDate = getCurrentDateString()

        val birthdayDate = LocalDate.parse(selectedDate, DateTimeFormatter.ISO_DATE)
        val currentDate = LocalDate.parse(strCurrentDate, DateTimeFormatter.ISO_DATE)

        val strBirthdayDateCurrentYear = String.format("%4d-%02d-%2d", currentDate.year, birthdayDate.monthValue, birthdayDate.dayOfMonth)
        val birthdayDateCurrentYear = LocalDate.parse(strBirthdayDateCurrentYear, DateTimeFormatter.ISO_DATE)

        var difference = currentDate.until(birthdayDateCurrentYear, ChronoUnit.DAYS)

        if (difference < 0)
            difference += 365

        Output.text =
            "Olá $name, falta(m) $difference dia(s) para o seu aniversário! Espero que você ganhe um(a) $gift"
    }

    private fun getCurrentDateString(): String {
        val today = Calendar.getInstance()
        val dayOfMonth: Int = today.get(Calendar.DAY_OF_MONTH)
        val month: Int = today.get(Calendar.MONTH)
        val year: Int = today.get(Calendar.YEAR)

        return String.format("%4d-%02d-%2d", year, month + 1, dayOfMonth)
    }
}