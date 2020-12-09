package com.ikem.nwodo.cryptonyte.ui.list

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getColor
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.snackbar.Snackbar
import com.ikem.nwodo.cryptonyte.R
import com.ikem.nwodo.cryptonyte.databinding.CoinListFragmentBinding
import com.ikem.nwodo.cryptonyte.data.local.db.model.Coin
import com.ikem.nwodo.cryptonyte.data.local.db.model.CoinsWithHistory
import com.ikem.nwodo.cryptonyte.data.local.db.model.Data
import com.ikem.nwodo.cryptonyte.data.remote.network.api.CoinService
import com.ikem.nwodo.cryptonyte.utils.CoinClickListener
import com.ikem.nwodo.cryptonyte.utils.ConnectivityReceiver
import com.ikem.nwodo.cryptonyte.utils.Resource
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class CoinListFragment : DaggerFragment(), CoinClickListener, SwipeRefreshLayout.OnRefreshListener {


    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject
    lateinit var coinService: CoinService
    @Inject
    lateinit var connectivityReceiver: ConnectivityReceiver

    private lateinit var viewModel: CoinListViewModel

    lateinit var binding: CoinListFragmentBinding

    private val coinAdapter: CoinListAdapter = CoinListAdapter(this)

    private var isConnected = false
    private var isLoading = true

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.coin_list_fragment, container, false)

        //(activity as AppCompatActivity).setSupportActionBar(binding.root.app_bar)
        binding.swipeRefresh.setOnRefreshListener(this)
        binding.swipeRefresh.setColorSchemeColors(getColor(requireContext(), R.color.colorPrimary),
                getColor(requireContext(), R.color.colorAccent),
                getColor(requireContext(), R.color.colorPrimaryDark)
       )

        connectivityReceiver.observe(viewLifecycleOwner, Observer { t ->
            run {
                if (t.isConnected) {
                    isConnected = true
                    viewModel.refresh()
                }
                else{
                    showSnackbar("Enable connection for new update!")
                }
            }
        })
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory).get(CoinListViewModel::class.java)

        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.toolbar_coin_list)

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            coinListRecycler.layoutManager = LinearLayoutManager(context)
            coinListRecycler.adapter = coinAdapter
        }

    }

    override fun onResume() {
        super.onResume()

        viewModel.coins.observe(viewLifecycleOwner, Observer(fun(coinResource: Resource<List<Coin>>){
            binding.resource = coinResource
            when(coinResource){
                is Resource.Success -> {coinAdapter.submitList(coinResource.data)
                                        isLoading = false
                }
            }

        }))
    }

    fun showSnackbar(message: String){
        Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG)
                .setAction(getString(R.string.retry)) { snackBarRetryAction() }
                .show()
    }

    private fun snackBarRetryAction(){
        if(isConnected){
            refetch()
        }
    }

    override fun onCoinClickListener(id: Int) {
        val action = CoinListFragmentDirections.actionCoinListFragmentToCoinDetailFragment(id)
        findNavController().navigate(action)
    }

    override fun onCoinHistoryListener(id: Int) {}

    override fun onRefresh() {
        if (isConnected){
            refetch()
            binding.swipeRefresh.isRefreshing = isLoading
        }
        binding.swipeRefresh.isRefreshing = false
    }

    private fun refetch() {
        viewModel.refresh()
    }

}
