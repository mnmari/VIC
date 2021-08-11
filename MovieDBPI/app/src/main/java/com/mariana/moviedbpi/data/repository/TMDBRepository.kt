package com.mariana.moviedbpi.data.repository

import android.net.Uri
import com.mariana.moviedbpi.data.mapper.response.*
import com.mariana.moviedbpi.domain.entity.*
import io.reactivex.Observable

class TMDBRepository(
    private val moviesMapper: MoviesListResponseMapper = MoviesListResponseMapper(),
    private val movieDetailMapper: MovieDetailResponseMapper = MovieDetailResponseMapper(),
    private val genresMapper: GenresResponseMapper = GenresResponseMapper(),
    private val releaseDatesMapper: ReleaseDatesResponseMapper = ReleaseDatesResponseMapper(),
    private val castMapper: CastResponseMapper = CastResponseMapper()
) {

    fun fetchMoviesList() : Observable<PopularMovies> {
        return Network.getService().getPopularMovies().map { moviesMapper.mapPopularMovies(it) }
    }

    fun fetchGenresList() : Observable<AllGenres> {
        return Network.getService().getAllGenres().map { genresMapper.mapAllGenres(it) }
    }

    fun fetchMovieDetail(movieID: Int) : Observable<MovieDetail> {
        return Network.getService().getMovieDetail(movieID).map { movieDetailMapper.mapMovie(it) }
    }

    fun fetchMovieRating(movieID: Int) : Observable<ReleaseDates> {
        return Network.getService().getMovieRating(movieID).map { releaseDatesMapper.mapReleaseDates(it) }
    }

    fun fetchMovieCast(movieID: Int) : Observable<MovieCast> {
        return Network.getService().getMovieCast(movieID).map { castMapper.mapMovieCast(it) }
    }

    fun searchForMovies(query: Uri) : Observable<SearchMovies> {
        return Network.getService().searchMovie(query).map { moviesMapper.mapFoundMovies(it) }
    }
}