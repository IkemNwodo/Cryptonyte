package com.ikem.nwodo.cryptonyte.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.util.Log
import androidx.lifecycle.LiveData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ConnectivityReceiver @Inject constructor(val context: Context) : LiveData<ConnectionModel>() {
    override fun onInactive() {
        super.onInactive()
    }

    override fun onActive() {
        super.onActive()
        val filter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        context.registerReceiver(networkReceiver, filter)
    }

    private val networkReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            try {
                if (intent.extras != null) {
                    val activeNetwork = intent.extras!!.get(ConnectivityManager.EXTRA_NETWORK_INFO) as NetworkInfo
                    val isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting

                    if (isConnected) {
                        when (activeNetwork.type) {
                            ConnectivityManager.TYPE_WIFI -> postValue(ConnectionModel(0, true))
                            ConnectivityManager.TYPE_MOBILE -> postValue(ConnectionModel(0, true))
                        }
                    } else {
                        postValue(ConnectionModel(0, false))
                    }
                }

            } catch (e: Exception) {
                Log.e("Catched Error!", "Not able to connect to Internet!")
            }

        }
    }

}