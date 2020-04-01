package com.example.pokemonsearch

import PokemonGO
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class PokemonListGOAdapter : RecyclerView.Adapter<PokemonListGOAdapter.CustomViewHolder>() {

    var data: List<PokemonGO> = emptyList()

    fun setDataSet(list: List<PokemonGO>){
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
                .inflate(R.layout.pokemongo_list_layout, parent, false)
        )
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.name.text = data[position].name
        holder.index.text = data[position].id.toString()
        // https://www.serebii.net/pokemongo/pokemon/001.png
        val editURLString = "https://" + data[position].img.substringAfter("http://") // had to edit img url because it it needs https not http
        Picasso.get().load(editURLString).resize(300,300).into(holder.image)

    }

}
