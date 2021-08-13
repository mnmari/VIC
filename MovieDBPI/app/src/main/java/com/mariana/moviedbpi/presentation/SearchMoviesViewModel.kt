package com.mariana.moviedbpi.presentation

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mariana.moviedbpi.domain.*
import com.mariana.moviedbpi.domain.entity.Movie
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchMoviesViewModel(private val errorListener: DoOnErrorOnRequestListener? = null) : ViewModel() {

    private val _moviesLiveData = MutableLiveData<List<Movie>>()
    val moviesLiveData : LiveData<List<Movie>> = _moviesLiveData

    private lateinit var completeMovieList : List<Movie>

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
            .subscribe ({
                updateFavoriteStatus(it.foundMovies)
                completeMovieList = it.foundMovies
                _genresIDLiveData.value = it.foundMovies.map { movies -> movies.genreIDs }.flatten().distinct()
            },{
                errorListener?.onError()
            })
    }

    fun addFavoriteMovie(movie: Movie) {
        CoroutineScope(Dispatchers.IO).launch {
            addFavoriteMovieUseCase.fromMovieToMovieDB(movie)

            val favoriteMovie = _favoriteMoviesLiveDataFromBD.value?.find { it.movieID == movie.movieID }
            favoriteMovie?.isFavorite = true
        }
    }

    fun deleteFavoriteMovie(movie: Movie) {
        CoroutineScope(Dispatchers.IO).launch {
            deleteFavoriteMovieUseCase.fromMovieToMovieDB(movie)

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

    fun getMoviesByGenres(selectedGenres: List<Int>) {

        if (selectedGenres.isNotEmpty()) {
            val filteredMovies = completeMovieList.filter { movie ->
                movie.genreIDs.containsAll(selectedGenres)
            }

            updateFavoriteStatus(filteredMovies)

        } else {
            updateFavoriteStatus(completeMovieList)
        }
    }
}
