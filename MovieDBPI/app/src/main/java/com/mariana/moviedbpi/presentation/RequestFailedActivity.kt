package com.mariana.moviedbpi.presentation

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mariana.moviedbpi.R
import com.mariana.moviedbpi.domain.entity.Genres
import com.mariana.moviedbpi.presentation.adapter.CastAdapter
import com.mariana.moviedbpi.presentation.adapter.MovieDetailGenresAdapter

class RequestFailedActivity : AppCompatActivity() {

//    private lateinit var btnExitErrorScreen: Button
//    private lateinit var btnTryAgain: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_request_failed)

//        bindViews()
//
//        btnTryAgain.setOnClickListener {
//            val intent = Intent(this, HomeActivity::class.java)
//            startActivity(intent)
//        }
    }

//    private fun bindViews() {
//        btnExitErrorScreen = findViewById(R.id.btnExitErrorScreen)
//        btnTryAgain = findViewById(R.id.btnTryAgain)
//    }
}