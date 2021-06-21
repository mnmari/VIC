package com.mariana.minhaagenda

import kotlinx.android.parcel.Parcelize

@Parcelize
class Personal(
    var personalName: String,
    var personalPhoneNumber: String,
    var reference: String
) :
    Agenda(personalName, personalPhoneNumber){

    fun showPersonalContacts() : String {
        val strAllContacts = " - " + name + "\n" + " - " + phoneNumber + "\n" + " - " + reference + "\n\n"
        return strAllContacts
    }

    }