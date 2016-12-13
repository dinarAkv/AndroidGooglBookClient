package com.bignerdranch.android.googlebookclient.Models;

import com.google.gson.annotations.SerializedName;



public class IndustryIdentifiers {



    @SerializedName("type")
    private String type;

    @SerializedName("identifier")
    private String identifier;


    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
