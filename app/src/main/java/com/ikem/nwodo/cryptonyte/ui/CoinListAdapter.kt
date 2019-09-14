package com.ikem.nwodo.cryptonyte.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ikem.nwodo.cryptonyte.databinding.CardBinding
import com.ikem.nwodo.cryptonyte.db.model.Coin
import com.ikem.nwodo.cryptonyte.utils.CoinClickListener

class CoinListAdapter(val coinClickListener: CoinClickListener) :
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
        Log.d("coin", "recycler")

        //val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CardBinding.inflate(LayoutInflater.from(parent.context))
        //val binding = DataBindingUtil.inflate<CardBinding>(layoutInflater,
          //      R.layout.card, parent, false, dataBindingComponent)
        return CoinViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CoinViewHolder, position: Int) {
        val coin = getItem(position)

        holder.binding.coin = coin
        //holder.binding.executePendingBindings()
    }

    inner class CoinViewHolder constructor(val binding: CardBinding): RecyclerView.ViewHolder(binding.root)
}