package com.mariana.moviedbpi.data.model

import com.google.gson.annotations.SerializedName

data class AllGenres(
    @SerializedName("genres")
    val genres: List<Genres>
)

data class Genres(

    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String,

    var flagOnClicked: Boolean
)
