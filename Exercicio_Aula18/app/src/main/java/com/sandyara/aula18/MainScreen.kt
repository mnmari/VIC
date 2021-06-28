package com.sandyara.aula18

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

class MainScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        val viewpager = findViewById<ViewPager>(R.id.vpPeople)
        viewpager.adapter = frAdapter(supportFragmentManager)
        val tablayout = findViewById<TabLayout>(R.id.tlMenu)
        tablayout.setupWithViewPager(viewpager)
    }

    companion object {
        val data = listOf("Nome: Sandy, Idade: 21", "Nome: Joana, Idade: 23")
    }
}