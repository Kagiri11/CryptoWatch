package com.example.cryptowatch.ui.fragments.coins

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.cryptowatch.R
import com.example.cryptowatch.databinding.FragmentCoinsBinding
import com.example.cryptowatch.repositories.MainRepository
import com.example.cryptowatch.ui.adapters.CoinsAdapter
import kotlinx.android.synthetic.main.fragment_coins.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CoinsFragment : Fragment() {
    lateinit var viewModel: CoinsViewModel
    lateinit var viewModelFactory: CoinsViewModelFactory
    lateinit var binding: FragmentCoinsBinding
    private val coinsAdapter = CoinsAdapter()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_coins, container, false)
        coinsAdapter.setOnCoinClickListener {
            val bundle = Bundle().apply {
                putSerializable("coin",it)
            }
            findNavController().navigate(R.id.action_coinsFragment_to_coinDetailsFragment,bundle)
        }
        val repository = MainRepository()
        val application = requireNotNull(this.activity).application
        viewModelFactory = CoinsViewModelFactory(repository, application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(CoinsViewModel::class.java)
        fetchData()
        viewModel.coins.observe(viewLifecycleOwner, { respons ->

            coinsAdapter.differ.submitList(respons.body())
            val singleCoin = respons.body()!!.random()
            binding.tvCoinAbbr.text = singleCoin.symbol
            binding.tvCoinName.text = singleCoin.name
            binding.tvCoinPrice.text = singleCoin.current_price.toString()
            val change = singleCoin.price_change_24h
            if (change > 0) {
                binding.ivCoinPriceDirection.setImageResource(R.drawable.ic_price_up)
            } else if (change < 0) {
                binding.ivCoinPriceDirection.setImageResource(R.drawable.ic_price_down)
            } else binding.ivCoinPriceDirection.visibility = GONE
            Glide.with(requireActivity()).load(singleCoin.image).into(iv_coin_symbol)
            binding.rvCoins.adapter = coinsAdapter


        })

        return binding.root
    }

    private fun fetchData() = lifecycleScope.launch {
        while (true) {
            viewModel.fetchCoinMarketData()
            delay(60000)
        }
    }

}