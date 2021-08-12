package com.mariana.moviedbpi.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mariana.moviedbpi.domain.*
import com.mariana.moviedbpi.domain.entity.Genres
import com.mariana.moviedbpi.domain.entity.Movie
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class MoviesViewModel(private val errorListener: DoOnErrorOnRequestListener? = null) : ViewModel()  {

    private val _moviesLiveDataFromAPI = MutableLiveData<List<Movie>>()
    val moviesLiveDataFromAPI : LiveData<List<Movie>> = _moviesLiveDataFromAPI

    private val _favoriteMoviesLiveDataFromBD = MutableLiveData<List<Movie>>()

    private val _genresLiveData = MutableLiveData<List<Genres>>()
    val genresLiveData : LiveData<List<Genres>> = _genresLiveData

    private val fetchMoviesUseCase = FetchMoviesUseCase()
    private val fetchGenresUseCase = FetchGenresUseCase()
    private val fetchFavoriteMoviesUseCase = FetchFavoriteMoviesUseCase()
    private val addFavoriteMovieUseCase = AddFavoriteMovieUseCase()
    private val deleteFavoriteMovieUseCase = DeleteFavoriteMovieUseCase()

    fun getMovies() {
        val service = fetchMoviesUseCase.run()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe( { moviesFromAPI ->
                updateFavoriteStatus(moviesFromAPI.popularMovies)
            },{
                errorListener?.onError()
            })
    }

    fun updateFavoriteStatus(moviesFromAPI: List<Movie>?) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = fetchFavoriteMoviesUseCase.run()

            val favoriteMovies = moviesFromAPI?.filter { movie ->
                response?.find { it.movieID == movie.movieID } != null
            }

            _moviesLiveDataFromAPI.postValue(moviesFromAPI?.map { movie ->
                if (favoriteMovies?.find { it.movieID == movie.movieID } != null) {
                    movie.isFavorite = true
                }
                movie
            })
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

    fun getGenres() {
        val service = fetchGenresUseCase.run()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({
                _genresLiveData.value = it.genres
            },{
                errorListener?.onError()
            })
    }

    fun getMoviesByGenres(selectedGenres: List<Int>) {
        val service = fetchMoviesUseCase.run()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({ movies ->

                if (selectedGenres.isNotEmpty()) {
                    val filteredMovies = movies?.popularMovies?.filter { movie ->
                        movie.genreIDs.containsAll(selectedGenres)
                    }

                    updateFavoriteStatus(filteredMovies)

                } else {
                    updateFavoriteStatus(movies.popularMovies)
                }
            },{
                errorListener?.onError()
            })
    }
}