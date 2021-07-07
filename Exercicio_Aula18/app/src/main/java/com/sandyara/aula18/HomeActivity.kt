package com.sandyara.aula18

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

class HomeActivity : AppCompatActivity() {

    lateinit var vwpContainer : ViewPager
    lateinit var tblMenu : TabLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        bindViews()
        setupViews()

//        tblMenu.addOnTabSelectedListener()
//        vwpContainer.addOnPageChangeListener()
    }

    private fun setupViews() {
        vwpContainer.adapter = PeoplePageAdapter(supportFragmentManager)
        tblMenu.setupWithViewPager(vwpContainer)
    }

    private fun bindViews() {
        vwpContainer = findViewById(R.id.vwpContent)
        tblMenu = findViewById(R.id.tblMenu)
    }

    companion object {
        val data = listOf("Nome: Sandy, Idade: 21", "Nome: Joana, Idade: 23")
    }
}