package com.joaquito.rickmorty_app.adapterFiltered

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.joaquito.rickmorty_app.CharacterItemModel
import com.joaquito.rickmorty_app.databinding.ItemCharacterBinding
import com.squareup.picasso.Picasso

class FilteredViewHolder(view: View):RecyclerView.ViewHolder(view){


private val binding = ItemCharacterBinding.bind(view)

    fun bind(characterItemModel: CharacterItemModel, onItemSelected: (Int) -> Unit) {
        binding.tvCharacterName.text = characterItemModel.characterName
        Picasso.get().load(characterItemModel.characterImage).into(binding.ivCharacter)
        binding.tvCharacterSpecies.text = characterItemModel.characterSpecies
        binding.root.setOnClickListener { onItemSelected(characterItemModel.characterId) }
    }
}