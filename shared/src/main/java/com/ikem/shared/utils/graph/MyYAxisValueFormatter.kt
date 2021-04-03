package com.ikem.nwodo.cryptonyte.utils.graph

import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.formatter.IAxisValueFormatter

class MyYAxisValueFormatter : IAxisValueFormatter {

    override fun getFormattedValue(value: Float, axis: AxisBase): String {
        return "$" + NumberFormatter.graphYAxisFormatter(value)
    }
}
