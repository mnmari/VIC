package com.mariana.minhaagenda

class Contact (var name: String,
               var phoneNumber : String,
               var referenceOrEmail : String) {

    fun setNewContactOnViewFromContactList(contact: Agenda?) {

        if (contact is Personal) {
            name = contact.personalName
            phoneNumber = contact.personalPhoneNumber
            referenceOrEmail = contact.reference
        }

        else if (contact is Work) {
            name = contact.workName
            phoneNumber = contact.workPhoneNumber
            referenceOrEmail = contact.email
        }
    }

    }