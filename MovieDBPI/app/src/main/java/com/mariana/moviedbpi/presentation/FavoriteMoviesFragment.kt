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

class FavoriteMoviesFragment : Fragment(), MovieActionListener {

    private lateinit var genresAdapter: GenresAdapter
    private lateinit var moviesAdapter: MoviesAdapter
    private val favoriteMoviesFragmentViewModel = FavoriteMoviesViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_genres_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        setupGenresRecyclerView(view)
        setupMoviesRecyclerView(view)

        favoriteMoviesFragmentViewModel.getFavoriteMovies()
        setupObserveMoviesList()
        setupObserveGenresList()

    }

    private fun setupGenresRecyclerView(view: View) {
        val rvGenre = view.findViewById<RecyclerView>(R.id.rvGenres)
        genresAdapter = GenresAdapter(requireContext(), this)
        rvGenre.adapter = genresAdapter
        rvGenre.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
    }

    private fun setupMoviesRecyclerView(view: View) {
        val rvMovies = view.findViewById<RecyclerView>(R.id.rvMovies)
        moviesAdapter = MoviesAdapter(requireContext(), this)
        rvMovies.adapter = moviesAdapter
        rvMovies.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
    }

    private fun setupObserveMoviesList() {
        favoriteMoviesFragmentViewModel.moviesLiveData.observe(requireActivity(),
            { response ->
                response?.let {
                    moviesAdapter.dataSet.addAll(it) //TODO: verificar a adição de elementos nessa recyclerview
                    moviesAdapter.notifyDataSetChanged()
                }
            })
    }

    private fun setupObserveGenresList() {
        favoriteMoviesFragmentViewModel.genresIDLiveData.observe(requireActivity(),
            { response ->
                response?.let {
                    genresAdapter.dataSet = it as MutableList<Int>
                    genresAdapter.notifyDataSetChanged()
                }
            })
    }

    override fun openMovieDetailActivity(movieID: Int) {
        val intent = Intent(context, MovieDetailActivity::class.java)
        intent.putExtra(MoviesFragment.MOVIE_ID, movieID)
        context?.startActivity(intent)
    }

    override fun filterMoviesByGenre(genresIDs: MutableList<Int>) {
        TODO("Not yet implemented")
    }

    //TODO: ISSO AQUI VAI SER O TESTE DE INTERFACE
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