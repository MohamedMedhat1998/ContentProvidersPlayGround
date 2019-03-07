package com.andalus.contentprovidersplayground.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.andalus.contentprovidersplayground.Objects.Contact
import com.andalus.contentprovidersplayground.R
import kotlinx.android.synthetic.main.word_dictionary_item.view.*

class ContactsAdapter(var data: MutableList<Contact> = mutableListOf()) :
    RecyclerView.Adapter<ContactsAdapter.ContactHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.word_dictionary_item, parent, false)
        return ContactHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ContactHolder, position: Int) {
        holder.tvWordItem.text = data[position].name
        holder.tvLocaleItem.text = data[position].timesContacted
    }

    class ContactHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvWordItem: TextView = view.tvDisplayName
        val tvLocaleItem: TextView = view.tvTimesContacted
    }
}