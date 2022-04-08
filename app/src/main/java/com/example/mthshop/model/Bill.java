package com.example.mthshop.model;

import android.util.Log;

import com.google.gson.annotations.SerializedName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Bill {
    private int idBill;
    @SerializedName("Total")
    private double total;
    private String date;
    @SerializedName("Status")
    private double status;
    @SerializedName("Owner")
    private String owner;

    public Bill(int idBill, double total, String date, double status, String owner) {
        this.idBill = idBill;
        this.total = total;
        this.status = status;
        this.owner = owner;
        formatDate(date);
    }

    public Bill() {

    }

    public int getIdBill() {
        return idBill;
    }

    public void setIdBill(int idBill) {
        this.idBill = idBill;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getStatus() {
        return status;
    }

    public void setStatus(double status) {
        this.status = status;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "idBill=" + idBill +
                ", total=" + total +
                ", date=" + date +
                ", status=" + status +
                ", owner=" + owner +
                '}';
    }

    private void formatDate(String date) {
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy");
        try {
            Date tmp = inputFormat.parse(date);
            String formattedDate = outputFormat.format(tmp);
            this.date = formattedDate;
        }catch (Exception ex) {
            Log.e("date", "Sai dinh dang");
        }

    }
}
