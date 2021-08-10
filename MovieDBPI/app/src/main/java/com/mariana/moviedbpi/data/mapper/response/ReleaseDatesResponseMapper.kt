package com.mariana.moviedbpi.data.mapper.response

import com.mariana.moviedbpi.data.model.response.RatingByCountryResponse
import com.mariana.moviedbpi.data.model.response.RatingResponse
import com.mariana.moviedbpi.data.model.response.ReleaseDatesResponse
import com.mariana.moviedbpi.domain.entity.*

class ReleaseDatesResponseMapper {
    fun mapReleaseDates(releaseDatesResponse: ReleaseDatesResponse) = ReleaseDates(
        id = releaseDatesResponse.id,
        response = releaseDatesResponse.response.map { mapRatingByCountry(it) }
    )

    fun mapRatingByCountry(ratingByCountryResponse: RatingByCountryResponse) = RatingByCountry(
        countryCode = ratingByCountryResponse.countryCode,
        releaseDates = ratingByCountryResponse.releaseDates.map { mapRating(it) }
    )

    fun mapRating(ratingResponse : RatingResponse) = Rating(
         movieRating = ratingResponse.movieRating
    )
}