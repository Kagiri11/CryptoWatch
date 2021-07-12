package com.example.cryptowatch.ui.adapters

import android.graphics.Color
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
import com.example.cryptowatch.models.Coins
import kotlinx.android.synthetic.main.coin_item.view.*
import kotlin.math.roundToInt

class CoinsAdapter :RecyclerView.Adapter<CoinsAdapter.ViewHolder>() {
    class ViewHolder(binding: CoinItemBinding):RecyclerView.ViewHolder(binding.root)

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
        val coin= differ.currentList[position]
        holder.itemView.apply {
            tv_rv_coin_abbr.text=coin.symbol
            tv_rv_coin_name.text=coin.name
            val priceChange = coin.price_change_24h
            val x = String.format("%.2f", priceChange)
            val newPrice = "%.2f".format(priceChange).toDouble()
            if(priceChange>0){
                tv_coin_price_change.setTextColor(resources.getColor(R.color.green))
            }else tv_coin_price_change.setTextColor(resources.getColor(R.color.red))
            tv_rv_coin_price.text="$${coin.current_price}"
            tv_coin_price_change.text= x
            Glide.with(this).load(coin.image).into(iv_rv_coin_image)
            setOnClickListener {
                onItemClickListener?.let {
                    it(coin)
                }
            }
        }
    }

    private var onItemClickListener : ((CoinItem)-> Unit)?=null

    fun setOnCoinClickListener(listener:(CoinItem)-> Unit){
        onItemClickListener=listener
    }

    override fun getItemCount(): Int= differ.currentList.size
}