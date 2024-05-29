package com.joaquito.rickmorty_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.joaquito.rickmorty_app.databinding.ActivityDetailBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class DetailActivity : AppCompatActivity() {

    companion object{
        const val EXTRA_ID = "extra_id"
    }

    private lateinit var retrofit:Retrofit
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retrofit = getRetrofit()
        val id: Int = intent.getIntExtra(EXTRA_ID, 0)
        getCharacterInfo(id)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun getCharacterInfo(id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            val myResponse: Response<CharactersDetailModel> = retrofit.create(ApiClient::class.java).getCharacterDetail(id.toString())
            if (myResponse.isSuccessful){
                val response: CharactersDetailModel? = myResponse.body()
                if (response != null){
                    runOnUiThread{
                        createUI(response)
                    }
                }
            }
        }
    }

    private fun createUI(response: CharactersDetailModel) {
        Picasso.get().load(response.characterImage).into(binding.ivDetail)
        binding.tvTitleName.text = response.characterName
        binding.tvDetailSpecies.text = response.characterSpecies
        binding.tvDetailGender.text = response.characterGender
        binding.tvOrigin.text = response.characterOrigin.originName
        binding.tvLocation.text = response.characterLocation.locationName
    }


    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}