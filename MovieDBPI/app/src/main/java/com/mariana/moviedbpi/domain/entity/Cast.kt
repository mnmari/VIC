package com.mariana.moviedbpi.domain.entity

data class MovieCast(
    val id: Int,
    val cast: List<Cast>
)

data class Cast(
    val name: String,
    val role: String,
    val profilePicturePath: String? = ""
)
