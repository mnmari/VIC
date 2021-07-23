package com.mariana.moviedbpi.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mariana.moviedbpi.R
import com.mariana.moviedbpi.data.repository.Network
import com.mariana.moviedbpi.presentation.adapter.GenresAdapter
import com.mariana.moviedbpi.presentation.adapter.MoviesAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MoviesFragment : Fragment() {

    private lateinit var genresAdapter: GenresAdapter
    private lateinit var moviesAdapter: MoviesAdapter
    private val viewModel = MoviesFragmentViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_genres_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val rvMovies = view.findViewById<RecyclerView>(R.id.rvMovies)
        moviesAdapter = MoviesAdapter(requireContext())
        rvMovies.adapter = moviesAdapter
        rvMovies.layoutManager= LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)

        val rvGenre = view.findViewById<RecyclerView>(R.id.rvGenres)
        genresAdapter = GenresAdapter()
        rvGenre.adapter = genresAdapter
        rvGenre.layoutManager= LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)

        viewModel.getMovies()
        setupObserveMoviesList()

        viewModel.getGenres()
        setupObserveGenresList()
    }

    private fun setupObserveMoviesList() {
        viewModel.moviesLiveData.observe(requireActivity(),
            { response ->
                response?.let {
                    moviesAdapter.dataSet.addAll(it)
                    moviesAdapter.notifyDataSetChanged()
                }
            })
    }

    private fun setupObserveGenresList() {
        viewModel.genresLiveData.observe(requireActivity(),
            { response ->
                response?.let {
                    genresAdapter.dataSet.addAll(it)
                    genresAdapter.notifyDataSetChanged()
                }
            })
    }
}