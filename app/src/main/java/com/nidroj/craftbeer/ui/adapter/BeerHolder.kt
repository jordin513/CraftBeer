package com.nidroj.craftbeer.ui.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nidroj.craftbeer.R

class BeerHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val title: TextView = itemView.findViewById(R.id.title)
    val abv: TextView = itemView.findViewById(R.id.abv)
    val image: ImageView = itemView.findViewById(R.id.image)
}