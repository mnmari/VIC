package com.mariana.moviedbpi.domain

import android.content.Context
import com.mariana.moviedbpi.data.repository.LocalRepository
import com.mariana.moviedbpi.domain.entity.Movie

class DeleteFavoriteMovieUseCase (
    private val repository: LocalRepository = LocalRepository()
) {
    suspend fun run(movie: Movie) = repository.deleteFavoriteMovie(movie)
}