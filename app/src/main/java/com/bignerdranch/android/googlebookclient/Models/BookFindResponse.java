package com.bignerdranch.android.googlebookclient.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class BookFindResponse {


    @SerializedName("kind")
    private String kind;

    @SerializedName("totalItems")
    private Integer totalItems;

    @SerializedName("items")
    private List<Items> itemses;









    public List<Items> getItems() {
        return itemses;
    }

    public void setItemses(List<Items> itemses) {
        this.itemses = itemses;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public Integer getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(Integer totalItems) {
        this.totalItems = totalItems;
    }
}
