package com.ikem.nwodo.cryptonyte.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ikem.nwodo.cryptonyte.databinding.CardBinding
import com.ikem.nwodo.cryptonyte.db.model.Coin
import com.ikem.nwodo.cryptonyte.db.model.History
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
    companion object{
        var coinHistory: List<History>? = listOf()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinViewHolder {

        //val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CardBinding.inflate(LayoutInflater.from(parent.context))
        //val binding = DataBindingUtil.inflate<CardBinding>(layoutInflater,
          //      R.layout.card, parent, false, dataBindingComponent)
        return CoinViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CoinViewHolder, position: Int) {
        val coin = getItem(position)

        coinClickListener.onCoinHistoryListener(coin.id)
        Log.d("Coin History size", "${coinHistory?.size}")
        //Log.i("Coin Id", "${coin.id}")

        holder.binding.coin = coin
        holder.binding.coinHistory = coinHistory

        //holder.binding.executePendingBindings()
    }

    inner class CoinViewHolder constructor(val binding: CardBinding): RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        override fun onClick(v: View?) {
            coinClickListener.onCoinClickListener(getItem(adapterPosition).id)
        }
    }
}