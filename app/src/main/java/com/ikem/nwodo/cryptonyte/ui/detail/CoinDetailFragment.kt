package com.ikem.nwodo.cryptonyte.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs

import com.ikem.nwodo.cryptonyte.R
import com.ikem.nwodo.cryptonyte.databinding.CoinDetailFragmentBinding
import com.ikem.nwodo.cryptonyte.utils.CoinListHandler
import com.ikem.nwodo.cryptonyte.utils.CustomLayoutManager
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class CoinDetailFragment : DaggerFragment(), CoinListHandler {

    private lateinit var viewModel: CoinDetailViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var binding: CoinDetailFragmentBinding

    private val adapter: CoinDetailAdapter = CoinDetailAdapter(this)
    private val args: CoinDetailFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.coin_detail_fragment, container, false)

        // Toolbar
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.toolbar_coin_detail)
        // MaterialToggleButtonGroup listener
       /* binding.toggleButtonGroup.addOnButtonCheckedListener { _, _, _ ->
            when(binding.toggleButtonGroup.checkedButtonId){
                R.id.day_history -> viewModel.coinHistory24h.observe(viewLifecycleOwner, Observer { binding.coinHistory = it. })
                *//*R.id.week_history -> viewModel.coinHistory7d.observe(viewLifecycleOwner, Observer { binding.historyData = it.data })
                R.id.month_history -> viewModel.coinHistory30d.observe(viewLifecycleOwner, Observer { binding.historyData = it.data })
                R.id.year_history -> viewModel.coinHistory1y.observe(viewLifecycleOwner, Observer { binding.historyData = it.data })*//*
            }
        }*/
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory).get(CoinDetailViewModel::class.java)
        viewModel.setId(args.coinId)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.recyclerView.layoutManager = CustomLayoutManager(context)
        binding.recyclerView.scrollToPosition(args.coinId)


        /*viewModel.coins.observe(viewLifecycleOwner, Observer { adapter.submitList(it.data) })
        viewModel.singleCoin.observe(viewLifecycleOwner, Observer { binding.coin = it.data })
        viewModel.coinHistory24h.observe(viewLifecycleOwner, Observer {
            binding.coinHistory = it.data?.history
        })*/

        binding.recyclerView.adapter = adapter




    }

    // Not needed here
    override fun onCoinHistoryClick(coinId: Int) {
    }

    override fun onCoinClick(coinId: Int) {
       /* viewModel.setId(id)
        viewModel.singleCoin.observe(viewLifecycleOwner, Observer { binding.coin = it.data })
        viewModel.coinHistory24h.observe(viewLifecycleOwner, Observer { binding.coinHistory = it.data?.history })*/
    }

}
