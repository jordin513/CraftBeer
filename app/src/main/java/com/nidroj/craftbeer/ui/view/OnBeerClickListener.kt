package com.nidroj.craftbeer.ui.view

import android.view.View
import com.nidroj.craftbeer.data.model.Beer

interface OnBeerClickListener {
    /**
     * Handles when a user clicks on a beer item in a recycler view
     *
     * @param v view of item
     * @param beer Beer item containing all attributes
     * @param position position of item in recycler view
     */
    fun onItemClicked(v: View?, beer: Beer, position: Int)
}