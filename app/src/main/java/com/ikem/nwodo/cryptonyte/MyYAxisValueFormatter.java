package com.ikem.nwodo.cryptonyte;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

public class MyYAxisValueFormatter implements IAxisValueFormatter {

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        return "$" + NumberFormatter.graphYAxisFormatter(value);
    }
}
