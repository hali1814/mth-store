package com.example.mthshop.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.example.mthshop.R;
import com.example.mthshop.adapter.MyBillAdapter;
import com.example.mthshop.api.APIService;
import com.example.mthshop.databinding.ActivityMyProductBinding;
import com.example.mthshop.dialog.NotificationDiaLog;
import com.example.mthshop.model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyProductActivity extends AppCompatActivity {
    private ActivityMyProductBinding thisActivity;
    private List<Product> listProduct;
    private MyBillAdapter myBillAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        thisActivity = ActivityMyProductBinding.inflate(getLayoutInflater());
        setContentView(thisActivity.getRoot());
        NotificationDiaLog.showProgressBar(this);
        callMyProduct();
    }

    private void callMyProduct() {
        APIService.appService.callMyProduct(LoginActivity.userCurrent.getUser()).enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                listProduct = response.body();

                if (listProduct != null && listProduct.size() != 0) {
                    setRecyclerView();
                }
                NotificationDiaLog.dismissProgressBar();
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                NotificationDiaLog.dismissProgressBar();
            }
        });
    }
    private void setRecyclerView() {
        NotificationDiaLog.dismissProgressBar();
        thisActivity.aMyProductTvEmpty.setVisibility(View.GONE);
        myBillAdapter = new MyBillAdapter(listProduct, this, 6);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        thisActivity.aMyProductRecyclerView.setHasFixedSize(true);
        thisActivity.aMyProductRecyclerView.setLayoutManager(linearLayoutManager);
        thisActivity.aMyProductRecyclerView.setAdapter(myBillAdapter);
    }


}