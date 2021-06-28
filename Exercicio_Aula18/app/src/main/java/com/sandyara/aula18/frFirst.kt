package com.sandyara.aula18

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sandyara.aula18.MainScreen.Companion.data

class frFirst : Fragment(){

    lateinit var adapter : rvAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.rvperson, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val rvPerson = view.findViewById<RecyclerView>(R.id.rvPerson)
        adapter = rvAdapter(data)
        rvPerson.adapter = adapter
        rvPerson.layoutManager = LinearLayoutManager(requireActivity())
    }
}