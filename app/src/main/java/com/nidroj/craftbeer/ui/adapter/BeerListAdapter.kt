package com.nidroj.craftbeer.ui.adapter

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.nidroj.craftbeer.R
import com.nidroj.craftbeer.data.model.Beer

open class BeerListAdapter : PagingDataAdapter<Beer, BeerListAdapter.Holder>(BEER_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(LayoutInflater.from(parent.context).inflate(R.layout.item_beer, parent, false))
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: Holder, position: Int) {
        val beer = getItem(position)

        //set list item content
        beer?.apply {

            name.let { holder.title.text = it }
            abv.let { holder.abv.text = "$it% ABV" }

            //set list item's image
            image_url.let { url ->
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

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.title)
        val abv: TextView = itemView.findViewById(R.id.abv)
        val image: ImageView = itemView.findViewById(R.id.image)
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