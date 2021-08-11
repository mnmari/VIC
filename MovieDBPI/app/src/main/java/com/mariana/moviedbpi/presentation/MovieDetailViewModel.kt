package com.mariana.moviedbpi.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mariana.moviedbpi.domain.FetchMovieCastUseCase
import com.mariana.moviedbpi.domain.FetchMovieDetailUseCase
import com.mariana.moviedbpi.domain.FetchMovieRatingUseCase
import com.mariana.moviedbpi.domain.DoOnErrorOnRequestListener
import com.mariana.moviedbpi.domain.entity.Cast
import com.mariana.moviedbpi.domain.entity.MovieDetail
import com.mariana.moviedbpi.domain.entity.Rating
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MovieDetailViewModel(private val errorListener: DoOnErrorOnRequestListener? = null) : ViewModel() {

    private val _movieDetailLiveData = MutableLiveData<MovieDetail>()
    val movieDetailLiveData : LiveData<MovieDetail> = _movieDetailLiveData

    private val _movieRatingLiveData = MutableLiveData<Rating>()
    val movieRatingLiveData : LiveData<Rating> = _movieRatingLiveData

    private val _movieCastLiveData = MutableLiveData<List<Cast>>()
    val movieCastLiveData : LiveData<List<Cast>> = _movieCastLiveData

    private val fetchMovieDetailUseCase = FetchMovieDetailUseCase()
    private val fetchMovieCastUseCase = FetchMovieCastUseCase()
    private val fetchMovieRatingUseCase = FetchMovieRatingUseCase()

    fun getMovieDetail(movieID: Int) {
        val service = fetchMovieDetailUseCase.run(movieID)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError {
                errorListener?.onError()
            }
            .subscribe {
                _movieDetailLiveData.value = it
            }
    }

    fun getMovieRating(movieID: Int) {
        val service = fetchMovieRatingUseCase.run(movieID)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError {
                errorListener?.onError()
            }
            .subscribe { it ->
                val localMovieRating = (it.response.find {it.countryCode == COUNTRY_CODE})?.releaseDates
                _movieRatingLiveData.value = localMovieRating?.last()
                }
    }

    companion object {
        const val COUNTRY_CODE = "BR"
    }

    fun getMovieCast(movieID: Int) {
        val service = fetchMovieCastUseCase.run(movieID)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError {
                errorListener?.onError()
            }
            .subscribe {
                _movieCastLiveData.value = it.cast
            }
    }
}
