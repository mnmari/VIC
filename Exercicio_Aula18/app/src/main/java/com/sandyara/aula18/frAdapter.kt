package com.sandyara.aula18

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class frAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
    override fun getCount(): Int {
        return 2
    }

    override fun getItem(position: Int): Fragment {
        var fragment: Fragment = frFirst()

        when (position) {
            0 -> fragment = frFirst()
            1 -> fragment = frSecond()
        }

        return fragment
    }

    override fun getPageTitle(position: Int): CharSequence? {
        var pageTitle = ""

        when (position) {
            0 -> pageTitle =  "Primeiro"
            1 -> pageTitle = "Segundo"
        }

        return pageTitle
    }

}