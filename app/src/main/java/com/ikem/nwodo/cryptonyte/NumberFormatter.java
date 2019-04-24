package com.ikem.nwodo.cryptonyte;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class NumberFormatter {

    public static String currencyFormatter(long input){
        NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.US);
        return nf.format(input);
    }

    public static String graphYAxisFormatter(float input){
        NumberFormat nf = NumberFormat.getInstance();

        return nf.format((int)input);
    }

}
