package com.mariana.moviedbpi.data.model.response

import com.google.gson.annotations.SerializedName

data class AllGenresResponse(
    @SerializedName("genres")
    val genres: List<GenresResponse>
)

data class GenresResponse(
    @SerializedName("id")
    val genreID: Int,

    @SerializedName("name")
    val name: String,
)
