package com.mariana.moviedbpi.presentation

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mariana.moviedbpi.R
import com.mariana.moviedbpi.domain.DoOnErrorOnRequestListener
import com.mariana.moviedbpi.domain.entity.Genres
import com.mariana.moviedbpi.presentation.adapter.CastAdapter
import com.mariana.moviedbpi.presentation.adapter.MovieDetailGenresAdapter

class MovieDetailActivity : AppCompatActivity(), DoOnErrorOnRequestListener {

    private var movieID : Int = 0

    private lateinit var moviePoster : ImageView
    private lateinit var movieTitle : TextView
    private lateinit var movieYear : TextView
    private lateinit var movieRating : TextView
    private lateinit var movieRuntime : TextView
    private lateinit var movieOverview : TextView
    private lateinit var movieUserRating : TextView

    private lateinit var genresAdapter : MovieDetailGenresAdapter
    private lateinit var castAdapter : CastAdapter

    private lateinit var btnFavoriteIcon : ImageButton
    private lateinit var btnReturn : ImageButton

    private val movieDetailViewModel = MovieDetailViewModel(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        getMovieID()

        setupGenresRecyclerView()
        setupCastRecyclerView()

        bindViews()

        movieDetailViewModel.getMovieDetail(movieID)
        setupObservableMovieDetail()

        movieDetailViewModel.getMovieRating(movieID)
        setupObservableMovieRating()

        movieDetailViewModel.getMovieCast(movieID)
        setupObservableMovieCast()

        onClickBtnReturn()

        btnFavoriteIcon.setOnClickListener {
            onFavoriteClickedListener()
        }
    }

    private fun onClickBtnReturn() {
        btnReturn.setOnClickListener {
            onBackPressed()
        }
    }

    private fun getMovieID() {
        movieID = intent.getIntExtra("movieID", 0)
    }

    private fun setupGenresRecyclerView() {
        val rvGenres = findViewById<RecyclerView>(R.id.rvGenresMovieDetail)
        genresAdapter = MovieDetailGenresAdapter()
        rvGenres.adapter = genresAdapter
        rvGenres.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun setupCastRecyclerView() {
        val rvCast = findViewById<RecyclerView>(R.id.rvCastMovieDetail)
        castAdapter = CastAdapter(this@MovieDetailActivity)
        rvCast.adapter = castAdapter
        rvCast.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun bindViews() {
        btnFavoriteIcon = findViewById(R.id.btnFavoriteIconDetailActivity)
        btnReturn = findViewById(R.id.btnReturn)
        moviePoster = findViewById(R.id.imgMovieDetailPoster)
        movieTitle = findViewById(R.id.txtMovieDetailTitle)
        movieYear = findViewById(R.id.txtMovieDetailYear)
        movieRating = findViewById(R.id.txtMovieDetailMovieRating)
        movieRuntime = findViewById(R.id.txtMovieDetailRuntime)
        movieOverview = findViewById(R.id.txtMovieDetailOverview)
        movieUserRating = findViewById(R.id.txtMovieDetailUserRating)
    }

    private fun setupObservableMovieDetail() {
        movieDetailViewModel.movieDetailLiveData.observe(this,
            { response ->
                response?.let {
                    movieTitle.text = it.title
                    movieYear.text = it.showYearFromDate()
                    movieRuntime.text = it.showRuntimeInHoursAndMinutes()
                    movieOverview.text = it.overview
                    movieUserRating.text = it.showUserRatingString()
                    genresAdapter.dataSet = it.genres as MutableList<Genres>

                    if (it.isFavorite) {
                        btnFavoriteIcon.setImageResource(R.drawable.ic_heart_favorites_selected)
                    } else {
                        btnFavoriteIcon.setImageResource(R.drawable.ic_heart_favorites_unselected)
                    }

                    genresAdapter.notifyDataSetChanged()

                    Glide.with(this)
                        .load("https://image.tmdb.org/t/p/w500${it.posterPath}")
                        .into(moviePoster)

                }
            })
    }

    private fun setupObservableMovieRating() {
        movieDetailViewModel.movieRatingLiveData.observe(this,
            { response ->
                response?.let {
                    movieRating.text = it.movieRating
                }
            })
    }

    private fun setupObservableMovieCast() {
        movieDetailViewModel.movieCastLiveData.observe(this,
            { response ->
                response?.let {
                    castAdapter.dataSet.addAll(it)
                    castAdapter.notifyDataSetChanged()
                }
            })
    }

    fun onFavoriteClickedListener() {
        movieDetailViewModel.updateFavoriteMovie()
    }

    override fun onError() {
        val intent = Intent(this, RequestFailedActivity::class.java)
        startActivity(intent)
    }
}


