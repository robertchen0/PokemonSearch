package com.example.pokemonsearch

import PokemonGOResponse
import com.example.pokemonsearch.model.Pokemon.PokemonAllListResponse
import retrofit2.Response
import retrofit2.http.GET

interface NetworkApi {

    // https://pokeapi.co/api/v2/pokemon/?limit=964
    // https://raw.githubusercontent.com/Biuni/PokemonGO-Pokedex/master/pokedex.json

    @GET("api/v2/pokemon/?limit=964")
    suspend fun getAllPokemon() : Response<PokemonAllListResponse>

    @GET("Biuni/PokemonGO-Pokedex/master/pokedex.json")
    suspend fun getAllPokemonGO() : Response<PokemonGOResponse>
}