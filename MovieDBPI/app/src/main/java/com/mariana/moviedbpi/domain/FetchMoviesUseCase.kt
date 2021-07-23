package com.mariana.moviedbpi.domain

import com.mariana.moviedbpi.data.repository.TMDBRepository

class FetchMoviesUseCase (
    private val repository: TMDBRepository = TMDBRepository()
) {
    fun run() = repository.fetchMoviesList()
}