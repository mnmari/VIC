package com.mariana.moviedbpi.presentation

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mariana.moviedbpi.domain.SearchMoviesUseCase
import com.mariana.moviedbpi.domain.entity.Movie
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SearchMoviesViewModel : ViewModel() {

    private val _moviesLiveData = MutableLiveData<List<Movie>>()
    val moviesLiveData : LiveData<List<Movie>> = _moviesLiveData

    private val _genresIDLiveData = MutableLiveData<List<Int>>()
    val genresIDLiveData : LiveData<List<Int>> = _genresIDLiveData

    private val fetchMoviesUseCase = SearchMoviesUseCase()

    fun getMovies(queryURI : Uri) {
        val service = fetchMoviesUseCase.run(queryURI)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError {

            }
            .subscribe { it ->
                _moviesLiveData.value = it.foundMovies
                _genresIDLiveData.value = it.foundMovies.map { movies -> movies.genreIDs }.flatten().distinct()
            }
    }
}