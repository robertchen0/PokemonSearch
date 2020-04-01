package com.example.pokemonsearch

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemonsearch.model.Pokemon.Pokemon
import com.squareup.picasso.Picasso


class PokemonListAdapter : RecyclerView.Adapter<PokemonListAdapter.CustomViewHolder>() {

    var data: List<Pokemon> = emptyList()

    fun setDataSet(list: List<Pokemon>){
        data = list
        notifyDataSetChanged()
    }

    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView = itemView.findViewById(R.id.tv_name)
        var index: TextView = itemView.findViewById(R.id.tv_index)
        var image: ImageView = itemView.findViewById(R.id.iv_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        return CustomViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.pokemon_list_layout, parent, false)
        )
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.name.text = data[position].name.capitalize()
        val result = data[position].url.substringAfter("/pokemon/").substringBefore('/')
        holder.index.text = result
        val urlImage: String = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + result + ".png"
        Picasso.get().load(urlImage).resize(300,300).into(holder.image)
    }

}
