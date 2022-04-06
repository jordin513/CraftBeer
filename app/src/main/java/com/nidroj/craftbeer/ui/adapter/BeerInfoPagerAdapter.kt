package com.nidroj.craftbeer.ui.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.nidroj.craftbeer.data.model.Beer
import com.nidroj.craftbeer.ui.view.BeerSummaryFragment

/**
 * Adapter for the Viewpager in the BeerInfoBottomSheet
 *
 * @property beer Beer item containing all attributes
 * @constructor
 * Pass in the fragment it will attach to and the beer item.
 *
 * @param fragment parent fragment
 */
class BeerInfoPagerAdapter(fragment: Fragment, private val beer: Beer) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return if (position == 0) {
            BeerSummaryFragment(beer)
        } else {
            //todo implement ingredients tab
            Fragment()
        }
    }
}