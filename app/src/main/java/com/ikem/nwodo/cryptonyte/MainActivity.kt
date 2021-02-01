package com.ikem.nwodo.cryptonyte

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.ikem.nwodo.cryptonyte.databinding.ActivityMainBinding
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        setSupportActionBar(binding.appBar)

        binding.appBar.title = ""
    }

}
