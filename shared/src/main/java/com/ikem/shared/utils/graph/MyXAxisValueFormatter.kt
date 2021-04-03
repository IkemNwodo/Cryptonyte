package com.ikem.nwodo.cryptonyte.utils.graph

import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.formatter.IAxisValueFormatter

import java.sql.Time
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.concurrent.TimeUnit

class MyXAxisValueFormatter : IAxisValueFormatter {

    private val mFormat = SimpleDateFormat("hh a", Locale.ENGLISH)

    override fun getFormattedValue(value: Float, axis: AxisBase): String {

        val millis = TimeUnit.MINUTES.toMillis(value.toLong())
        return mFormat.format(Date(millis))
    }
}
