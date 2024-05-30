package com.joaquito.rickmorty_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.joaquito.rickmorty_app.DetailActivity.Companion.EXTRA_ID
import com.joaquito.rickmorty_app.adapterAll.CharactersAdapter
import com.joaquito.rickmorty_app.adapterFiltered.FilteredAdapter
import com.joaquito.rickmorty_app.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var retrofit:Retrofit
    private lateinit var adapter:CharactersAdapter
    private lateinit var filteredAdapter: FilteredAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        retrofit = getRetrofit()
        CoroutineScope(Dispatchers.IO).launch {
            val myResponse: Response<CharactersModel> = retrofit.create(ApiClient::class.java).getCharacters()
            if (myResponse.isSuccessful){
                val response: CharactersModel? = myResponse.body()
                if (response != null){
                    runOnUiThread {
                        adapter.updateList(response.characterInfo)
                    }

                }
            }else{
                Log.i("joaco","no funciona :(")
            }
        }
        initUI()

    }

    private fun initUI() {
            binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    searchByName(query.orEmpty())
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return false
                }

            })


        adapter = CharactersAdapter{ characterId -> navigateToDetail(characterId) }
        binding.rvAll.setHasFixedSize(true)
        binding.rvAll.layoutManager = GridLayoutManager(this, 2)
        binding.rvAll.adapter = adapter

        filteredAdapter = FilteredAdapter{ characterId -> navigateToDetail(characterId) }
        binding.rvFiltered.setHasFixedSize(true)
        binding.rvFiltered.layoutManager = GridLayoutManager(this, 2)
        binding.rvFiltered.adapter = filteredAdapter


    }

    private fun searchByName(query: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val myResponse:Response<CharactersModel> = retrofit.create(ApiClient::class.java).getCharactersFiltered(query)
            if (myResponse.isSuccessful){
                val response: CharactersModel? = myResponse.body()
                if (response != null){
                    runOnUiThread {
                        binding.ivBack.isVisible = true
                        binding.rvAll.isVisible = false
                        binding.rvFiltered.isVisible = true
                        filteredAdapter.updateList(response.characterInfo)
                        if (binding.rvFiltered.isVisible && !binding.rvAll.isVisible){
                            goBack()
                        }
                    }
                }
            }
        }
    }

    private fun getRetrofit():Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun navigateToDetail(id:Int){
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(EXTRA_ID, id)
        startActivity(intent)
    }

    private fun goBack(){
        binding.ivBack.setOnClickListener {
            binding.rvAll.isVisible = true
            binding.rvFiltered.isVisible = false
            binding.ivBack.isVisible = false
        }

    }
}




