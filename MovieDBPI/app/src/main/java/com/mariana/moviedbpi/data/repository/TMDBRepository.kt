package com.mariana.moviedbpi.data.repository

import com.mariana.moviedbpi.data.model.AllGenres
import com.mariana.moviedbpi.data.model.PopularMovies
import io.reactivex.Observable

class TMDBRepository {

    fun fetchMoviesList() : Observable<PopularMovies> {
        return Network.getService().getAllMovies()
    }

    fun fetchGenresList() : Observable<AllGenres> {
        return Network.getService().getAllGenres()
    }

}