package com.bignerdranch.android.googlebookclient.Models;

import com.google.gson.annotations.SerializedName;



public class SaleInfo {

    @SerializedName("country")
    private String country;

    @SerializedName("saleability")
    private String saleability;

    @SerializedName("isEbook")
    private Boolean isEbook;

    @SerializedName("buyLink")
    private String buyLink;


    public String getBuyLink() {
        return buyLink;
    }

    public void setBuyLink(String buyLink) {
        this.buyLink = buyLink;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Boolean isEbook() {
        return isEbook;
    }

    public void setEbook(Boolean ebook) {
        isEbook = ebook;
    }

    public String getSaleability() {
        return saleability;
    }

    public void setSaleability(String saleability) {
        this.saleability = saleability;
    }
}
