package com.mariana.moviedbpi.presentation

import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.mariana.moviedbpi.R

class RequestFailedActivity : AppCompatActivity() {

    private lateinit var btnExitErrorScreen: ImageButton
    private lateinit var btnTryAgain: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_request_failed)
        bindViews()

        btnExitErrorScreen.setOnClickListener {
            onBackPressed()
        }

        btnTryAgain.setOnClickListener {
            onBackPressed()
        }
    }

    private fun bindViews() {
        btnExitErrorScreen = findViewById(R.id.btnExitErrorScreen)
        btnTryAgain = findViewById(R.id.btnTryAgain)
    }
}