package com.mariana.moviedbpi.data.mapper.response

import com.mariana.moviedbpi.data.model.response.MovieResponse
import com.mariana.moviedbpi.data.model.response.PopularMoviesResponse
import com.mariana.moviedbpi.data.model.response.SearchMoviesResponse
import com.mariana.moviedbpi.domain.entity.Movie
import com.mariana.moviedbpi.domain.entity.PopularMovies
import com.mariana.moviedbpi.domain.entity.SearchMovies

class MoviesListResponseMapper{
    fun mapPopularMovies(popularMoviesResponse : PopularMoviesResponse) = PopularMovies(
        popularMovies = popularMoviesResponse.popularMovies.map {mapMovies(it)}
    )

    fun mapFoundMovies(searchMoviesResponse : SearchMoviesResponse) = SearchMovies(
        foundMovies = searchMoviesResponse.foundMovies.map {mapMovies(it)}
    )

    fun mapMovies(moviesResponse : MovieResponse) = Movie(
        movieID = moviesResponse.movieID,
        posterPath = moviesResponse.posterPath,
        genreIDs = moviesResponse.genreIDs,
        title = moviesResponse.title,
        releaseDate = moviesResponse.releaseDate,
        userRating = moviesResponse.userRating,
        overview = moviesResponse.overview)
}