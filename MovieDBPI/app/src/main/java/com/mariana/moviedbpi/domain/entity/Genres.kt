package com.mariana.moviedbpi.domain.entity

data class AllGenres(
    val genres: List<Genres>
)

data class Genres(
    val genreID: Int,
    val name: String,
)
