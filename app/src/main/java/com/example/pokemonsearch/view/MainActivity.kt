package com.example.pokemonsearch.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pokemonsearch.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_allPokemon.setOnClickListener {
            var intent = Intent(this, AllPokemon::class.java)
            startActivity(intent)
        }
        btn_pokemonGO.setOnClickListener {
            var intent = Intent(this, AllPokemonGO::class.java)
            startActivity(intent)
        }
    }
}
