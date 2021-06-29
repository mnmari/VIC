package com.sandyara.aula18

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class PeoplePageAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
    override fun getCount(): Int {
        return 2
    }

    override fun getItem(position: Int): Fragment {

        return when (position) {
            0 -> frFirst()
            1 -> frSecond()
            else -> frFirst()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {

        return when (position) {
            0 -> FIRST_FRAGMENT_TITLE
            1 -> SECOND_FRAGMENT_TITLE
            else -> null
        }
    }

    companion object {
        private const val FIRST_FRAGMENT_TITLE = "Primeiro"
        private const val SECOND_FRAGMENT_TITLE = "Segundo"
    }
}