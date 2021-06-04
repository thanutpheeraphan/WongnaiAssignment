package com.example.wongnaiassignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wongnaiassignment.Adapter.CoinsItemAdapter
import com.example.wongnaiassignment.Adapter.CoinsPagingAdapter
import com.example.wongnaiassignment.Adapter.FifthCoinAdapter
import com.example.wongnaiassignment.Adapter.NormalCoinAdapter
import com.example.wongnaiassignment.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collectLatest


class MainActivity : AppCompatActivity(){

//	private lateinit var coinsPagingAdapter: CoinsPagingAdapter
	private lateinit var coinsItemAdapter: CoinsItemAdapter
//	private lateinit var normalCoinAdapter: NormalCoinAdapter
//	private lateinit var fifthCoinAdapter: FifthCoinAdapter

	private val binding: ActivityMainBinding by lazy {
		ActivityMainBinding.inflate(layoutInflater)
	}

//	private val concatAdapter: ConcatAdapter by lazy {
//		val config = ConcatAdapter.Config.Builder().apply {
//			setIsolateViewTypes(false)
//		}.build()
//		ConcatAdapter(config, normalCoinAdapter , fifthCoinAdapter)
//	}



	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(binding.root)

//		val view = binding.root
//		setContentView(view)

		setUpRecycleView()
		setUpViewModel()
	}

	private fun setUpRecycleView() {

		binding.coinRecycleView.apply {
			layoutManager = LinearLayoutManager(this@MainActivity)
			val decoration  = DividerItemDecoration(applicationContext, DividerItemDecoration.VERTICAL)
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