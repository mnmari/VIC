package com.mariana.moviedbpi.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mariana.moviedbpi.domain.AddFavoriteMovieUseCase
import com.mariana.moviedbpi.domain.DeleteFavoriteMovieUseCase
import com.mariana.moviedbpi.domain.FetchFavoriteMoviesUseCase
import com.mariana.moviedbpi.domain.entity.Movie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoriteMoviesViewModel : ViewModel() {

    private val _moviesLiveData = MutableLiveData<List<Movie>>()
    val moviesLiveData : LiveData<List<Movie>> = _moviesLiveData

    private val _genresIDLiveData = MutableLiveData<List<Int>>()
    val genresIDLiveData : LiveData<List<Int>> = _genresIDLiveData

    private val fetchFavoriteMoviesUseCase = FetchFavoriteMoviesUseCase()
    private val addFavoriteMovieUseCase = AddFavoriteMovieUseCase()
    private val deleteFavoriteMovieUseCase = DeleteFavoriteMovieUseCase()

    fun getFavoriteMovies() : List<Movie>? {
        var response : List<Movie>? = null

        CoroutineScope(Dispatchers.IO).launch {
            response = fetchFavoriteMoviesUseCase.run()
            _moviesLiveData.postValue(response)
            _genresIDLiveData.postValue(response?.map { movies -> movies.genreIDs }?.flatten()?.distinct())
        }

        return response
    }

    fun addFavoriteMovie(movie: Movie) {
        CoroutineScope(Dispatchers.IO).launch {
            addFavoriteMovieUseCase.run(movie)
            getFavoriteMovies()
        }
    }

    fun deleteFavoriteMovie(movie: Movie) {
        CoroutineScope(Dispatchers.IO).launch {
            deleteFavoriteMovieUseCase.run(movie)
            getFavoriteMovies()
        }
    }
}