package com.andalus.contentprovidersplayground.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.andalus.contentprovidersplayground.Objects.Word
import com.andalus.contentprovidersplayground.R
import kotlinx.android.synthetic.main.word_dictionary_item.view.*

class WordDictionaryAdapter(var data: MutableList<Word> = mutableListOf()) :
    RecyclerView.Adapter<WordDictionaryAdapter.WordHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.word_dictionary_item, parent, false)
        return WordHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: WordHolder, position: Int) {
        holder.tvWordItem.text = data[position].word
        holder.tvLocaleItem.text = data[position].locale
        holder.tvFrequencyItem.text = data[position].frequency
        holder.tvAppIdItem.text = data[position].appId
    }

    class WordHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvWordItem: TextView = view.tvWordItem
        val tvLocaleItem: TextView = view.tvLocaleItem
        val tvFrequencyItem: TextView = view.tvFrequencyItem
        val tvAppIdItem: TextView = view.tvAppIdItem
    }
}