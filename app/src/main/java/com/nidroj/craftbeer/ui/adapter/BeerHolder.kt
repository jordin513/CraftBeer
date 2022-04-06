package com.nidroj.craftbeer.ui.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nidroj.craftbeer.R

/**
 * RecyclerView holder for beer items.
 *
 * @constructor
 * pass in the item view
 *
 * @param itemView view of the specific beer item
 */
class BeerHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val beerName: TextView = itemView.findViewById(R.id.beer_name)
    val abv: TextView = itemView.findViewById(R.id.abv)
    val image: ImageView = itemView.findViewById(R.id.image)
}