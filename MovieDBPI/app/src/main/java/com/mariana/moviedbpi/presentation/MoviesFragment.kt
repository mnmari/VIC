package com.mariana.moviedbpi.presentation

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mariana.moviedbpi.R
import com.mariana.moviedbpi.domain.DoOnErrorOnRequestListener
import com.mariana.moviedbpi.domain.MovieActionListener
import com.mariana.moviedbpi.domain.entity.Movie
import com.mariana.moviedbpi.presentation.adapter.GenresAdapter
import com.mariana.moviedbpi.presentation.adapter.MoviesAdapter

class MoviesFragment : Fragment(), MovieActionListener, DoOnErrorOnRequestListener {

    private lateinit var genresAdapter: GenresAdapter
    private lateinit var moviesAdapter: MoviesAdapter
    private val moviesViewModel = MoviesViewModel(this)

    private lateinit var progressBar: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_genres_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupMoviesRecyclerView(view)
        setupGenresRecyclerView(view)

        bindProgressBar(view)

        moviesViewModel.getMovies()
        setupObserveMoviesList()

        moviesViewModel.getGenres()
        setupObserveGenresList()
    }

    private fun bindProgressBar(view: View) {
        progressBar = view.findViewById(R.id.progressBar)
    }

    override fun onResume() {
        super.onResume()

        moviesViewModel.getMovies()
        moviesViewModel.getGenres()
        setupObserveMoviesList()
        setupObserveGenresList()
    }

    private fun setupGenresRecyclerView(view: View) {
        val rvGenre = view.findViewById<RecyclerView>(R.id.rvGenres)
        genresAdapter = GenresAdapter(requireContext(), this)
        rvGenre.adapter = genresAdapter
        rvGenre.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
    }

    private fun setupMoviesRecyclerView(view: View) {
        val rvMovies = view.findViewById<RecyclerView>(R.id.rvMovies)
        moviesAdapter = MoviesAdapter(requireContext(), this)
        rvMovies.adapter = moviesAdapter
        rvMovies.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
    }

    private fun setupObserveMoviesList() {
        moviesViewModel.moviesLiveDataFromAPI.observe(requireActivity(),
            { response ->
                response?.let {
                    moviesAdapter.dataSet = it as MutableList<Movie>
                    moviesAdapter.notifyDataSetChanged()
                    progressBar.visibility = View.GONE
                }
            })
    }

    private fun setupObserveGenresList() {
        moviesViewModel.genresLiveData.observe(requireActivity(),
            { response ->
                response?.let {
                    genresAdapter.dataSet.addAll(it.map {it.genreID})
                    genresAdapter.notifyDataSetChanged()
                }
            })
    }

    override fun openMovieDetailActivity(movieID: Int) {
        val intent = Intent(context, MovieDetailActivity::class.java)
        intent.putExtra(MOVIE_ID, movieID)
        context?.startActivity(intent)
    }

    override fun filterMoviesByGenre(genresIDs: MutableList<Int>) {
        moviesViewModel.getMoviesByGenres(genresIDs)
        setupObserveMoviesList()
    }

    companion object {
        const val MOVIE_ID = "movieID"
    }

    // TODO: colocar usecases de favoritos na viewmodel de filmes
    override fun onFavoriteClickedListener(movie: Movie, isClicked: Boolean) {
        if (isClicked) {
            if (!movie.isFavorite) {
                movie.isFavorite = true
                moviesViewModel.addFavoriteMovie(movie)
                moviesAdapter.notifyDataSetChanged()
            }
            else {
                movie.isFavorite = false
                moviesViewModel.deleteFavoriteMovie(movie)
                moviesAdapter.notifyDataSetChanged()
            }
        }
    }

    override fun onError() {
        val intent = Intent(context, RequestFailedActivity::class.java)
        context?.startActivity(intent)
    }
}