package com.example.pokemonsearch.viewmodel

import PokemonGO
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemonsearch.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException

class PokemonGOListViewModel : ViewModel() {

    private var pokemonList = MutableLiveData<List<PokemonGO>>()
    val observeList: LiveData<List<PokemonGO>>
        get() = pokemonList

    private var error = MutableLiveData<String>()
    val observeError: LiveData<String>
        get() = error

    fun getPokemonList() {
        viewModelScope.launch(Dispatchers.IO) {
            val retrievedData = Repository.getApiPokemonGOResponse()
            try {
                if (retrievedData.isSuccessful) {
                    pokemonList.postValue(
                        retrievedData.body()?.pokemon as List<PokemonGO>)
                } else {
                    error.postValue(retrievedData.code().toString())
                }
            } catch (e: HttpException) {
                error.postValue(e.message().toString())
            } catch (e: Throwable) {
                error.postValue(e.toString())
            }
        }
    }
}