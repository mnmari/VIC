package com.mariana.moviedbpi.data.repository

import android.net.Uri
import com.mariana.moviedbpi.data.model.response.*
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TMDBService {

    @GET("movie/popular")
    fun getPopularMovies(): Observable<PopularMoviesResponse>

    @GET("genre/movie/list")
    fun getAllGenres(): Observable<AllGenresResponse>

    @GET("movie/{movie_id}/credits")
    fun getMovieCast(@Path("movie_id") movieID: Int): Observable<MovieCastResponse>

    @GET("movie/{movie_id}")
    fun getMovieDetail(@Path("movie_id") movieID: Int): Observable<MovieDetailResponse>

    @GET("movie/{movie_id}/release_dates")
    fun getMovieRating(@Path("movie_id") movieID: Int): Observable<ReleaseDatesResponse>

    @GET("search/movie")
    fun searchMovie(@Query("query") query: Uri): Observable<SearchMoviesResponse>

    @GET("discover/movie")
    fun getMovieByGenre(@Query(value = "with_genres", encoded = true) genreID: String): Observable<PopularMoviesResponse>
}