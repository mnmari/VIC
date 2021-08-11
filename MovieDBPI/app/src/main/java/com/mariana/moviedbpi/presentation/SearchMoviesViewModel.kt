package com.mariana.moviedbpi.presentation

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mariana.moviedbpi.domain.AddFavoriteMovieUseCase
import com.mariana.moviedbpi.domain.DeleteFavoriteMovieUseCase
import com.mariana.moviedbpi.domain.FetchFavoriteMoviesUseCase
import com.mariana.moviedbpi.domain.SearchMoviesUseCase
import com.mariana.moviedbpi.domain.entity.Movie
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchMoviesViewModel : ViewModel() {

    private val _moviesLiveData = MutableLiveData<List<Movie>>()
    val moviesLiveData : LiveData<List<Movie>> = _moviesLiveData

    private val _genresIDLiveData = MutableLiveData<List<Int>>()
    val genresIDLiveData : LiveData<List<Int>> = _genresIDLiveData

    private val _favoriteMoviesLiveDataFromBD = MutableLiveData<List<Movie>>()

    private val searchMoviesUseCase = SearchMoviesUseCase()
    private val fetchFavoriteMoviesUseCase = FetchFavoriteMoviesUseCase()
    private val addFavoriteMovieUseCase = AddFavoriteMovieUseCase()
    private val deleteFavoriteMovieUseCase = DeleteFavoriteMovieUseCase()

    fun getMovies(queryURI : Uri) {
        val service = searchMoviesUseCase.run(queryURI)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError {

            }
            .subscribe {
                _moviesLiveData.value = it.foundMovies
                _genresIDLiveData.value = it.foundMovies.map { movies -> movies.genreIDs }.flatten().distinct()
            }
    }

    fun addFavoriteMovie(movie: Movie) {
        CoroutineScope(Dispatchers.IO).launch {
            addFavoriteMovieUseCase.run(movie)

            val favoriteMovie = _favoriteMoviesLiveDataFromBD.value?.find { it.movieID == movie.movieID }
            favoriteMovie?.isFavorite = true
        }
    }

    fun deleteFavoriteMovie(movie: Movie) {
        CoroutineScope(Dispatchers.IO).launch {
            deleteFavoriteMovieUseCase.run(movie)

            val favoriteMovie = _favoriteMoviesLiveDataFromBD.value?.find {it.movieID == movie.movieID}
            favoriteMovie?.isFavorite = false
        }
    }

    fun updateFavoriteStatus(foundMovies: List<Movie>?) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = fetchFavoriteMoviesUseCase.run()

            val favoriteMovies = foundMovies?.filter { movie ->
                response?.find { it.movieID == movie.movieID } != null
            }

            _moviesLiveData.postValue(foundMovies?.map { movie ->
                if (favoriteMovies?.find { it.movieID == movie.movieID } != null) {
                    movie.isFavorite = true
                }
                movie
            })
        }
    }
}