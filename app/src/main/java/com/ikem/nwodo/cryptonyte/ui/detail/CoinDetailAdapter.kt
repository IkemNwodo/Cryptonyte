package com.ikem.nwodo.cryptonyte.ui.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ikem.nwodo.cryptonyte.BR
import com.ikem.nwodo.cryptonyte.R
import com.ikem.nwodo.cryptonyte.databinding.CoinDetailRvCardBinding
import com.ikem.nwodo.cryptonyte.data.local.db.model.Coin
import com.ikem.nwodo.cryptonyte.utils.CoinClickListener

class CoinDetailAdapter(val coinClickListener: CoinClickListener) :
        ListAdapter<Coin, CoinDetailAdapter.CoinViewHolder>(object : DiffUtil.ItemCallback<Coin>() {
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
        val binding = DataBindingUtil.inflate<CoinDetailRvCardBinding>(layoutInflater,
                R.layout.coin_detail_rv_card, parent, false)
        return CoinViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: CoinViewHolder, position: Int) {
        val coin = getItem(position)

        holder.binding?.setVariable(BR.coin, coin)
        holder.binding?.executePendingBindings()
    }


    inner class CoinViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        internal var binding: CoinDetailRvCardBinding?

        init {
            itemView.setOnClickListener(this)
            binding = DataBindingUtil.bind(itemView)
        }
        override fun onClick(v: View?) {
            coinClickListener.onCoinClickListener(getItem(adapterPosition).id)
        }
    }
}