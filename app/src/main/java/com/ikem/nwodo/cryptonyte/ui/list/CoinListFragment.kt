package com.ikem.nwodo.cryptonyte.ui.list

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager

import com.ikem.nwodo.cryptonyte.R
import com.ikem.nwodo.cryptonyte.databinding.CoinListFragmentBinding
import com.ikem.nwodo.cryptonyte.db.model.Data
import com.ikem.nwodo.cryptonyte.db.model.History
import com.ikem.nwodo.cryptonyte.network.api.CoinService
import com.ikem.nwodo.cryptonyte.ui.CoinListAdapter
import com.ikem.nwodo.cryptonyte.utils.CoinClickListener
import com.ikem.nwodo.cryptonyte.utils.Resource
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class CoinListFragment : DaggerFragment(), CoinClickListener{

    companion object {
        fun newInstance() = CoinListFragment()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var viewModel: CoinListViewModel

    lateinit var binding: CoinListFragmentBinding

    @Inject
    lateinit var coinService: CoinService

    private val coinAdapter: CoinListAdapter = CoinListAdapter(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.coin_list_fragment, container, false)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(CoinListViewModel::class.java)

        binding.lifecycleOwner = this
        binding.coinListRecycler.layoutManager = LinearLayoutManager(context)

        viewModel.loadCoins().observe(viewLifecycleOwner, Observer { coinAdapter.submitList(it.data)})

        binding.coinListRecycler.adapter = coinAdapter

    }

    override fun onCoinHistoryListener(id: Int) {
        viewModel.loadCoinHistory(id).observe(viewLifecycleOwner, Observer(fun(_coinHistory: Resource<Data>) {
            CoinListAdapter.coinHistory = _coinHistory.data?.coinHistory
            Log.d("Coin History", "${CoinListAdapter.coinHistory?.size}")

            //Log.i("Coin History", "${_coinHistory.data == null}")
        }))

    }

    override fun onCoinClickListener(id: Int) {
        //val action = CoinListFragmentDirections.actionCoinListFragmentToCoinDetailFragment(id)
        //findNavController().navigate(action)
    }


}
