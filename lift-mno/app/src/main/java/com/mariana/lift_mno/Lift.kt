package com.mariana.lift_mno

data class Lift(private var currentNumberOfPeople: Int,
                private val maxNumberOfPeople: Int,
                private var currentFloor: Int,
                private val numberOfFloors: Int){

    fun getCurrentPeopleInLift() : Int = currentNumberOfPeople
    fun getMaxNumberOfPeople() : Int = maxNumberOfPeople
    fun showCurrentPeopleInLift() : String = "Há $currentNumberOfPeople pessoa(s) no elevador!"

    fun updateCurrentFloor(floor: Int){
        if (floor in 0..numberOfFloors)
            currentFloor = floor
    }

    fun updateNumberOfPeople(option: Int) {

        if (option == 1 && currentNumberOfPeople < maxNumberOfPeople)
            currentNumberOfPeople += 1

        else if (option == 2 && currentNumberOfPeople > 0)
            currentNumberOfPeople -= 1
    }

    fun validateAndShowCurrentFloor(floor: Int) : String {

        return if (floor < 0 || floor > numberOfFloors)
            "Insira um andar válido!"

        else if (floor == 0)
            "O elevador agora se encontra no térreo!"

        else
            "O elevador agora se encontra no $floor° andar!"
    }
}
