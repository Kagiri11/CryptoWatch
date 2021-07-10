package com.example.cryptowatch.ui.fragments.coins

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.cryptowatch.R
import com.example.cryptowatch.databinding.FragmentCoinsBinding
import com.example.cryptowatch.repositories.MainRepository
import com.example.cryptowatch.ui.adapters.CoinsAdapter
import com.example.cryptowatch.utils.Resource

class CoinsFragment : Fragment() {
    lateinit var viewModel: CoinsViewModel
    lateinit var viewModelFactory: CoinsViewModelFactory
    lateinit var binding: FragmentCoinsBinding
    val coinsAdapter = CoinsAdapter()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_coins,container,false)

        val repository = MainRepository()
        val application = requireNotNull(this.activity).application
        viewModelFactory = CoinsViewModelFactory(repository,application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(CoinsViewModel::class.java)

        viewModel.coins.observe(viewLifecycleOwner, {respons->

            coinsAdapter.differ.submitList(respons.body())
            val singleCoin = respons.body()!!.random()
                binding.rvCoins.adapter=coinsAdapter
                binding.tvApp1.text=singleCoin.name

        })

        return binding.root
    }

}