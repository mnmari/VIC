package com.mariana.moviedbpi.presentation.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.mariana.moviedbpi.presentation.FavoriteMoviesFragment
import com.mariana.moviedbpi.presentation.MoviesFragment

private const val NUMBER_OF_FRAGMENTS = 2

class HomeActivityFragmentsAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {

    override fun getItemCount(): Int = NUMBER_OF_FRAGMENTS

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> MoviesFragment()
            1 -> FavoriteMoviesFragment()
            else -> MoviesFragment()
        }
    }
}

