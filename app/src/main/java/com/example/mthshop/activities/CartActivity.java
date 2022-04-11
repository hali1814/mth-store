package com.example.mthshop.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.example.mthshop.R;
import com.example.mthshop.adapter.CartAdapter;
import com.example.mthshop.api.APIService;
import com.example.mthshop.databinding.ActivityCartBinding;
import com.example.mthshop.dialog.NotificationDiaLog;
import com.example.mthshop.fortmat.FortMartData;
import com.example.mthshop.model.Bill;
import com.example.mthshop.model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartActivity extends AppCompatActivity {
    private ActivityCartBinding thisActivity;
    private CartAdapter cartAdapter;
    private Bill billInCart;
    private List<Product> listProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //status
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.white));
        //
        thisActivity = ActivityCartBinding.inflate(getLayoutInflater());
        setContentView(thisActivity.getRoot());
        //listener back
        thisActivity.aCartImgBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        NotificationDiaLog.showProgressBar(this);
        callBillInCart();


    }

    private void callBillInCart() {
        APIService.appService.callBillInCart(0, LoginActivity.userCurrent.getUser())
                .enqueue(new Callback<List<Bill>>() {
                    @Override
                    public void onResponse(Call<List<Bill>> call, Response<List<Bill>> response) {
                        try {
                            billInCart = response.body().get(0);
                            Log.e("hoho", billInCart.getIdBill() + "");
                            callProductInCart();

                        } catch (Exception e) {
                            billInCart = null;
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Bill>> call, Throwable t) {
                        NotificationDiaLog.dismissProgressBar();
                        Log.e(thisActivity.toString(), t.toString());

                    }
                });
    }

    private void callProductInCart() {
        APIService.appService.callProductInCart(billInCart.getIdBill()).enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {

                listProduct = response.body();
                if (listProduct != null && listProduct.size() != 0)
                    for (int i = 0; i < listProduct.size(); i++) {
                        listProduct.get(i).inBill = billInCart.getIdBill();
                    }

                Log.e("hoho", listProduct.get(0).toString());
                NotificationDiaLog.dismissProgressBar();
                setAdapterRecycleView(false);
                setCheckAll();
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                NotificationDiaLog.dismissProgressBar();
                Log.e(thisActivity.toString(), t.toString());
            }
        });
    }

    private void setAdapterRecycleView(Boolean b) {
        totalBill();

        thisActivity.aCartTvEmpty.setVisibility(View.GONE);
        cartAdapter = new CartAdapter(listProduct, this, b);
        thisActivity.aCartTvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NotificationDiaLog.showProgressBar(CartActivity.this);

                cartAdapter.deleteProductInCart();

            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        thisActivity.aCartRecyclerView.setHasFixedSize(true);
        thisActivity.aCartRecyclerView.setLayoutManager(linearLayoutManager);
        thisActivity.aCartRecyclerView.setAdapter(cartAdapter);
    }

    private void setCheckAll() {
        thisActivity.aCartChkSelectAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (!compoundButton.isPressed()) {
                    return;
                }
                setAdapterRecycleView(b);
            }
        });
    }

    public void referenceCheckAll(boolean b) {
        thisActivity.aCartChkSelectAll.setChecked(b);
    }

    public void totalBill() {
        double total = 0;
        for (Product p : listProduct) {
            double sale = (double) p.getSale() / 100;
            double priceSale = (double) p.getPrice() * (1 - sale);
            total += priceSale;
        }
        if (total == 0) {
            thisActivity.aCartTvEmpty.setVisibility(View.VISIBLE);
            thisActivity.aCartChkSelectAll.setChecked(false);
            checkStatusDelete(0);
        }
        thisActivity.aCartTvTotalPay.setText("Tổng thanh toán \u20AB" + FortMartData.fortMartTypeDoubleToMoney(total));
    }


    public void checkStatusDelete(int i) {
        if (i == 0) {
            thisActivity.aCartTvDelete.setTextColor(getResources().getColor(R.color.second_text));
            thisActivity.aCartTvDelete.setEnabled(false);
        } else {
            thisActivity.aCartTvDelete.setTextColor(getResources().getColor(R.color.main));
            thisActivity.aCartTvDelete.setEnabled(true);
        }
    }

}