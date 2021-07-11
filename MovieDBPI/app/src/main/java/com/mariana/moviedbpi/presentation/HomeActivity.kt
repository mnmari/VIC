package com.mariana.moviedbpi.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mariana.moviedbpi.presentation.adapter.rvFilterAdapter
import com.mariana.moviedbpi.R

class HomeActivity : AppCompatActivity() {

    private lateinit var filterAdapter: rvFilterAdapter
    private lateinit var rvFilter: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        rvFilter = findViewById(R.id.rvFilter)
        filterAdapter = rvFilterAdapter()
        rvFilter.adapter = filterAdapter
        rvFilter.layoutManager = LinearLayoutManager(this)

    }
}