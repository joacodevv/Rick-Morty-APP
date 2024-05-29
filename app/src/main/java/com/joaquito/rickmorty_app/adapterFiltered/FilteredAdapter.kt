package com.joaquito.rickmorty_app.adapterFiltered

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.joaquito.rickmorty_app.CharacterItemModel
import com.joaquito.rickmorty_app.R

class FilteredAdapter (private var characterList: List<CharacterItemModel> = emptyList()): RecyclerView.Adapter<FilteredViewHolder>() {

    fun updateList(characterList: List<CharacterItemModel>){
        this.characterList = characterList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilteredViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_character ,parent, false)
        return FilteredViewHolder(view)
    }

    override fun onBindViewHolder(holder: FilteredViewHolder, position: Int) {
        holder.bind(characterList[position])
    }

    override fun getItemCount(): Int {
        return characterList.size
    }
}