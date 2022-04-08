

package com.example.mthshop.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.bumptech.glide.Glide;
import com.example.mthshop.R;
import com.example.mthshop.api.APIService;
import com.example.mthshop.databinding.ActivityDetailsProductBinding;
import com.example.mthshop.dialog.NotificationDiaLog;
import com.example.mthshop.fragment.DetailsProductFragment;
import com.example.mthshop.model.BillDetails;
import com.example.mthshop.model.Product;
import com.example.mthshop.model.Rate;
import com.example.mthshop.model.User;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsProductActivity extends AppCompatActivity {
    private ActivityDetailsProductBinding thisCurrent;
    private Product productCurrent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        thisCurrent = ActivityDetailsProductBinding.inflate(getLayoutInflater());
        //status bar
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        setContentView(thisCurrent.getRoot());

        productCurrent = (Product) getIntent().getSerializableExtra("product");
        //add fragment
        getSupportFragmentManager().beginTransaction().add(R.id.fDetailsProduct_frameLayout, new DetailsProductFragment()).commit();

        thisCurrent.fDetailsProductBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }



    public Product getProductCurrent() {
        return productCurrent;
    }
}