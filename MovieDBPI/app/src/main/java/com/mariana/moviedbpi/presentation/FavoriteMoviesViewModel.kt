package com.mariana.moviedbpi.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
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

    fun deleteFavoriteMovie(movie: Movie) {
        CoroutineScope(Dispatchers.IO).launch {
            deleteFavoriteMovieUseCase.run(movie)

            val favoriteMovie = _moviesLiveData.value?.find { it.movieID == movie.movieID }
            favoriteMovie?.isFavorite = false
        }
    }

    //TODO: Consertar a lógica do filtro
    fun getMoviesByGenres(selectedGenres: List<Int>) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = fetchFavoriteMoviesUseCase.run()

            if (selectedGenres.isNotEmpty()) {
                val filteredMovies = response?.filter { movie ->
                    movie.genreIDs.any { it in selectedGenres }
                }

                _moviesLiveData.postValue(filteredMovies)

            } else {
                _moviesLiveData.postValue(response)
            }
        }
    }
}