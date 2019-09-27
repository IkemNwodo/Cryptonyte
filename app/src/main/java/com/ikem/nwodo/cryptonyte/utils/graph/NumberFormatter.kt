package com.ikem.nwodo.cryptonyte.utils.graph

import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*


object NumberFormatter {

    fun currencyFormatter(input: Long): String {
        val nf = NumberFormat.getCurrencyInstance(Locale.US)
        return nf.format(input)
    }

    fun graphYAxisFormatter(input: Float): String {
        val nf = NumberFormat.getInstance()
        return nf.format(input.toInt().toLong())
    }

    @JvmStatic
    fun priceFormatter(input: String): String {
        val nf = DecimalFormat("##,###.00")
        return nf.format(input.toDouble())
    }

    @JvmStatic
    fun coinNameFormatter(input: String): String {
        return if (input.contains(' ')) {
            input.replace(' ', '\n')
        } else {
            input
        }
    }
}
