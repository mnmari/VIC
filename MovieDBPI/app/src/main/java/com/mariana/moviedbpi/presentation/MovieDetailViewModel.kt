package com.mariana.moviedbpi.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.room.Delete
import com.mariana.moviedbpi.domain.*
import com.mariana.moviedbpi.domain.entity.Cast
import com.mariana.moviedbpi.domain.entity.Movie
import com.mariana.moviedbpi.domain.entity.MovieDetail
import com.mariana.moviedbpi.domain.entity.Rating
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieDetailViewModel(private val errorListener: DoOnErrorOnRequestListener? = null) : ViewModel() {

    private val _movieDetailLiveData = MutableLiveData<MovieDetail>()
    val movieDetailLiveData : LiveData<MovieDetail> = _movieDetailLiveData

    private val _movieRatingLiveData = MutableLiveData<Rating>()
    val movieRatingLiveData : LiveData<Rating> = _movieRatingLiveData

    private val _movieCastLiveData = MutableLiveData<List<Cast>>()
    val movieCastLiveData : LiveData<List<Cast>> = _movieCastLiveData

    private val _moviesLiveDataFromAPI = MutableLiveData<List<Movie>>()
    private val _favoriteMoviesLiveDataFromBD = MutableLiveData<List<Movie>>()

    private val fetchMovieDetailUseCase = FetchMovieDetailUseCase()
    private val fetchMovieCastUseCase = FetchMovieCastUseCase()
    private val fetchMovieRatingUseCase = FetchMovieRatingUseCase()
    private val fetchFavoriteMoviesUseCase = FetchFavoriteMoviesUseCase()
    private val addFavoriteMovieUseCase = AddFavoriteMovieUseCase()
    private val deleteFavoriteMovieUseCase = DeleteFavoriteMovieUseCase()

    fun getMovieDetail(movieID: Int) {
        val service = fetchMovieDetailUseCase.run(movieID)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({
                updateFavoriteStatus(movieID)
                _movieDetailLiveData.value = it
            },{
                errorListener?.onError()
            })
    }

    fun getMovieRating(movieID: Int) {
        val service = fetchMovieRatingUseCase.run(movieID)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({ it ->
                val localMovieRating = (it.response.find {it.countryCode == COUNTRY_CODE})?.releaseDates
                _movieRatingLiveData.value = localMovieRating?.last()
            },{
                errorListener?.onError()
            })
    }

    companion object {
        const val COUNTRY_CODE = "BR"
    }

    fun getMovieCast(movieID: Int) {
        val service = fetchMovieCastUseCase.run(movieID)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({
                _movieCastLiveData.value = it.cast
            },{
                errorListener?.onError()
            })
    }

    fun updateFavoriteStatus(movieID: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = fetchFavoriteMoviesUseCase.run()

            val movie = response?.find { it.movieID == movieID }

            movie?.isFavorite = movie != null
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
}
