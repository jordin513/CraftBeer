package com.nidroj.craftbeer.ui.adapter

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.nidroj.craftbeer.R
import com.nidroj.craftbeer.data.model.Beer
import com.nidroj.craftbeer.ui.view.OnBeerClickListener

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
            name.let { holder.title.text = it }
            abv.let { holder.abv.text = "$it%" }

            //set list item's image
            image_url.let { url ->

                //handle null url and placeholder image url supplied by PunkAPI
                if (url != null && !url.contains("keg.png")) {
                    Glide.with(holder.itemView).asBitmap().load(url).listener(object : RequestListener<Bitmap> {
                        override fun onResourceReady(
                            resource: Bitmap?, model: Any?, target: Target<Bitmap>?,
                            dataSource: DataSource?, isFirstResource: Boolean
                        ): Boolean {
                            resource?.let { res ->
                                holder.image.post {
                                    holder.image.setImageBitmap(res)
                                }
                            }
                            return true
                        }

                        override fun onLoadFailed(
                            e: GlideException?, model: Any?, target: Target<Bitmap>?,
                            isFirstResource: Boolean
                        ): Boolean {
                            holder.image.let { iv ->
                                iv.post { iv.setImageResource(R.drawable.ic_placeholder_error) }
                            }
                            return true
                        }
                    }).submit()
                }
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