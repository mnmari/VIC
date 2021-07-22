package com.mariana.moviedbpi.data.repository

import com.mariana.moviedbpi.data.model.AllGenres
import com.mariana.moviedbpi.data.model.PopularMovies
import io.reactivex.Observable
import retrofit2.http.GET

interface TMDBService {

    @GET("movie/popular")
    fun getAllMovies(): Observable<PopularMovies>

    @GET("genre/movie/list")
    fun getAllGenres(): Observable<AllGenres>

    //@GET("movie/popular/:id")
    //cria função
}