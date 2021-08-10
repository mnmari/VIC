package com.mariana.moviedbpi.data.model.response

import com.google.gson.annotations.SerializedName

data class ReleaseDatesResponse(
    @SerializedName("id")
    val id: Int,

    @SerializedName("results")
    val response: List<RatingByCountryResponse>
)

data class RatingByCountryResponse(
    @SerializedName("iso_3166_1")
    val countryCode: String,

    @SerializedName("release_dates")
    val releaseDates: List<RatingResponse>
)

data class RatingResponse(
    @SerializedName("certification")
    val movieRating: String
)
