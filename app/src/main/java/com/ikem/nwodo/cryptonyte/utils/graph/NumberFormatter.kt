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

    fun priceFormatter(input: String): String {
        val nf = DecimalFormat.getNumberInstance()
        return nf.format(input.toDouble())
    }
}
