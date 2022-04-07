package com.nidroj.craftbeer.util

import android.graphics.Bitmap
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.nidroj.craftbeer.R
import timber.log.Timber

object ImageUtil {
    fun loadImage(view: ImageView, url: String?) {
        //handle null url and placeholder image url supplied by PunkAPI
        if (url != null && !url.contains("keg.png")) {
            Glide.with(view)
                .asBitmap()
                .load(url)
                .listener(object : RequestListener<Bitmap> {
                    override fun onResourceReady(
                        resource: Bitmap?, model: Any?, target: Target<Bitmap>?,
                        dataSource: DataSource?, isFirstResource: Boolean
                    ): Boolean {
                        try {
                            resource?.let { res ->
                                view.setImageBitmap(res)
                            }
                        } catch (e: Exception) {
                            Timber.e("Unable to set image: $e")
                        }
                        return true
                    }

                    override fun onLoadFailed(
                        e: GlideException?, model: Any?, target: Target<Bitmap>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        view.setImageResource(R.drawable.ic_placeholder_error)
                        return true
                    }
                }).submit()
        } else {
            view.setImageResource(R.drawable.ic_beer)

        }
    }
}