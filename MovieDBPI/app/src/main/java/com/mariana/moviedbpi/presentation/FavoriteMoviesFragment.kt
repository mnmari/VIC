package com.mariana.moviedbpi.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.mariana.moviedbpi.R

class FavoriteMoviesFragment : Fragment() {

    val text = "teste"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.rv_favorite_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val texto = text

        Toast.makeText(requireActivity(), texto, Toast.LENGTH_SHORT).show()
    }
}