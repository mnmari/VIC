package com.mariana.minhaagenda

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mariana.minhaagenda.MainActivity.Companion.contactList
import com.mariana.minhaagenda.MainActivity.Companion.viewContactList
import kotlinx.android.synthetic.main.item_contact.view.*

class AgendaAdapter(val context: Context, var dataSet: MutableList<Contact>) : RecyclerView.Adapter<AgendaAdapter.AgendaViewHolder>() {

    class AgendaViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name = view.findViewById<TextView>(R.id.itemName)
        val phoneNumber = view.findViewById<TextView>(R.id.itemPhoneNumber)
        val referenceOrEmail = view.findViewById<TextView>(R.id.itemReferenceOrEmail)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AgendaViewHolder {
        val viewInstance = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_contact, parent, false)
        return AgendaViewHolder(viewInstance)
    }

    override fun onBindViewHolder(holder: AgendaViewHolder, position: Int) {
        holder.name.text = "Nome: " + dataSet[position].name
        holder.phoneNumber.text = "Telefone: " + dataSet[position].phoneNumber
        holder.referenceOrEmail.text = "Referência: " + dataSet[position].referenceOrEmail

        holder.itemView.btnDelete.setOnClickListener {
            contactList.removeAt(position)
            viewContactList.removeAt(position)
            notifyItemRemoved(position)
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int = dataSet.size

    fun updateList(filteredList: MutableList<Contact>) {
        dataSet = filteredList
        notifyDataSetChanged()
    }
}