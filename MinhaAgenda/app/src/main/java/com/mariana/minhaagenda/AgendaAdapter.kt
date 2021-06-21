package com.mariana.minhaagenda

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mariana.minhaagenda.MainActivity.Companion.contactList
import kotlinx.android.synthetic.main.item_contact.view.*

class AgendaAdapter(val context: Context, var dataSet: MutableList<String>) : RecyclerView.Adapter<AgendaAdapter.AgendaViewHolder>() {

    class AgendaViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val contact = view.findViewById<TextView>(R.id.itemContact)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AgendaViewHolder {
        val viewInstance = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_contact, parent, false)
        return AgendaViewHolder(viewInstance)
    }

    override fun onBindViewHolder(holder: AgendaViewHolder, position: Int) {
        holder.contact.text = dataSet[position]

//        holder.itemView.btnDelete.setOnClickListener {
//            contactList.removeAt(position)
//            notifyItemRemoved(position)
//            notifyDataSetChanged()
//        }
    }

    override fun getItemCount(): Int = dataSet.size

    fun updateList(filteredList: MutableList<String>) {
        dataSet = filteredList
        notifyDataSetChanged()
    }
}