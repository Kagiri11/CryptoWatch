package com.example.cryptowatch.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.AsyncListUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cryptowatch.R
import com.example.cryptowatch.databinding.CoinItemBinding
import com.example.cryptowatch.models.CoinItem
import kotlinx.android.synthetic.main.coin_item.view.*

class CoinsAdapter :RecyclerView.Adapter<CoinsAdapter.ViewHolder>() {
    class ViewHolder(private val binding: CoinItemBinding):RecyclerView.ViewHolder(binding.root)

    private val differCallBack = object : DiffUtil.ItemCallback<CoinItem>(){
        override fun areItemsTheSame(oldItem: CoinItem, newItem: CoinItem): Boolean {
            return  oldItem.id==newItem.id
        }

        override fun areContentsTheSame(oldItem: CoinItem, newItem: CoinItem): Boolean {
            return oldItem==newItem
        }

    }

    val differ=AsyncListDiffer(this,differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding : CoinItemBinding = DataBindingUtil.inflate(layoutInflater, R.layout.coin_item,parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val coin = differ.currentList[position]
        holder.itemView.apply {
            tv_rv_coin_abbr.text=coin.symbol
            tv_rv_coin_name.text=coin.name
            tv_rv_coin_price.text="$${coin.current_price}"
            Glide.with(this).load(coin.image).into(iv_rv_coin_image)
        }
    }

    override fun getItemCount(): Int= differ.currentList.size
}