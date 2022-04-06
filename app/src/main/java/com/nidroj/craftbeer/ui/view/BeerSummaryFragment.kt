package com.nidroj.craftbeer.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.nidroj.craftbeer.R
import com.nidroj.craftbeer.data.model.Beer
import com.nidroj.craftbeer.databinding.FragmentBeerSummaryBinding

/**
 * Fragment that displays some key data about a beer.
 * This includes, a description, food pairings, and brewer's tips
 *
 * @property beer Beer item containing all attributes
 */
class BeerSummaryFragment(private val beer: Beer) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentBeerSummaryBinding = DataBindingUtil.inflate<FragmentBeerSummaryBinding?>(
            inflater, R.layout.fragment_beer_summary, container, false
        ).apply {
            //set data binding variables
            beerDescription = this@BeerSummaryFragment.beer.description
            brewersTips = this@BeerSummaryFragment.beer.brewers_tips
            foodPairings = this@BeerSummaryFragment.beer.food_pairing?.joinToString("\n•", "•")
        }

        return binding.root
    }

}