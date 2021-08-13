package com.mariana.moviedbpi.data.repository

import com.mariana.moviedbpi.data.database.DatabaseBuilder
import com.mariana.moviedbpi.data.mapper.database.MoviesDetailDBMapper
import com.mariana.moviedbpi.data.mapper.database.MoviesListDBMapper
import com.mariana.moviedbpi.domain.entity.Movie
import com.mariana.moviedbpi.domain.entity.MovieDetail

class LocalRepository(
    private val moviesMapper: MoviesListDBMapper = MoviesListDBMapper(),
    private val moviesDetailMapper: MoviesDetailDBMapper = MoviesDetailDBMapper()
) {

    suspend fun getFavoriteMovies() : List<Movie>? {
        return DatabaseBuilder.database?.moviesDao()?.getFavoriteMovies()?.map { moviesMapper.mapMoviesFromDatabaseToEntity(it) }
    }

    suspend fun getFavoriteMovieFromId(movieID: Int) : MovieDetail? {

        val movieDB = DatabaseBuilder.database?.moviesDao()?.getFavoriteMovieFromId(movieID)

        return if (movieDB.isNullOrEmpty()) {
            null
        } else {
            moviesDetailMapper.mapMoviesFromDatabaseToEntity(movieDB[0])
        }
    }

    suspend fun addFavoriteMovie(movie: Movie) : Movie {
        DatabaseBuilder.database?.moviesDao()?.addFavoriteMovie(moviesMapper.mapMoviesFromEntityToDatabase(movie))
        return movie
    }

    suspend fun addFavoriteMovieOnMovieDetail(movie: MovieDetail) : MovieDetail {
        DatabaseBuilder.database?.moviesDao()?.addFavoriteMovie(moviesDetailMapper.mapMoviesFromEntityToDatabase(movie))
        return movie
    }

    suspend fun deleteFavoriteMovieOnMovieDetail(movie: MovieDetail) : MovieDetail {
        DatabaseBuilder.database?.moviesDao()?.deleteFavoriteMovie(moviesDetailMapper.mapMoviesFromEntityToDatabase(movie))
        return movie
    }

    suspend fun deleteFavoriteMovie(movie: Movie) : Movie {
        DatabaseBuilder.database?.moviesDao()?.deleteFavoriteMovie(moviesMapper.mapMoviesFromEntityToDatabase(movie))
        return movie
    }
}