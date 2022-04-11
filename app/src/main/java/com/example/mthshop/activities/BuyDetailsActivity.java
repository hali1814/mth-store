package com.example.mthshop.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.mthshop.R;
import com.example.mthshop.adapter.CartAdapter;
import com.example.mthshop.adapter.OderAdapter;
import com.example.mthshop.api.APIService;
import com.example.mthshop.databinding.ActivityBuyDetailsBinding;
import com.example.mthshop.dialog.NotificationDiaLog;
import com.example.mthshop.fortmat.FortMartData;
import com.example.mthshop.fragment.BuyDetailsFragment;
import com.example.mthshop.model.Bill;
import com.example.mthshop.model.BillDetails;
import com.example.mthshop.model.Product;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BuyDetailsActivity extends AppCompatActivity {
    private ActivityBuyDetailsBinding thisActivity;
    private Product productCurrent;
    private int status; // mua ngay hay mua trogn gio hang
    private List<Product> listProduct;
    private double priceSale;
    private double priceTotal;
    private double priceTotalBill;
    private Bill billCurrent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        thisActivity = ActivityBuyDetailsBinding.inflate(getLayoutInflater());
        setContentView(thisActivity.getRoot());
        //status bar
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        //     //
        productCurrent = (Product) getIntent().getSerializableExtra("product");
        status = getIntent().getIntExtra("buyNow", 3);
        //tinh toan bill
        setBill();
        //call fragment
        getSupportFragmentManager().beginTransaction().add(R.id.aBuyDetails_frameLayout, new BuyDetailsFragment()).commit();


        //

        //listener
        thisActivity.aBuyDetailsBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //listener dat hang
        thisActivity.aBuyDetailsBtnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NotificationDiaLog.dismissProgressBar();
                if (status == 0) {// oder trong gio hang

                }else {// mua ngay

                    createBillBuyNow();

                }
            }
        });
    }

    private void createBillBuyNow() {
        Bill tmp = new Bill(0, priceTotalBill, FortMartData.getDateCurrent(), 2, LoginActivity.userCurrent.getUser());
        APIService.appService.postBillCart(tmp).enqueue(new Callback<Bill>() {
            @Override
            public void onResponse(Call<Bill> call, Response<Bill> response) {

            }

            @Override
            public void onFailure(Call<Bill> call, Throwable t) {
                callBillBuyNow();
            }
        });
    }

    private void callBillBuyNow() {
        APIService.appService.callBillInCart(2, LoginActivity.userCurrent.getUser()).enqueue(new Callback<List<Bill>>() {
            @Override
            public void onResponse(Call<List<Bill>> call, Response<List<Bill>> response) {
                try {
                    billCurrent = response.body().get(0);
                    if (billCurrent != null) {
                        createBillDetails();
                    }
                }catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Call<List<Bill>> call, Throwable t) {

            }
        });
    }

    private void createBillDetails() { // thêm các sản phẩm vào bill detais
        for(Product product : listProduct) {
            BillDetails billDetails = new BillDetails(0, billCurrent.getIdBill(), product.getIdProduct(), 2);
            APIService.appService.postBillDetails(billDetails).enqueue(new Callback<BillDetails>() {
                @Override
                public void onResponse(Call<BillDetails> call, Response<BillDetails> response) {


                }

                @Override
                public void onFailure(Call<BillDetails> call, Throwable t) {
                    changeStatusBill(); // chỉnh trạng thái mua ngay thành chờ đợi 2 -> 3
                }
            });
        }

    }

    private void changeStatusBill() {
        billCurrent.setStatus(3);
        Log.e("bullCurrent", billCurrent.toString());
        APIService.appService.putBill(billCurrent).enqueue(new Callback<Bill>() {
            @Override
            public void onResponse(Call<Bill> call, Response<Bill> response) {
                Log.e("put", "thanh cong");
                NotificationDiaLog.dismissProgressBar();
                NotificationDiaLog.showDiaLogValidDate("Mua thành công!", BuyDetailsActivity.this);
                Intent intent = new Intent(BuyDetailsActivity.this, MyBillsActivity.class);
                startActivity(intent);

            }

            @Override
            public void onFailure(Call<Bill> call, Throwable t) {
                Log.e("put", "that bai");
            }
        });
    }


    private void setBill() {

        if (status == 0) {

        } else {
            listProduct = new ArrayList<>();
            listProduct.add(productCurrent);
            double percent = (double) productCurrent.getSale() / 100;
            priceSale = (double) productCurrent.getPrice() * percent;
            priceTotal = productCurrent.getPrice();
            priceTotalBill = (double) productCurrent.getPrice() * (1 - percent);
        }
        thisActivity.aBuyDetailsTvTotal.setText(FortMartData.fortMartTypeDoubleToMoney(priceTotalBill));
    }

    public Product getProductCurrent() {
        return productCurrent;
    }

    public int getStatus() {
        return status;
    }

    public List<Product> getListProduct() {
        return listProduct;
    }

    public double getPriceSale() {
        return priceSale;
    }

    public double getPriceTotal() {
        return priceTotal;
    }

    public double getPriceTotalBill() {
        return priceTotalBill;
    }
}