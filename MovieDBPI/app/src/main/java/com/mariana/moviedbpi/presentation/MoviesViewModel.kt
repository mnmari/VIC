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

    private val _moviesLiveDataFromBD = MutableLiveData<List<Movie>>()
    val moviesLiveDataFromDB : LiveData<List<Movie>> = _moviesLiveDataFromBD

    private val _genresLiveData = MutableLiveData<List<Genres>>()
    val genresLiveData : LiveData<List<Genres>> = _genresLiveData

    private val fetchMoviesUseCase = FetchMoviesUseCase()
    private val fetchGenresUseCase = FetchGenresUseCase()
    private val fetchFavoriteMoviesUseCase = FetchFavoriteMoviesUseCase()

    //TODO: getMovies recebe uma função como argumento e ela estará implementada na minha fragment (pra mandar pra tela de erro
    //TODO: antes de setar os filmes no moviesLiveData, fazer a chamada no BD (get), fazer a comparação se cada filme que tá vindo
    // da minha API está nos favoritos, caso ele esteja, eu seto o isFavorite como true (e mudo o ícone), e depois seto o valor do moviesLiveData
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

    private fun updateFavoriteStatus(moviesFromAPI: List<Movie>?) {
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

    fun getGenres() {
        try {
            val service = fetchGenresUseCase.run()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
//                .doOnError {
//                    errorListener?.onError()
//                }
                .subscribe ({
                    _genresLiveData.value = it.genres
                },{
                    errorListener?.onError()
                })
        }
        catch (e: Exception) {
            errorListener?.onError()
        }

    }

    fun getMoviesByGenres(selectedGenres: List<Int>) {
        val service = fetchMoviesUseCase.run()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError {

            }
            .subscribe { movies ->

                if (selectedGenres.isNotEmpty()) {
                    val filteredMovies = movies?.popularMovies?.filter { movie ->
                        movie.genreIDs.any { it in selectedGenres }
                    }

                    updateFavoriteStatus(filteredMovies)

                } else {
                    updateFavoriteStatus(movies.popularMovies)
                }
            }

    }
}