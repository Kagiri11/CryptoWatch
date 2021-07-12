package com.example.cryptowatch.ui.fragments.coinDetails

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.cryptowatch.R
import com.example.cryptowatch.databinding.FragmentCoinDetailsBinding

class CoinDetailsFragment : Fragment() {
    private val args : CoinDetailsFragmentArgs by navArgs()

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding : FragmentCoinDetailsBinding=DataBindingUtil.inflate(inflater,R.layout.fragment_coin_details,container,false)
        val coin = args.coin
        Glide.with(requireActivity()).load(coin.image).into(binding.ivCoinSymbol)
        binding.tvCoinName.text=coin.name
        binding.tvCoinPrice.text="$ ${coin.current_price}"
        val change = coin.price_change_24h
        if(change > 0) {
            binding.ivCoinPriceDirection.setImageResource(R.drawable.ic_price_up)
        } else if (change < 0) {
            binding.ivCoinPriceDirection.setImageResource(R.drawable.ic_price_down)
        } else binding.ivCoinPriceDirection.visibility = View.GONE
        return binding.root
    }
}