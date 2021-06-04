package com.example.wongnaiassignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wongnaiassignment.Adapter.CoinsItemAdapter
import com.example.wongnaiassignment.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collectLatest


class MainActivity : AppCompatActivity() {

    private lateinit var coinsItemAdapter: CoinsItemAdapter

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setUpRecycleView()
        setUpAdapter()
        setUpViewModel()


    }

    private fun setUpAdapter() {

        coinsItemAdapter.addLoadStateListener { loadstates ->
            binding.swipeRefreshLayout.isRefreshing = loadstates.refresh is LoadState.Loading

        }

        binding.swipeRefreshLayout.setOnRefreshListener { coinsItemAdapter.refresh() }
    }

    private fun setUpRecycleView() {

        binding.coinRecycleView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            val decoration =
                DividerItemDecoration(applicationContext, DividerItemDecoration.VERTICAL)
            addItemDecoration(decoration)
            coinsItemAdapter = CoinsItemAdapter()
            adapter = coinsItemAdapter


        }
    }

    private fun setUpViewModel() {
        val viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        lifecycleScope.launchWhenCreated {
            viewModel.getListData().collectLatest {
                coinsItemAdapter.submitData(it)
            }
        }
    }


}