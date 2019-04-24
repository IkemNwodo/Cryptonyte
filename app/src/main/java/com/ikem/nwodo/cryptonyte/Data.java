package com.ikem.nwodo.cryptonyte;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Data {

    @SerializedName("coins")
    @Expose
    private ArrayList<Coin> coins = null;

    @SerializedName("history")
    @Expose
    private ArrayList<History> coinHistory = null;

    @SerializedName("change")
    @Expose
    private Double change;

    public ArrayList<Coin> getCoins() {
        return coins;
    }

    public ArrayList<History> getCoinHistory() {
        return coinHistory;
    }
}

