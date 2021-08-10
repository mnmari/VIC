package com.mariana.moviedbpi.data.model.response

import com.google.gson.annotations.SerializedName

data class MovieCastResponse(
    @SerializedName("id")
    val id: Int,

    @SerializedName("cast")
    val cast: List<CastResponse>
)

data class CastResponse(
    @SerializedName("name")
    val name: String,

    @SerializedName("character")
    val role: String,
    
    @SerializedName("profile_path")
    val profilePicturePath: String? = ""
)
