package com.mariana.moviedbpi.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.mariana.moviedbpi.R
import com.mariana.moviedbpi.presentation.adapter.FragmentsAdapter

class HomeActivity : AppCompatActivity() {

    lateinit var vwpContent : ViewPager2
    lateinit var tblMenu: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        bindViews()
        setupViews()
    }

    private fun bindViews() {
        vwpContent = findViewById(R.id.vwpContent)
        tblMenu = findViewById(R.id.tblMenu)
    }

    private fun setupViews() {
        vwpContent.adapter = FragmentsAdapter(this@HomeActivity)
        TabLayoutMediator(tblMenu, vwpContent) { tab, position ->

            tab.text = when (position) {
                0 -> FIRST_FRAGMENT_TITLE
                1 -> SECOND_FRAGMENT_TITLE
                else -> null
            }
        }.attach()
    }

    companion object {
        private const val FIRST_FRAGMENT_TITLE = "Todos os filmes"
        private const val SECOND_FRAGMENT_TITLE = "Favoritos"
    }
}