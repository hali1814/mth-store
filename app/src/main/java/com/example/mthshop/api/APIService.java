package com.example.mthshop.api;

import com.example.mthshop.model.Bill;
import com.example.mthshop.model.BillDetails;
import com.example.mthshop.model.Category;
import com.example.mthshop.model.Product;
import com.example.mthshop.model.Rate;
import com.example.mthshop.model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIService {
    public static String baseUrl = "http://nth.congtydacap.club/";

    APIService appService = new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).build().create(APIService.class);

    //users
    @GET("api/users")
    Call<List<User>> callAllUsers();

    @GET("api/user-info")
    Call<List<User>> callAllUserInfo();

    @GET("api/users/{user}")
    Call<User> callAllUsers(@Path("user") String user);

    //bill
    @GET("api/bills")
    Call<List<Bill>> callAllBills();

    //bill  details
    @GET("api/bill-details")
    Call<List<BillDetails>> callAllBillDetails();

    @GET("api/bill-details/product/{idProduct}")
    Call<List<BillDetails>> callBillDetailsByIdProduct(@Path("idProduct") int idProduct);

    //category
    @GET("api/category")
    Call<List<Category>> callAllCategory();

    //product
    @GET("api/products")
    Call<List<Product>> callAllProducts();

    @GET("api/products/category/{idCategory}")
    Call<List<Product>> callProductsByIDCategory(@Path("idCategory") int idCategory);
    //rate
    @GET("api/rates")
    Call<List<Rate>> callAllRates();

    @GET("api/rates/products/{idProduct}")
    Call<List<Rate>> callRatesByIdProduct(@Path("idProduct") int idProduct);



}
