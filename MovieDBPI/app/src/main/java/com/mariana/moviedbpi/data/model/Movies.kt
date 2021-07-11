package com.mariana.moviedbpi.data.model

import java.util.*

data class Movies(
    val movie_id: Int,
    val original_title: String,
    val year: String,
    val rating: Int,
    val duration: Date,
    val synposis
)