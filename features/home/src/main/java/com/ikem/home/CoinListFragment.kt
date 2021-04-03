package com.ikem.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.getColor
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.*
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.snackbar.Snackbar
import com.ikem.home.CoinListAdapter
import com.ikem.nwodo.cryptonyte.R
import com.ikem.nwodo.cryptonyte.databinding.CoinListFragmentBinding
import com.ikem.nwodo.cryptonyte.data.remote.network.api.CoinService
import com.ikem.nwodo.cryptonyte.utils.CoinListHandler
import com.ikem.nwodo.cryptonyte.utils.ConnectivityReceiver
import com.ikem.nwodo.cryptonyte.utils.Resource
import dagger.android.support.DaggerFragment
import kotlinx.coroutines.launch
import javax.inject.Inject

class CoinListFragment : DaggerFragment(), CoinListHandler {
    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject lateinit var coinService: CoinService
    @Inject lateinit var connectivityReceiver: ConnectivityReceiver
    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(CoinListViewModel::class.java)
    }
    lateinit var binding: CoinListFragmentBinding
    private val coinAdapter = CoinListAdapter(this)
    private var isInternetConnectionPresent = false
    private var isLoading = true

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.toolbar_coin_list)
    }

    override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.coin_list_fragment, container, false)

        setupSwipeRefresh(binding.swipeRefresh)
        setupRecyclerView(binding)
        setupObservers()

        return binding.root
    }

    private fun setupSwipeRefresh(swipeRefresh: SwipeRefreshLayout) {
        setSwipeRefreshListener(swipeRefresh)
        swipeRefresh.setColorSchemeColors(
          getColor(requireContext(), R.color.colorPrimary),
          getColor(requireContext(), R.color.colorAccent),
          getColor(requireContext(), R.color.colorPrimaryDark)
        )
    }

    private fun setSwipeRefreshListener(swipeRefresh: SwipeRefreshLayout) {
        swipeRefresh.setOnRefreshListener {
            if (isInternetConnectionPresent) {
                refetch()
                swipeRefresh.isRefreshing = isLoading
            }
            swipeRefresh.isRefreshing = false
        }
    }

    private fun refetch() {
        // refetch() is doing nothing. It is returning LiveData<Resource<List<Coin>>> which is not getting used anywhere.
        viewModel.refresh()
    }

    private fun setupRecyclerView(binding: CoinListFragmentBinding) {
        binding.apply {
            coinListRecyclerView.layoutManager = LinearLayoutManager(context)
            coinListRecyclerView.adapter = coinAdapter
        }
    }

    private fun setupObservers() {
        setConnectivityReceiverObserver()
        setupLiveDataObservers()
    }

    private fun setConnectivityReceiverObserver() {
        connectivityReceiver.observe(viewLifecycleOwner) { connectionModel ->
            lifecycleScope.launch {
                if (connectionModel.isConnected) {
                    isInternetConnectionPresent = true
                    viewModel.refresh()
                }
                else {
                    Snackbar
                      .make(binding.root, "Enable connection for new update!", Snackbar.LENGTH_LONG)
                      .setAction(getString(R.string.retry)) { checkForConnectivity() }
                      .show()
                }
            }
        }
    }

    private fun setupLiveDataObservers() {
        viewModel.coins.observe(viewLifecycleOwner) { coinResource ->
            when (coinResource) {
                is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                is Resource.Success -> {
                    coinAdapter.submitList(coinResource.data)
                    isLoading = false
                    binding.progressBar.visibility = View.GONE
                }
                is Resource.Error -> binding.progressBar.visibility = View.GONE
            }
        }
    }

    private fun checkForConnectivity() {
        if (isInternetConnectionPresent) refetch()
    }

    override fun onCoinClick(coinId: Int) {
        findNavController()
          .navigate(CoinListFragmentDirections.actionCoinListFragmentToCoinDetailFragment(coinId))
    }

    override fun onCoinHistoryClick(coinId: Int) {}
}
