package com.bignerdranch.android.googlebookclient.Models;

import com.google.gson.annotations.SerializedName;



public class ReadingModes {

    @SerializedName("text")
    private Boolean text;



    @SerializedName("image")
    private Boolean image;








    public Boolean isImage() {
        return image;
    }

    public void setImage(Boolean image) {
        this.image = image;
    }

    public Boolean isText() {
        return text;
    }

    public void setText(Boolean text) {
        this.text = text;
    }
}
