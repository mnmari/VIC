package com.mariana.moviedbpi.domain

import com.mariana.moviedbpi.data.repository.TMDBRepository

class FetchMovieRatingUseCase (
    private val repository: TMDBRepository = TMDBRepository()
) {
    fun run(movieID: Int) = repository.fetchMovieRating(movieID)
}