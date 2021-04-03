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
    fun priceFormatter(input: String?): String {
        val nf = DecimalFormat("##,###.00")
        var result = ""
        if (input != null)
            result = nf.format(input.toDouble())

        return result
    }

    @JvmStatic
    fun coinNameFormatter(input: String?): String {
        var result = ""
        if (input != null) result = (if (input.contains(' ')) {
            input.replace(' ', '\n')
        } else {
            input
        }).toString()
        return result
    }
}
