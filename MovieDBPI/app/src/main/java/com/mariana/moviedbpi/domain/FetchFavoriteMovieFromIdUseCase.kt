package com.mariana.moviedbpi.domain

import com.mariana.moviedbpi.data.repository.LocalRepository

class FetchFavoriteMovieFromIdUseCase (
    private val repository: LocalRepository = LocalRepository()
) {
    suspend fun run(id: Int) = repository.getFavoriteMovieFromId(id)
}