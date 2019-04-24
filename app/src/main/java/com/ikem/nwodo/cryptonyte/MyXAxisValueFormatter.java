package com.ikem.nwodo.cryptonyte;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class MyXAxisValueFormatter implements IAxisValueFormatter {

    private final SimpleDateFormat mFormat = new SimpleDateFormat("hh a", Locale.ENGLISH);

    @Override
    public String getFormattedValue(float value, AxisBase axis) {

        long millis =  TimeUnit.MINUTES.toMillis((long) value);
        return mFormat.format(new Date(millis));
    }
}
