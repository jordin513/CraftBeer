package com.nidroj.craftbeer.ui.view

import android.os.Bundle
import android.transition.Explode
import android.view.View
import android.view.Window
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.nidroj.craftbeer.api.PunkApi
import com.nidroj.craftbeer.data.BeerRepository
import com.nidroj.craftbeer.data.model.Beer
import com.nidroj.craftbeer.databinding.ActivityMainBinding
import com.nidroj.craftbeer.ui.adapter.BeerListAdapter
import com.nidroj.craftbeer.ui.viewmodel.BeerViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber

class MainActivity : AppCompatActivity(), OnBeerClickListener {
    private lateinit var binding: ActivityMainBinding
    private val beerViewModel = BeerViewModel(BeerRepository(PunkApi.createApi()))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = BeerListAdapter(this).also {
            it.addLoadStateListener { loadState ->

                if (loadState.refresh is LoadState.Loading) {
                    binding.progressBar.visibility = View.VISIBLE
                } else {
                    binding.progressBar.visibility = View.GONE
                    val error = when {
                        loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
                        loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                        loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
                        else -> null
                    }

                    Timber.e(error?.error)

                }
            }
            binding.recyclerView.adapter = it
        }

        lifecycleScope.launch {
            beerViewModel.getBeers().collectLatest {
                adapter.submitData(it)
            }
        }

    }


    override fun onItemClicked(v: View?, beer: Beer, position: Int) {
        //TODO open mor info about beer
       }
}