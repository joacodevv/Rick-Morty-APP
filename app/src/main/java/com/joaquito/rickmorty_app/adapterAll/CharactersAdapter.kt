package com.joaquito.rickmorty_app.adapterAll

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.joaquito.rickmorty_app.CharacterItemModel
import com.joaquito.rickmorty_app.R

class CharactersAdapter(
    private var characterList:List<CharacterItemModel> = emptyList(),
    private val onItemSelected: (Int) -> Unit
):RecyclerView.Adapter<CharactersViewHolder>(){

    fun updateList(characterList: List<CharacterItemModel>){
        this.characterList = characterList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_character ,parent, false)
        return CharactersViewHolder(view)
    }

    override fun getItemCount(): Int {
        return characterList.size
    }

    override fun onBindViewHolder(holder: CharactersViewHolder, position: Int) {
        holder.bind(characterList[position], onItemSelected)
    }

}