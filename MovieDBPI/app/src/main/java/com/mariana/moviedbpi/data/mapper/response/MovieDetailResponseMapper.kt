package com.mariana.moviedbpi.data.mapper.response

import com.mariana.moviedbpi.data.model.response.MovieDetailResponse
import com.mariana.moviedbpi.domain.entity.MovieDetail

class MovieDetailResponseMapper(
    private val genresMapper: GenresResponseMapper = GenresResponseMapper(),

    ) {
    fun mapMovie(movieDetailResponse : MovieDetailResponse) = MovieDetail(
        movieID = movieDetailResponse.movieID,
        posterPath = movieDetailResponse.posterPath,
        genres = movieDetailResponse.genres?.map { genresMapper.mapGenres(it) },
        title = movieDetailResponse.title,
        releaseDate = movieDetailResponse.releaseDate,
        userRating = movieDetailResponse.userRating,
        runtime = movieDetailResponse.runtime,
        overview = movieDetailResponse.overview,
        isFavorite = true )
}