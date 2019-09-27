package com.ikem.nwodo.cryptonyte.ui.detail

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider

import com.ikem.nwodo.cryptonyte.R
import com.ikem.nwodo.cryptonyte.databinding.CoinDetailFragmentBinding
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class CoinDetailFragment : DaggerFragment() {

    companion object {
        fun newInstance() = CoinDetailFragment()
    }

    private lateinit var viewModel: CoinDetailViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var binding: CoinDetailFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.coin_detail_fragment, container, false)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(CoinDetailViewModel::class.java)

        binding.lifecycleOwner = viewLifecycleOwner


    }

}
