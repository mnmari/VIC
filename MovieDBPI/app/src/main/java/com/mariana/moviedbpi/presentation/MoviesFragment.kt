package com.mariana.moviedbpi.presentation

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mariana.moviedbpi.R
import com.mariana.moviedbpi.domain.MovieActionListener
import com.mariana.moviedbpi.domain.entity.Movie
import com.mariana.moviedbpi.presentation.adapter.GenresAdapter
import com.mariana.moviedbpi.presentation.adapter.MoviesAdapter

class MoviesFragment : Fragment(), MovieActionListener {

    private lateinit var genresAdapter: GenresAdapter
    private lateinit var moviesAdapter: MoviesAdapter
    private val moviesFragmentViewModel = MoviesViewModel()
    private val favoriteMoviesFragmentViewModel = FavoriteMoviesViewModel()

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

        moviesFragmentViewModel.getMovies()
        setupObserveMoviesList()

        moviesFragmentViewModel.getGenres()
        setupObserveGenresList()
    }

    private fun setupGenresRecyclerView(view: View) {
        val rvGenre = view.findViewById<RecyclerView>(R.id.rvGenres)
        genresAdapter = GenresAdapter()
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
        moviesFragmentViewModel.moviesLiveDataFromAPI.observe(requireActivity(),
            { response ->
                response?.let {
                    moviesAdapter.dataSet = it as MutableList<Movie>
                    moviesAdapter.notifyDataSetChanged()
                }
            })
    }

    private fun setupObserveGenresList() {
        moviesFragmentViewModel.genresLiveData.observe(requireActivity(),
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
        TODO("Not yet implemented")
    }

    companion object {
        const val MOVIE_ID = "movieID"
    }

    //TODO: fazer uma comparação dos filmes que vieram da API com os filmes salvos em favoritos.
    // Salvar a lista de filmes favoritos,
    override fun onFavoriteClickedListener(movie: Movie, isClicked: Boolean) {

        if (isClicked) {
            if (!movie.isFavorite) {
                movie.isFavorite = true
                favoriteMoviesFragmentViewModel.addFavoriteMovie(movie)
            }
            else {
                movie.isFavorite = false
                favoriteMoviesFragmentViewModel.deleteFavoriteMovie(movie)
            }
        }
    }
}