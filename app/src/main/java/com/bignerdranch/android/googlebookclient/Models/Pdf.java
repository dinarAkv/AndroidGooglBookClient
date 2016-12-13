package com.bignerdranch.android.googlebookclient.Models;

import com.google.gson.annotations.SerializedName;



public class Pdf {


    @SerializedName("isAvailable")
    private Boolean isAvailable;

    @SerializedName("downloadLink")
    private String downloadLink;


    public String getDownloadLink() {
        return downloadLink;
    }

    public void setDownloadLink(String downloadLink) {
        this.downloadLink = downloadLink;
    }

    public Boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(Boolean available) {
        isAvailable = available;
    }
}
