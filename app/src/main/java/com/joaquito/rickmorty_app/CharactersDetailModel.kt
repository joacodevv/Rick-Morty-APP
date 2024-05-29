package com.joaquito.rickmorty_app

import com.google.gson.annotations.SerializedName

data class CharactersDetailModel(
    @SerializedName("id") val characterId:String,
    @SerializedName("name") val characterName:String,
    @SerializedName("species") val characterSpecies:String,
    @SerializedName("gender") val characterGender:String,
    @SerializedName("image") val characterImage:String,
    @SerializedName("origin") val characterOrigin: OriginModel,
    @SerializedName("location") val characterLocation:LocationModel,
)

data class OriginModel(
    @SerializedName("name") val originName:String
)

data class LocationModel(
    @SerializedName("name") val locationName:String
)