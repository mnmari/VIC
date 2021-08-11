package com.mariana.moviedbpi.presentation

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mariana.moviedbpi.R
import com.mariana.moviedbpi.domain.MovieActionListener
import com.mariana.moviedbpi.domain.entity.Movie
import com.mariana.moviedbpi.presentation.MoviesFragment.Companion.MOVIE_ID
import com.mariana.moviedbpi.presentation.adapter.GenresAdapter
import com.mariana.moviedbpi.presentation.adapter.MoviesAdapter

class SearchMoviesFragment : Fragment(), MovieActionListener {

    private lateinit var moviesAdapter: MoviesAdapter
    private lateinit var genresAdapter : GenresAdapter
    private val searchMoviesFragmentViewModel = SearchMoviesViewModel()
    private lateinit var movieNotFound : View

    private var query : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            query = it.getString(ARG_NAME)
        }
    }

    companion object {
        private const val ARG_NAME = "query"

        @JvmStatic
        fun newInstance(query: String) =
            SearchMoviesFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_NAME, query)
                }
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_genres_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        movieNotFound = view.findViewById(R.id.viewMovieNotFound)

        setupMoviesRecyclerView(view)
        setupGenresRecyclerView(view)

        val queryURI = query?.toUri()

        if (queryURI != null) {
            updateQuery(queryURI)
        }
    }

    fun updateQuery(query: Uri) {

        searchMoviesFragmentViewModel.getMovies(query)
        setupObserveMoviesList()
        setupObserveGenresList()
        movieNotFound.visibility = View.GONE
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
        moviesAdapter = MoviesAdapter(requireContext())
        rvMovies.adapter = moviesAdapter
        rvMovies.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
    }

    private fun setupObserveMoviesList() {
        searchMoviesFragmentViewModel.moviesLiveData.observe(requireActivity(),
            { response ->
                response?.let {
                    if (it.isEmpty()) {
                        movieNotFound.visibility = View.VISIBLE
                        moviesAdapter.dataSet.clear()
                        moviesAdapter.notifyDataSetChanged()
                    } else {
                        moviesAdapter.dataSet = it as MutableList<Movie>
                        moviesAdapter.notifyDataSetChanged()
                    }
                }
            })
    }

    private fun setupObserveGenresList() {
        searchMoviesFragmentViewModel.genresIDLiveData.observe(requireActivity(),
            { response ->
                response?.let {
                    if (it.isEmpty()) {
                        moviesAdapter.dataSet.clear()
                        moviesAdapter.notifyDataSetChanged()
                    } else {
                        genresAdapter.dataSet = it as MutableList<Int>
                        genresAdapter.notifyDataSetChanged()
                    }
                }
            })
    }

    //TODO: verificar intent
    override fun openMovieDetailActivity(movieID: Int) {
        val intent = Intent(context, MovieDetailActivity::class.java)
        intent.putExtra(MOVIE_ID, movieID)
        context?.startActivity(intent)
    }

    override fun filterMoviesByGenre(genresIDs: MutableList<Int>) {
        TODO("Not yet implemented")
    }

    override fun onFavoriteClickedListener(movie: Movie, isClicked: Boolean) {
        TODO("Not yet implemented")
    }
}