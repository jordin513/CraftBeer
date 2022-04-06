package com.nidroj.craftbeer.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.material.tabs.TabLayoutMediator
import com.nidroj.craftbeer.R
import com.nidroj.craftbeer.data.model.Beer
import com.nidroj.craftbeer.databinding.BottomSheetBeerInfoBinding
import com.nidroj.craftbeer.ui.adapter.BeerInfoPagerAdapter
import com.nidroj.craftbeer.util.ImageUtil

/**
 * Bottom Sheet that displays some key attributes of a selected beer
 *
 * @property beer Beer item containing all attributes
 */
class BeerInfoBottomSheet(private val beer: Beer) : BottomSheetDialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val binding: BottomSheetBeerInfoBinding = DataBindingUtil.inflate<BottomSheetBeerInfoBinding?>(
            inflater, R.layout.bottom_sheet_beer_info, container, false
        ).apply {
            //set data binding variables
            url = this@BeerInfoBottomSheet.beer.image_url
            beerName = this@BeerInfoBottomSheet.beer.name
            beerIbu = this@BeerInfoBottomSheet.beer.ibu
            beerAbv = "${this@BeerInfoBottomSheet.beer.abv}%"
        }

        //set up the viewpager adapter
        val adapter = BeerInfoPagerAdapter(this, beer)
        binding.pager.adapter = adapter

        binding.tabLayout.apply {
            /*TODO implement ingredients tab
            *  Here you would show any additional info a user would want */

            //update TabLayout listener to ignore page changes
            clearOnTabSelectedListeners()
            addOnTabSelectedListener(object : OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab) {
                    if (tab.position == 1) {
                        Toast.makeText(context, "Coming soon", Toast.LENGTH_SHORT).show()
                        getTabAt(0)?.select()

                    } else {
                        tab.select()
                    }
                }

                override fun onTabUnselected(tab: TabLayout.Tab) {
                    //no op
                }

                override fun onTabReselected(tab: TabLayout.Tab) {
                    //no op
                }
            })
        }

        //link tab layout and view pager
        TabLayoutMediator(
            binding.tabLayout, binding.pager
        ) { tab: TabLayout.Tab,
            position: Int ->
            tab.text = context?.getString(if (position == 0) R.string.summary else R.string.ingredients)
        }.attach()

        return binding.root
    }

    companion object {
        const val TAG = "BeerInfoBottomSheet"
    }
}

/**
 * Handles loading data bound image or the selected beer.
 *
 * @param view ImageView of beer
 * @param url URL of the beer's image
 */
@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url: String?) {
    ImageUtil.loadImage(view, url)
}