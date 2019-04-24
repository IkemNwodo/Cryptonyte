package com.ikem.nwodo.cryptonyte;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class History implements Parcelable
{

    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("timestamp")
    @Expose
    private Integer timestamp;
    public final static Parcelable.Creator<History> CREATOR = new Creator<History>() {


        @SuppressWarnings({
                "unchecked"
        })
        public History createFromParcel(Parcel in) {
            return new History(in);
        }

        public History[] newArray(int size) {
            return (new History[size]);
        }

    }
            ;

    protected History(Parcel in) {
        this.price = ((String) in.readValue((String.class.getClassLoader())));
        this.timestamp = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     *
     */
    public History() {
    }

    /**
     *
     * @param timestamp
     * @param price
     */
    public History(String price, Integer timestamp) {
        super();
        this.price = price;
        this.timestamp = timestamp;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Integer getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Integer timestamp) {
        this.timestamp = timestamp;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(price);
        dest.writeValue(timestamp);
    }

    public int describeContents() {
        return 0;
    }

}
