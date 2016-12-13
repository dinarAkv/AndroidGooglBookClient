package com.bignerdranch.android.googlebookclient.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;



public class Items {


    @SerializedName("kind")
    @Expose
    private String kind;


    @SerializedName("id")
    @Expose
    private String id;


    @SerializedName("etag")
    @Expose
    private String etag;



    @SerializedName("selfLink")
    @Expose
    private String selfLink;


    @SerializedName("volumeInfo")
    private  VolumeInfo volumeInfo;

    @SerializedName("saleInfo")
    private SaleInfo saleInfo;

    @SerializedName("accessInfo")
    private AccessInfo accessInfo;










    public AccessInfo getAccessInfo() {
        return accessInfo;
    }

    public void setAccessInfo(AccessInfo accessInfo) {
        this.accessInfo = accessInfo;
    }

    public String getEtag() {
        return etag;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public SaleInfo getSaleInfo() {
        return saleInfo;
    }

    public void setSaleInfo(SaleInfo saleInfo) {
        this.saleInfo = saleInfo;
    }

    public String getSelfLink() {
        return selfLink;
    }

    public void setSelfLink(String selfLink) {
        this.selfLink = selfLink;
    }

    public VolumeInfo getVolumeInfo() {
        return volumeInfo;
    }

    public void setVolumeInfo(VolumeInfo volumeInfo) {
        this.volumeInfo = volumeInfo;
    }
}
