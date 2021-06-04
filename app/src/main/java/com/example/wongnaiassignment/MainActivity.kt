package com.example.wongnaiassignment

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wongnaiassignment.Adapter.CoinsPagingAdapter
import com.example.wongnaiassignment.Repository.Repository
import com.example.wongnaiassignment.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collectLatest


class MainActivity : AppCompatActivity(){


	private lateinit var binding : ActivityMainBinding
	private lateinit var coinsPagingAdapter: CoinsPagingAdapter



	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityMainBinding.inflate(layoutInflater)
		val view = binding.root
		setContentView(view)

		setUpRecycleView()
		setUpViewModel()
	}

	private fun setUpRecycleView() {
		binding.coinRecycleView.apply {
			layoutManager = LinearLayoutManager(this@MainActivity)
			val decoration  = DividerItemDecoration(applicationContext, DividerItemDecoration.VERTICAL)
			addItemDecoration(decoration)
			coinsPagingAdapter = CoinsPagingAdapter()
			adapter = coinsPagingAdapter


		}


	}

	private fun setUpViewModel() {
		val viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
		lifecycleScope.launchWhenCreated {
			viewModel.getListData().collectLatest {
				coinsPagingAdapter.submitData(it)
			}
		}
	}
}