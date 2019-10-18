package com.ikem.nwodo.cryptonyte.ui.detail

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.button.MaterialButtonToggleGroup

import com.ikem.nwodo.cryptonyte.R
import com.ikem.nwodo.cryptonyte.databinding.CoinDetailFragmentBinding
import com.ikem.nwodo.cryptonyte.db.model.Data
import com.ikem.nwodo.cryptonyte.ui.list.CoinListAdapter
import com.ikem.nwodo.cryptonyte.utils.CoinClickListener
import com.ikem.nwodo.cryptonyte.utils.Resource
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class CoinDetailFragment : DaggerFragment(), CoinClickListener {

    private lateinit var viewModel: CoinDetailViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var binding: CoinDetailFragmentBinding

    private val adapter: CoinDetailAdapter = CoinDetailAdapter(this)
    private val args: CoinDetailFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.coin_detail_fragment, container, false)

        // MaterialToggleButtonGroup listener
        binding.toggleButtonGroup.addOnButtonCheckedListener { group, checkedId, isChecked ->
            if (R.id.day_history == checkedId && binding.dayHistory.isChecked) {
                viewModel.coinHistory24h.observe(viewLifecycleOwner, Observer { binding.historyData = it.data })
            }
            else if (R.id.week_history == checkedId && binding.weekHistory.isChecked) {
                viewModel.coinHistory7d.observe(viewLifecycleOwner, Observer { binding.historyData = it.data })
            }
            else if (R.id.month_history == checkedId && binding.monthHistory.isChecked) {
                viewModel.coinHistory30d.observe(viewLifecycleOwner, Observer { binding.historyData = it.data })
            }
            else if (R.id.year_history == checkedId && binding.yearHistory.isChecked) {
                viewModel.coinHistory1y.observe(viewLifecycleOwner, Observer { binding.historyData = it.data })
            }
        }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(CoinDetailViewModel::class.java)
        viewModel.setId(args.coinId)

        binding.lifecycleOwner = this
        binding.recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        viewModel.coins.observe(viewLifecycleOwner, Observer { adapter.submitList(it.data) })
        viewModel.singleCoin.observe(viewLifecycleOwner, Observer { binding.coin = it.data })
        viewModel.coinHistory24h.observe(viewLifecycleOwner, Observer<Resource<Data>> {
            binding.historyData = it.data
        })

        binding.recyclerView.adapter = adapter
        binding.recyclerView.scrollToPosition(args.coinId)


    }

    // Not needed here
    override fun onCoinHistoryListener(id: Int) {
    }

    override fun onCoinClickListener(id: Int) {
        viewModel.setId(id)
        viewModel.singleCoin.observe(viewLifecycleOwner, Observer { binding.coin = it.data })
        viewModel.coinHistory24h.observe(viewLifecycleOwner, Observer { binding.historyData = it.data })
    }

}
