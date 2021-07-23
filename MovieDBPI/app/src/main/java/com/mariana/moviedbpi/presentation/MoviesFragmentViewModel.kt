package com.mariana.moviedbpi.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mariana.moviedbpi.data.model.Genres
import com.mariana.moviedbpi.data.model.Movie
import com.mariana.moviedbpi.domain.FetchGenresUseCase
import com.mariana.moviedbpi.domain.FetchMoviesUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MoviesFragmentViewModel {

    private val _moviesLiveData = MutableLiveData<List<Movie>>()
    val moviesLiveData : LiveData<List<Movie>> = _moviesLiveData

    private val _genresLiveData = MutableLiveData<List<Genres>>()
    val genresLiveData : LiveData<List<Genres>> = _genresLiveData

    private val fetchMoviesUseCase = FetchMoviesUseCase()
    private val fetchGenresUseCase = FetchGenresUseCase()

    fun getMovies() {
        val service = fetchMoviesUseCase.run()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError {

            }
            .subscribe {
                _moviesLiveData.value = it.popularMovies
            }
    }

    fun getGenres() {
        val service = fetchGenresUseCase.run()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError {

            }
            .subscribe {
                _genresLiveData.value = it.genres
            }
    }
}