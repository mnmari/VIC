package com.mariana.moviedbpi.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AllGenres(
    @SerializedName("genres")
    val genres: List<Genres>
) : Parcelable

@Parcelize
data class Genres(

    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String,

    var flagOnClicked: Boolean
) : Parcelable
