package com.example.tparendreandroid;

import android.os.Parcel;
import android.os.Parcelable;

public class Item {
    private int imageResId;
    private double doubleValue;
    private String stringValue;

    public Item(int imageResId, double doubleValue, String stringValue) {
        this.imageResId = imageResId;
        this.doubleValue = doubleValue;
        this.stringValue = stringValue;
    }

    public int getImageResId() {
        return imageResId;
    }

    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }

    public double getDoubleValue() {
        return doubleValue;
    }

    public void setDoubleValue(double doubleValue) {
        this.doubleValue = doubleValue;
    }

    public String getStringValue() {
        return stringValue;
    }

    public void setStringValue(String stringValue) {
        this.stringValue = stringValue;
    }

}
