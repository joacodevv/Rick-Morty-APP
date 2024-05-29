package com.joaquito.rickmorty_app

import com.google.gson.annotations.SerializedName

data class CharactersModel (
    @SerializedName("results") val characterInfo: List<CharacterItemModel>

)

data class CharacterItemModel(
    @SerializedName("id") val characterId: Int,
    @SerializedName("name") val characterName: String,
    @SerializedName("species") val characterSpecies: String,
    @SerializedName("image") val characterImage: String
)
