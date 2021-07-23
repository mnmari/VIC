package com.mariana.moviedbpi.domain

import com.mariana.moviedbpi.data.repository.TMDBRepository

class FetchGenresUseCase (
    private val repository: TMDBRepository = TMDBRepository()
) {
    fun run() = repository.fetchGenresList()
}