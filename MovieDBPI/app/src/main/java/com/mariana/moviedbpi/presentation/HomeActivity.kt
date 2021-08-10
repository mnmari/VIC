package com.mariana.moviedbpi.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.*
import androidx.core.net.toUri
import androidx.core.widget.addTextChangedListener
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.mariana.moviedbpi.R
import com.mariana.moviedbpi.presentation.adapter.HomeActivityFragmentsAdapter

class HomeActivity : AppCompatActivity() {

    private var searchFragment: SearchMoviesFragment? = null
    private lateinit var vwpContent : ViewPager2
    private lateinit var tblMenu : TabLayout
    private lateinit var fragmentContainer : FrameLayout

    private var edtSearch : EditText? = null
    private lateinit var txtSearchTitle : TextView
    private lateinit var btnReturn : Button

    private lateinit var query : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        bindViews()
        setupViews()
        onSearch()
   }

    private fun bindViews() {
        vwpContent = findViewById(R.id.vwpContentHomeActivity)
        tblMenu = findViewById(R.id.tblMenuHomeActivity)
        edtSearch = findViewById(R.id.edtSearchMovieHomeActivity)
        txtSearchTitle = findViewById(R.id.txtSearchTitle)
        btnReturn = findViewById(R.id.btnReturn)
        fragmentContainer = findViewById(R.id.searchFragmentContainer)
    }

    private fun onSearch() {

        edtSearch?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                changeVisibilityFromHomeToSearch()
                onClickSearch()
            }

            override fun afterTextChanged(s: Editable?) {
                changeVisibilityFromSearchToHome(s)
            }
        })
    }

    private fun changeVisibilityFromSearchToHome(s: Editable?) {
        if (s != null) {
            if (s.isEmpty()) {
                tblMenu.visibility = View.VISIBLE
                vwpContent.visibility = View.VISIBLE
                txtSearchTitle.visibility = View.GONE
                btnReturn.visibility = View.GONE
                fragmentContainer.visibility = View.GONE
                edtSearch?.text?.clear()
            }
        }
    }

    private fun onClickSearch() {
        edtSearch?.setOnEditorActionListener { v, actionId, event ->
            when (actionId) {
                EditorInfo.IME_ACTION_SEARCH -> {
                    query = edtSearch?.text.toString()

                    if (searchFragment == null) {
                        searchFragment = SearchMoviesFragment.newInstance(query)
                        searchFragment?.let {
                            supportFragmentManager.beginTransaction()
                                .add(R.id.searchFragmentContainer, it)
                                .addToBackStack(null)
                                .commit()
                        }
                    } else {
                        searchFragment?.updateQuery(query.toUri())
                    }
                    true
                }
                else -> false
            }
        }
    }

    private fun changeVisibilityFromHomeToSearch() {
        tblMenu.visibility = View.GONE
        vwpContent.visibility = View.GONE
        txtSearchTitle.visibility = View.VISIBLE
        btnReturn.visibility = View.VISIBLE
        fragmentContainer.visibility = View.VISIBLE
    }

    private fun setupViews() {
        vwpContent.adapter = HomeActivityFragmentsAdapter(this@HomeActivity)
        vwpContent.isUserInputEnabled = false
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