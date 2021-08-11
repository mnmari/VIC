package com.mariana.moviedbpi.domain

import android.net.Uri
import com.mariana.moviedbpi.data.repository.TMDBRepository

class SearchMoviesUseCase (
    private val repository: TMDBRepository = TMDBRepository()
) {
    fun run(query: Uri) = repository.searchForMovies(query)
}