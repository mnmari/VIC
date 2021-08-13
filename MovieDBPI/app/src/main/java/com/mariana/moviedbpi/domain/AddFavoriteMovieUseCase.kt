package com.mariana.moviedbpi.domain

import android.content.Context
import com.mariana.moviedbpi.data.repository.LocalRepository
import com.mariana.moviedbpi.domain.entity.Movie
import com.mariana.moviedbpi.domain.entity.MovieDetail

class AddFavoriteMovieUseCase (
    private val repository: LocalRepository = LocalRepository()
) {
    //TODO: separar usecases
    suspend fun fromMovieToMovieDB(movie: Movie) = repository.addFavoriteMovie(movie)
    suspend fun fromMovieDetailToMovieDB(movieDetail: MovieDetail) = repository.addFavoriteMovieOnMovieDetail(movieDetail)
}