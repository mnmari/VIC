package com.mariana.moviedbpi.domain.entity

data class ReleaseDates(
    val id: Int,
    val response: List<RatingByCountry>
)

data class RatingByCountry(
    val countryCode: String,
    val releaseDates: List<Rating>
)

data class Rating(
    val movieRating: String
)
