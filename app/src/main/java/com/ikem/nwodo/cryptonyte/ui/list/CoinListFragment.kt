package com.ikem.nwodo.cryptonyte.ui.list

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager

import com.ikem.nwodo.cryptonyte.R
import com.ikem.nwodo.cryptonyte.databinding.CoinListFragmentBinding
import com.ikem.nwodo.cryptonyte.ui.CoinListAdapter
import com.ikem.nwodo.cryptonyte.utils.CoinClickListener
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class CoinListFragment : DaggerFragment(), CoinClickListener{

    override fun onCoinClickListener(id: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object {
        fun newInstance() = CoinListFragment()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var viewModel: CoinListViewModel

    lateinit var binding: CoinListFragmentBinding

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
        val coinAdapter = CoinListAdapter(this)

        viewModel.loadCoins().observe(viewLifecycleOwner, Observer { coinAdapter.submitList(it?.data)})

        binding.coinListRecycler.adapter = coinAdapter

    }

}
