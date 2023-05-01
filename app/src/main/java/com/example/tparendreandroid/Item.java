package com.example.tparendreandroid;

public class Item {
    private String imageUriString;
    private double doubleValue;
    private String stringValue;

    public Item(String imageUriString, double doubleValue, String stringValue) {
        this.imageUriString = imageUriString;
        this.doubleValue = doubleValue;
        this.stringValue = stringValue;
    }

    public String getImageUriString() {
        return imageUriString;
    }

    public void setImageUriString(String imageUriString) {
        this.imageUriString = imageUriString;
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
