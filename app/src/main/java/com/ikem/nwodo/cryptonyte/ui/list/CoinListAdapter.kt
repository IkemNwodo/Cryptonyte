package com.ikem.nwodo.cryptonyte.ui.list

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ikem.nwodo.cryptonyte.BR
import com.ikem.nwodo.cryptonyte.R
import com.ikem.nwodo.cryptonyte.databinding.CoinListRvCardBinding
import com.ikem.nwodo.cryptonyte.data.local.db.model.Coin
import com.ikem.nwodo.cryptonyte.utils.CoinListHandler

class CoinListAdapter(val coinListListHandler: CoinListHandler) :
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

        val layoutInflater = LayoutInflater.from(parent.context)
        //val binding = CardBinding.inflate(LayoutInflater.from(parent.context))
        val binding = DataBindingUtil.inflate<CoinListRvCardBinding>(layoutInflater,
               R.layout.coin_list_rv_card, parent, false)
        return CoinViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: CoinViewHolder, position: Int) {
        val coin = getItem(position)

        holder.binding?.setVariable(BR.coin, coin)
        Log.d("coinHistory", "${coin.histories}")
    }

    inner class CoinViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        internal var binding:CoinListRvCardBinding?

        init {
            itemView.setOnClickListener(this)
            binding = DataBindingUtil.bind(itemView)
        }
        override fun onClick(v: View?) {
            coinListListHandler.onCoinClick(getItem(adapterPosition).id)
        }
    }
}
