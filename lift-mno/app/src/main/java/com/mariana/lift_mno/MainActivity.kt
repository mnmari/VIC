package com.mariana.lift_mno

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var txtLocation2 : TextView
    private lateinit var txtLocation3 : TextView

    private lateinit var txtCurrentPeopleInLift : TextView
    private lateinit var txtTotalPeopleInLift : TextView

    private var edtInputText : EditText? = null
    private lateinit var btnSetFloor : Button
    private lateinit var btnIn : Button
    private lateinit var btnOut : Button

    private val maxNumberOfPeople = 5 // fixo
    private val numberOfFloors = 10 // fixo
    private var numberOfPeople = 0
    private var selectedFloor = 0

    private var lift = Lift(numberOfPeople, maxNumberOfPeople, selectedFloor, numberOfFloors)
    private var option = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bindViews()

        updateFloorViewTextView(selectedFloor, numberOfFloors)
        updateNumberOfPeopleTextView()

        btnSetFloor.setOnClickListener(::onClickBtnSetFloor)

        btnIn.setOnClickListener(::onClickBtnIn)

        btnOut.setOnClickListener(::onClickBtnOut)
    }

    private fun bindViews(){
        txtLocation2 = findViewById(R.id.txtLocation2)
        txtLocation3 = findViewById(R.id.txtLocation3)

        txtCurrentPeopleInLift = findViewById(R.id.txtCurrentPeopleInLift)
        txtTotalPeopleInLift = findViewById(R.id.txtTotalPeopleInLift)

        edtInputText = findViewById(R.id.edtInputText)
        btnSetFloor = findViewById(R.id.btnSetFloor)
        btnIn = findViewById(R.id.btnIn)
        btnOut = findViewById(R.id.btnOut)
    }

    private fun onClickBtnIn(view: View) {
        option = 1
        lift.updateNumberOfPeople(option)
        numberOfPeople = lift.getCurrentPeopleInLift()
        updateNumberOfPeopleTextView()
        Toast.makeText(this, lift.showCurrentPeopleInLift(), Toast.LENGTH_SHORT).show()
    }

    private fun onClickBtnOut(view: View) {
        option = 2
        lift.updateNumberOfPeople(option)
        numberOfPeople = lift.getCurrentPeopleInLift()
        updateNumberOfPeopleTextView()
        Toast.makeText(this, lift.showCurrentPeopleInLift(), Toast.LENGTH_SHORT).show()
    }

    private fun onClickBtnSetFloor(view: View) {
        selectedFloor = edtInputText?.text.toString().toIntOrNull()!!

        updateFloorViewTextView(selectedFloor, numberOfFloors)
        lift.updateCurrentFloor(selectedFloor)
        Toast.makeText(this, lift.validateAndShowCurrentFloor(selectedFloor), Toast.LENGTH_SHORT).show()
    }

    private fun updateNumberOfPeopleTextView() {
        txtCurrentPeopleInLift.text = lift.getCurrentPeopleInLift().toString()
        txtTotalPeopleInLift.text = lift.getMaxNumberOfPeople().toString()
    }

    private fun updateFloorViewTextView(selectedFloor: Int, numberOfFloors: Int) {
        selectedFloor.let {
            if (selectedFloor in 1..numberOfFloors) {
                txtLocation2.text = selectedFloor.toString() + "°"
                txtLocation3.text = "andar"
            } else if (selectedFloor == 0) {
                txtLocation2.text = "Térreo"
                txtLocation3.text = ""
            }
        }
    }
}