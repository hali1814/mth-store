package com.example.mthshop.fragment;


import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.fragment.app.Fragment;

import com.example.mthshop.R;
import com.example.mthshop.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding thisFragment;
    private Handler handler;
    private int[] listBanner = {R.drawable.banner3, R.drawable.banner1, R.drawable.banner2, R.drawable.banner};
    private int loadBanner = 0;
    private Animation animation;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        thisFragment = FragmentHomeBinding.inflate(getLayoutInflater(), container, false);
        //banner slide
        handler = new Handler();
        handler.postDelayed(runnable, 5000);
        //animation banner
        animation = AnimationUtils.loadAnimation(getContext(), R.anim.benner_out);

        return thisFragment.getRoot();
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
}