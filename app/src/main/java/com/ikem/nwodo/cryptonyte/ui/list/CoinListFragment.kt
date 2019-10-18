package com.ikem.nwodo.cryptonyte.ui.list

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
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
import com.ikem.nwodo.cryptonyte.db.model.Coin
import com.ikem.nwodo.cryptonyte.db.model.Data
import com.ikem.nwodo.cryptonyte.network.api.CoinService
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

    lateinit var viewModel: CoinListViewModel

    lateinit var binding: CoinListFragmentBinding

    private val coinAdapter: CoinListAdapter = CoinListAdapter(this)

    private var isConnected = false
    private var isLoading = true

   /** lateinit var handler: Handler
    private val runnable = object : Runnable {
        override fun run() {
            viewModel.reFetch("true")

            handler.postDelayed(this, 100000)
        }
    }
*/
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.coin_list_fragment, container, false)

        //(activity as AppCompatActivity).setSupportActionBar(binding.root.app_bar)
        binding.swipeRefresh.setOnRefreshListener(this)
        binding.swipeRefresh.setColorSchemeColors(
                ContextCompat.getColor(context!!, R.color.colorPrimary),
                ContextCompat.getColor(context!!, R.color.colorAccent),
                ContextCompat.getColor(context!!, R.color.colorPrimaryDark)
        )

        connectivityReceiver.observe(viewLifecycleOwner, Observer { t ->
            run {
                if (t.isConnected) {
                    isConnected = true
                    viewModel.reFetch("true")
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
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(CoinListViewModel::class.java)


        binding.lifecycleOwner = viewLifecycleOwner
        binding.coinListRecycler.layoutManager = LinearLayoutManager(context)
        viewModel.loadCoins.observe(viewLifecycleOwner, Observer(fun(coinResource: Resource<List<Coin>>){
            if (coinResource.data != null){
                isLoading = false
                coinAdapter.submitList(coinResource.data)
            }
            binding.resource = coinResource
        }))

        binding.coinListRecycler.adapter = coinAdapter

        //handler = Handler()
    }

    override fun onResume() {
        super.onResume()
        //runnable.run()
        refetch()
    }

    override fun onDestroy() {
        super.onDestroy()
        //handler.removeCallbacks(runnable)
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
    override fun onCoinHistoryListener(id: Int) {
        viewModel.loadCoinHistory(id).observe(viewLifecycleOwner, Observer(fun(_coinHistory: Resource<Data>) {
            _coinHistory.data?.coinHistory?.let { coinAdapter.setCoinHistories(it) }
            //Log.d("Coin History", "${coinAdapter.coinHistory?.size}")

            //Log.i("Coin History", "${_coinHistory.data == null}")
        }))

    }

    override fun onCoinClickListener(id: Int) {
        val action = CoinListFragmentDirections.actionCoinListFragmentToCoinDetailFragment(id)
        findNavController().navigate(action)
    }

    override fun onRefresh() {
        if (isConnected){
            refetch()
            binding.swipeRefresh.isRefreshing = isLoading
        }
        binding.swipeRefresh.isRefreshing = false
    }

    private fun refetch() {
        viewModel.reFetch("true")
    }

}
