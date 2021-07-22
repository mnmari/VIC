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

        getMovies()
        getGenres()


//        val request = Network.buildService(TMDBService::class.java)
//        val call = request.getMovies(Constants.PRIVATE_KEY.value)
//
//        call.enqueue(object : Callback<PopularMovies> {
//            override fun onResponse(call: Call<PopularMovies>, response: Response<PopularMovies>) {
//                if (response.isSuccessful) {
////                    progress_bar.visibility = View.GONE
//
//                    rvMovies.apply {
//                        setHasFixedSize(true)
//                        layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
//                        adapter = MoviesAdapter(response.body()!!.popularMovies as MutableList<Movie>)
//                    }
//                }
//            }
//
//            override fun onFailure(call: Call<PopularMovies>, t: Throwable) {
//                Toast.makeText(requireActivity(), "Deu ruim", Toast.LENGTH_SHORT).show()
//            }
//        })
    }

    private fun getMovies() {

        var service = Network.getService().getAllMovies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError {
                Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
            }
            .doOnComplete {

            }
            .subscribe { response ->
                Toast.makeText(requireContext(), "nada faz sentido nessa vida", Toast.LENGTH_SHORT).show()
                moviesAdapter.dataSet.addAll(response.popularMovies)
                moviesAdapter.notifyDataSetChanged()
            }
//            .dispose()
    }

    private fun getGenres() {

        var service = Network.getService().getAllGenres()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError {
                Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
            }
            .doOnComplete {

            }
            .subscribe { response ->
                genresAdapter.dataSet.addAll(response.genres)
                genresAdapter.notifyDataSetChanged()
            }
//            .dispose()
    }
}