package com.nidroj.craftbeer.ui.view

import android.view.View
import com.nidroj.craftbeer.data.model.Beer

interface OnBeerClickListener {
    fun onItemClicked(v: View?, beer: Beer, position: Int)
}