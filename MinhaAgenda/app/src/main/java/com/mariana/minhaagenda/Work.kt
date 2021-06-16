package com.mariana.minhaagenda

import kotlinx.android.parcel.Parcelize

@Parcelize
class Work(
    var personalName: String,
    var personalPhoneNumber: String,
    var email: String
) :
    Agenda(personalName, personalPhoneNumber){

    fun showWorkContacts() : String {
        val strAllContacts = " - " + name + "\n" + " - " + phoneNumber + "\n" + " - " + email + "\n\n"
        return strAllContacts
    }

    }