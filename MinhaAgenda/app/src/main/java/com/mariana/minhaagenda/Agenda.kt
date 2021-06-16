package com.mariana.minhaagenda

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
open class Agenda(open var name: String,
                  open var phoneNumber: String) : Parcelable {

}