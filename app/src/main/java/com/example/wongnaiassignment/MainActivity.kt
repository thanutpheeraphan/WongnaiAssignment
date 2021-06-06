package com.example.wongnaiassignment

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wongnaiassignment.Adapter.CoinsItemAdapter
import com.example.wongnaiassignment.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    private lateinit var coinsItemAdapter: CoinsItemAdapter
    private lateinit var viewModel: MainViewModel

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        setUpRecycleView()
        setUpAdapter()
        getInitialData()
        setUpSearchBar()

    }

    private fun setUpSearchBar() {

        binding.searchBar.apply {
            setOnEditorActionListener { view, actionId, keyEvent ->
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(view.windowToken, 0)
                    view.clearFocus()
                }
                false
            }

            addTextChangedListener(object : TextWatcher {

                override fun beforeTextChanged(query: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    lifecycleScope.launch {
                        viewModel.getListData().collectLatest {
                            coinsItemAdapter.submitData(lifecycle, PagingData.empty())

                        }
                    }
                }

                override fun onTextChanged(query: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    lifecycleScope.launch {
                        viewModel.getListData(query = query.toString()).collectLatest {
                            coinsItemAdapter.submitData(it)

                        }
                    }
                }

                override fun afterTextChanged(query: Editable?) {
                    if (query.isNullOrEmpty()) {
                        lifecycleScope.launch {
                            viewModel.getListData(sort = "coinranking").collectLatest {
                                coinsItemAdapter.submitData(it)

                            }
                        }
                    }
                }


            })
        }
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

    private fun getInitialData() {
        lifecycleScope.launchWhenCreated {
            viewModel.getListData().collectLatest {
                coinsItemAdapter.submitData(it)

            }
        }
    }
}

