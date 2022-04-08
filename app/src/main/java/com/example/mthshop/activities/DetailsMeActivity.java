package com.example.mthshop.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.mthshop.R;
import com.example.mthshop.databinding.ActivityDetailsMeBinding;

public class DetailsMeActivity extends AppCompatActivity {
    private ActivityDetailsMeBinding thisActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //status bar
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.back_ground));
        // binding
        thisActivity = ActivityDetailsMeBinding.inflate(getLayoutInflater());
        setContentView(thisActivity.getRoot());
        //back
        thisActivity.aDetailsMeImgBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //save
        thisActivity.aDetailsMeImgBtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        Log.e("date", LoginActivity.userCurrent.getDate());
    }
}