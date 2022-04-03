package com.nidroj.craftbeer.ui.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import com.nidroj.craftbeer.api.PunkApi
import com.nidroj.craftbeer.data.BeerRepository
import com.nidroj.craftbeer.databinding.ActivityMainBinding
import com.nidroj.craftbeer.ui.adapter.BeerListAdapter
import com.nidroj.craftbeer.ui.viewmodel.BeerViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val beerViewModel = BeerViewModel(BeerRepository(PunkApi.createApi()))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = BeerListAdapter()
        binding.recyclerView.apply {
            this.adapter = adapter
            addItemDecoration(DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL))
        }

        lifecycleScope.launch {
            beerViewModel.getBeers().collectLatest {
                adapter.submitData(it)
            }
        }
        //todo add loading progress bar

    }
}