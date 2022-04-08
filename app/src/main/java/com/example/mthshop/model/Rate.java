package com.example.mthshop.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Rate {
    private int idRate;
    @SerializedName("Date")
    private String date;
    @SerializedName("Content")
    private String content;
    @SerializedName("RateStar")
    private int rateStar;
    @SerializedName("Status")
    private String status;
    @SerializedName("Owner")
    private String owner;
    private int idProduct;

    public Rate(int idRate, String date, String content, int rateStar, String status, String owner, int idProduct) {
        this.idRate = idRate;
        this.date = date;
        this.content = content;
        this.rateStar = rateStar;
        this.status = status;
        this.owner = owner;
        this.idProduct = idProduct;
    }

    public Rate() {
    }

    public int getIdRate() {
        return idRate;
    }

    public void setIdRate(int idRate) {
        this.idRate = idRate;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getRateStar() {
        return rateStar;
    }

    public void setRateStar(int rateStar) {
        this.rateStar = rateStar;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    @Override
    public String toString() {
        return "Rate{" +
                "idRate=" + idRate +
                ", date=" + date +
                ", content='" + content + '\'' +
                ", rateStar=" + rateStar +
                ", status='" + status + '\'' +
                ", owner='" + owner + '\'' +
                ", idProduct=" + idProduct +
                '}';
    }
}
