package com.nidroj.craftbeer.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.nidroj.craftbeer.R
import com.nidroj.craftbeer.data.model.Beer
import com.nidroj.craftbeer.ui.view.OnBeerClickListener
import com.nidroj.craftbeer.util.ImageUtil

/**
 * RecyclerView adapter for a grid list of beers.
 * This PagingDataAdapter handles the pagination of the PunkAPI
 *
 * @property listener Click listener to handle when a beer item is selected
 */
open class BeerListAdapter internal constructor(
    private val listener: OnBeerClickListener
) : PagingDataAdapter<Beer, BeerHolder>(BEER_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeerHolder {
        return BeerHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_beer, parent, false))
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: BeerHolder, position: Int) {
        val beer = getItem(position)

        beer?.apply {

            //set item click listener
            holder.itemView.setOnClickListener {
                it.startAnimation(AnimationUtils.loadAnimation(it.context, R.anim.bounce))
                it.postDelayed({
                    listener.onItemClicked(it, this, position)
                }, 150)
            }

            //set list item content
            name.let { holder.beerName.text = it }
            abv.let { holder.abv.text = "$it%" }

            //set list item's image
            image_url.let { url ->
                ImageUtil.loadImage(holder.image, url)
            }
        }
    }

    companion object {

        private val BEER_COMPARATOR = object : DiffUtil.ItemCallback<Beer>() {
            override fun areItemsTheSame(oldItem: Beer, newItem: Beer): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Beer, newItem: Beer): Boolean =
                oldItem == newItem
        }
    }
}