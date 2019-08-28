package com.ikem.nwodo.cryptonyte.ui

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.mikephil.charting.charts.LineChart
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou
import com.ikem.nwodo.cryptonyte.R
import com.ikem.nwodo.cryptonyte.databinding.CardBinding
import com.ikem.nwodo.cryptonyte.db.Coin
import com.ikem.nwodo.cryptonyte.utils.CoinClickListener

class CoinListAdapter(val coinClickListener: CoinClickListener):
        ListAdapter<Coin, CoinListAdapter.CoinViewHolder>(object : DiffUtil.ItemCallback<Coin>() {
            override fun areItemsTheSame(oldItem: Coin, newItem: Coin): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Coin, newItem: Coin): Boolean {
               return oldItem == newItem
            }
        })
{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinViewHolder {
        //val v = LayoutInflater.from(parent.context).inflate(R.layout.card, parent, false)
        val binding = DataBindingUtil.inflate<CardBinding>(
                LayoutInflater.from(parent.context), R.layout.card, parent, false
        )
        return CoinViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: CoinViewHolder, position: Int) {
        val coin = getItem(position)

        holder.binding.coin = coin
        holder.binding.executePendingBindings()
    }



    inner class CoinViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        var binding: CardBinding

        init {
            binding = CardBinding.bind(itemView)
        }
    }

}