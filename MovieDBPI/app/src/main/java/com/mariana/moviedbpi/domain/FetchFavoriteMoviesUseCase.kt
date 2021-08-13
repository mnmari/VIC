package com.mariana.moviedbpi.domain

import com.mariana.moviedbpi.data.repository.LocalRepository

class FetchFavoriteMoviesUseCase (
    private val repository: LocalRepository = LocalRepository()
) {
    suspend fun run() = repository.getFavoriteMovies()
}