package com.example.pokemonsearch.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.pokemonsearch.PokemonListAdapter
import com.example.pokemonsearch.R
import com.example.pokemonsearch.model.Pokemon.Pokemon
import com.example.pokemonsearch.viewmodel.PokemonListViewModel
import kotlinx.android.synthetic.main.activity_all_pokemon.*

class AllPokemon : AppCompatActivity() {

    lateinit var pokemonListViewModel: PokemonListViewModel
    lateinit var listAdapter: PokemonListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_pokemon)

        pokemonListViewModel = ViewModelProvider(this).get(PokemonListViewModel::class.java)
        listAdapter = PokemonListAdapter()
        pokemonListViewModel.getPokemonList()
        pokemonListViewModel.observeList.observe(this, Observer {
            listAdapter.setDataSet(it)
        })
        pokemonListViewModel.observeError.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })
        et_search.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    listAdapter.setDataSet(filterPoke(pokemonListViewModel.observeList.value ?: emptyList(), s.toString()))
            }
        })
        recyclerView.adapter = listAdapter
        recyclerView.layoutManager = GridLayoutManager(this, 2)
    }

    private fun filterPoke(list: List<Pokemon>, userInput: String) : List<Pokemon>{
        val newArray: ArrayList<Pokemon> = arrayListOf()
        if(userInput.isEmpty()){
            return list
        }
        else {
            for(i in list){
                if(i.name.contains(userInput, true)){
                    newArray.add(i)
                }
            }
            return newArray
        }
    }
}
