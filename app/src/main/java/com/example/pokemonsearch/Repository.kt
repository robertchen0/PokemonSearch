package com.example.pokemonsearch

import PokemonGOResponse
import android.util.Log
import com.example.pokemonsearch.model.Pokemon.PokemonAllListResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Repository {

    init {
        Log.d("/////", "Instance made")
    }

    suspend fun getApiPokemonResponse(): Response<PokemonAllListResponse> {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://pokeapi.co/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(NetworkApi::class.java)

        return api.getAllPokemon()
    }

    suspend fun getApiPokemonGOResponse(): Response<PokemonGOResponse> {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://raw.githubusercontent.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(NetworkApi::class.java)

        return api.getAllPokemonGO()
    }

}
