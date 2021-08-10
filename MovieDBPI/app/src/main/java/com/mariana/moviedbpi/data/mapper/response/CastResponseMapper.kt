package com.mariana.moviedbpi.data.mapper.response

import com.mariana.moviedbpi.data.model.response.CastResponse
import com.mariana.moviedbpi.data.model.response.MovieCastResponse
import com.mariana.moviedbpi.domain.entity.Cast
import com.mariana.moviedbpi.domain.entity.MovieCast

class CastResponseMapper {
    fun mapMovieCast(movieCastResponse : MovieCastResponse) = MovieCast(
        id = movieCastResponse.id,
        cast = movieCastResponse.cast.map { mapCast(it) }
    )

    fun mapCast(castResponse : CastResponse) = Cast(
        name = castResponse.name,
        role = castResponse.role,
        profilePicturePath = castResponse.profilePicturePath)
}