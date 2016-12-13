package com.bignerdranch.android.googlebookclient.Models;

import com.google.gson.annotations.SerializedName;



public class Epub {




    @SerializedName("isAvailable")
    public String isAvailable;

    public String getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(String isAvailable) {
        this.isAvailable = isAvailable;
    }
}
