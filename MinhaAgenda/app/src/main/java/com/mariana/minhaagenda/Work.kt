package com.mariana.minhaagenda

import kotlinx.android.parcel.Parcelize

@Parcelize
class Work(
    var workName: String,
    var workPhoneNumber: String,
    var email: String
) :
    Agenda(workName, workPhoneNumber){

    fun showWorkContacts() : String {
        val strAllContacts = " - " + workName + "\n" + " - " + workPhoneNumber + "\n" + " - " + email + "\n\n"
        return strAllContacts
    }

    }