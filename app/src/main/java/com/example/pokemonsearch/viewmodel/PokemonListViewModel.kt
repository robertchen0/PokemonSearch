package com.example.pokemonsearch.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemonsearch.Repository
import com.example.pokemonsearch.model.Pokemon.Pokemon
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException

class PokemonListViewModel : ViewModel() {

    private var pokemonList = MutableLiveData<List<Pokemon>>()
    val observeList: LiveData<List<Pokemon>>
        get() = pokemonList

    private var error = MutableLiveData<String>()
    val observeError: LiveData<String>
        get() = error

    private val repo: Repository = Repository()

    fun getPokemonList() {
        viewModelScope.launch(Dispatchers.IO) {
            val retrievedData = repo.getApiPokemonResponse()
            try {
                if (retrievedData.isSuccessful) {
                    pokemonList.postValue(
                        retrievedData.body()?.results as List<Pokemon>
                    )
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