package com.example.mthshop.fragment;


import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mthshop.R;
import com.example.mthshop.activities.HeadPhoneActivity;
import com.example.mthshop.activities.LaptopActivity;
import com.example.mthshop.activities.PhoneActivity;
import com.example.mthshop.activities.WatchActivity;
import com.example.mthshop.adapter.ProductAdapter;
import com.example.mthshop.api.APIService;
import com.example.mthshop.databinding.FragmentHomeBinding;
import com.example.mthshop.dialog.NotificationDiaLog;
import com.example.mthshop.model.Product;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding thisFragment;
    private Handler handler;
    private int[] listBanner = {R.drawable.banner3, R.drawable.banner1, R.drawable.banner2, R.drawable.banner};
    private int loadBanner = 0;
    private Animation animation;
    private ProductAdapter productAdapter;
    private List<Product> listALlProducts;
    private List<Product> listSaleProducts;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //status bar
        getActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getActivity().getWindow().setStatusBarColor(ContextCompat.getColor(getContext(),R.color.back_ground));

        //FragmentHomeBinding
        thisFragment = FragmentHomeBinding.inflate(getLayoutInflater(), container, false);
        listSaleProducts = new ArrayList<>();
        //banner slide
        handler = new Handler();
        handler.postDelayed(runnable, 5000);
        //animation banner
        animation = AnimationUtils.loadAnimation(getContext(), R.anim.benner_out);
        setListCategory();
        //set recyclerview san pham giam gia

        NotificationDiaLog.showProgressBar(getContext());


        return thisFragment.getRoot();
    }

    private void setSaleProduct() {
        productAdapter = new ProductAdapter(getActivity(), listSaleProducts);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
        thisFragment.fHomeRecycleViewSaleProduct.setHasFixedSize(true);
        thisFragment.fHomeRecycleViewSaleProduct.setLayoutManager(linearLayoutManager);

        thisFragment.fHomeRecycleViewSaleProduct.setAdapter(productAdapter);
    }
    private void setSuggestProduct() {
        ProductAdapter suggestAdapter = new ProductAdapter(getActivity(), listALlProducts);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2, RecyclerView.VERTICAL, false);
        thisFragment.fHomeRecycleViewSuggest.setLayoutManager(gridLayoutManager);
        thisFragment.fHomeRecycleViewSuggest.setHasFixedSize(true);
        thisFragment.fHomeRecycleViewSuggest.setAdapter(suggestAdapter);
        thisFragment.fHomeRecycleViewSuggest.setNestedScrollingEnabled(false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getAllProduct();
    }

    private void getAllProduct() {
        APIService.appService.callAllProducts().enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                NotificationDiaLog.dismissProgressBar();
                listALlProducts = response.body();
                if (listALlProducts == null){
                    NotificationDiaLog.showDiaLogValidDate("Tải dữ liệu thất bại", getContext());
                }
                getListSale();
                setSaleProduct();
                setSuggestProduct();
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                NotificationDiaLog.dismissProgressBar();
                NotificationDiaLog.showDiaLogValidDate("Tải dữ liệu thất bại", getContext());
                Log.e("error", t.toString());
            }
        });
    }

    private void getListSale() {
        if (listALlProducts == null) return;
        for (Product product : listALlProducts) {
            if (product.getSale() != 0) {
                listSaleProducts.add(product);
            }
        }
    }

    //banner slide
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            thisFragment.fHomeImgBanner.startAnimation(animation);
            thisFragment.fHomeImgBanner.setImageResource(listBanner[loadBanner]);
            if (loadBanner >= listBanner.length - 1)
                loadBanner = 0;
            else
                loadBanner++;

            handler.postDelayed(this, 5000);
        }
    };

    //listener category
    private void setListCategory() {
        thisFragment.fHomeBtnPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), PhoneActivity.class));
            }
        });

        thisFragment.fHomeBtnHeadPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), HeadPhoneActivity.class));
            }
        });

        thisFragment.fHomeBtnWatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), WatchActivity.class));
            }
        });


        thisFragment.fHomeBtnLaptop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), LaptopActivity.class));
            }
        });
    }

}