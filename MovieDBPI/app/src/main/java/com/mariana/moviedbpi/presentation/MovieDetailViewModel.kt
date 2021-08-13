package com.mariana.moviedbpi.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
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

//    private val _moviesLiveDataFromAPI = MutableLiveData<List<Movie>>()
    private val _favoriteMoviesLiveDataFromBD = MutableLiveData<List<MovieDetail>>()

    private val fetchMovieDetailUseCase = FetchMovieDetailUseCase()
    private val fetchMovieCastUseCase = FetchMovieCastUseCase()
    private val fetchMovieRatingUseCase = FetchMovieRatingUseCase()
//    private val fetchFavoriteMoviesUseCase = FetchFavoriteMoviesUseCase()
    private val fetchFavoriteMovieFromIdUseCase = FetchFavoriteMovieFromIdUseCase()
    private val addFavoriteMovieUseCase = AddFavoriteMovieUseCase()
    private val deleteFavoriteMovieUseCase = DeleteFavoriteMovieUseCase()

    fun getMovieDetail(movieID: Int) {
        val service = fetchMovieDetailUseCase.run(movieID)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({
                updateFavoriteStatus(it)
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

    fun updateFavoriteStatus(movieDetail: MovieDetail) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = fetchFavoriteMovieFromIdUseCase.run(movieDetail.movieID)

            response?.let {
                movieDetail.isFavorite = true
            }
            _movieDetailLiveData.postValue(movieDetail)
        }
    }

//    fun addFavoriteMovie(movieDetail: MovieDetail) {
//        CoroutineScope(Dispatchers.IO).launch {
//            addFavoriteMovieUseCase.fromMovieDetailToMovieDB(movieDetail)
//
//            val favoriteMovie = _favoriteMoviesLiveDataFromBD.value?.find { it.movieID == movieDetail.movieID }
//            favoriteMovie?.isFavorite = true
//        }
////    }
//
//    fun deleteFavoriteMovie(movieDetail: MovieDetail) {
//        CoroutineScope(Dispatchers.IO).launch {
//            deleteFavoriteMovieUseCase.fromMovieDetailToMovieDB(movieDetail)
//
//            val favoriteMovie = _favoriteMoviesLiveDataFromBD.value?.find {it.movieID == movieDetail.movieID}
//            favoriteMovie?.isFavorite = false
//        }
//    }

    fun updateFavoriteMovie() {
        CoroutineScope(Dispatchers.IO).launch {
            _movieDetailLiveData.value?.let{

                if (it.isFavorite) {
                    deleteFavoriteMovieUseCase.fromMovieDetailToMovieDB(it)
                    it.isFavorite = false
                } else {
                    addFavoriteMovieUseCase.fromMovieDetailToMovieDB(it)
                    it.isFavorite = true
                }

                _movieDetailLiveData.postValue(it)
            }
        }
    }
}
