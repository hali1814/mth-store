package com.example.mthshop.model;

public class BillDetails {
    private int idBillDetails;
    private int idBill;
    private int idProduct;


    public BillDetails(int idBillDetails, int idBill, int idProduct) {
        this.idBillDetails = idBillDetails;
        this.idBill = idBill;
        this.idProduct = idProduct;
    }

    public BillDetails() {
    }

    public int getIdBillDetails() {
        return idBillDetails;
    }

    public void setIdBillDetails(int idBillDetails) {
        this.idBillDetails = idBillDetails;
    }

    public int getIdBill() {
        return idBill;
    }

    public void setIdBill(int idBill) {
        this.idBill = idBill;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    @Override
    public String toString() {
        return "BillDetails{" +
                "idBillDetails=" + idBillDetails +
                ", idBill=" + idBill +
                ", idProduct=" + idProduct +
                '}';
    }
}
