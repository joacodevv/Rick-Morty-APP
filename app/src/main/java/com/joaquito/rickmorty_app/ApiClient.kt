package com.joaquito.rickmorty_app

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiClient {
    @GET("character/")
    suspend fun getCharacters():Response<CharactersModel>

    @GET("character/")
    suspend fun getCharactersFiltered(@Query("name") name: String): Response<CharactersModel>

    @GET("character/{id}")
    suspend fun getCharacterDetail(@Path("id") characterId:String):Response<CharactersDetailModel>

}