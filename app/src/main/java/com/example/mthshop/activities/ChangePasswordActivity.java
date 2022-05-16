package com.example.mthshop.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.mthshop.R;
import com.example.mthshop.api.APIService;
import com.example.mthshop.dialog.NotificationDiaLog;
import com.example.mthshop.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePasswordActivity extends AppCompatActivity {
    EditText matkhauCu, matkhauMoi, xacnhanMatKhauMoi;
    AppCompatButton btnChangePass;
    ImageButton showPass, showNewP, showConformP;
    private boolean checkStatusPassword = true; // xu ly an hien pass word
    private boolean checkStatusNewPassword = true; // xu ly an hien pass word
    private boolean checkStatusConfirmPassword = true; // xu ly an hien pass word
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        matkhauCu = findViewById(R.id.aChangePass_edPass);
        matkhauMoi = findViewById(R.id.aChangePass_edNewPass);
        xacnhanMatKhauMoi = findViewById(R.id.aChangePass_edNewPassConfirm);
        btnChangePass = findViewById(R.id.aChangePass_btnOk);
        //
        showPass = findViewById(R.id.aChangePass_imgShowP);
        showNewP = findViewById(R.id.aChangePass_imgNewShowP);
        showConformP = findViewById(R.id.aChangePass_imgNewShowPConfirm);

        //hien hoac an pass word
        showPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkStatusPassword) {
                    showPass.setImageResource(R.drawable.ic_eye_show);
                    matkhauCu.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    matkhauCu.setSelection(matkhauCu.getText().length());
                    checkStatusPassword = false;
                }else {
                    showPass.setImageResource(R.drawable.ic_eye_hidden);
                    matkhauCu.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    matkhauCu.setSelection(matkhauCu.getText().length());
                    checkStatusPassword = true;
                }

            }
        });

        //hien hoac an pass word
        showNewP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkStatusConfirmPassword) {
                    showNewP.setImageResource(R.drawable.ic_eye_show);
                    matkhauMoi.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    matkhauMoi.setSelection(matkhauMoi.getText().length());
                    checkStatusConfirmPassword = false;
                }else {
                    showNewP.setImageResource(R.drawable.ic_eye_hidden);
                    matkhauMoi.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    matkhauMoi.setSelection(matkhauMoi.getText().length());
                    checkStatusConfirmPassword = true;
                }

            }
        });

        //hien hoac an pass word
        showConformP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkStatusNewPassword) {
                    showConformP.setImageResource(R.drawable.ic_eye_show);
                    xacnhanMatKhauMoi.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    xacnhanMatKhauMoi.setSelection(xacnhanMatKhauMoi.getText().length());
                    checkStatusNewPassword = false;
                }else {
                    showConformP.setImageResource(R.drawable.ic_eye_hidden);
                    xacnhanMatKhauMoi.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    xacnhanMatKhauMoi.setSelection(xacnhanMatKhauMoi.getText().length());
                    checkStatusNewPassword = true;
                }

            }
        });


        btnChangePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String oldpass = matkhauCu.getText().toString().trim();
                String newpass = matkhauMoi.getText().toString().trim();
                String passconfirm = xacnhanMatKhauMoi.getText().toString().trim();
                if (oldpass.isEmpty() || newpass.isEmpty() || passconfirm.isEmpty()) {
                    if (oldpass.isEmpty()) {
                        matkhauCu.setBackground(ContextCompat.getDrawable(ChangePasswordActivity.this, R.drawable.form_style_login_false));
                        NotificationDiaLog.showDiaLogValidDate("Ui!! Bạn chưa nhập mật khẩu cũ.", ChangePasswordActivity.this);
                    }else if(newpass.isEmpty()){
                        matkhauMoi.setBackground(ContextCompat.getDrawable(ChangePasswordActivity.this, R.drawable.form_style_login_false));
                        NotificationDiaLog.showDiaLogValidDate("Ui!! Bạn chưa nhập mật khẩu mới.", ChangePasswordActivity.this);
                    }else {
                        xacnhanMatKhauMoi.setBackground(ContextCompat.getDrawable(ChangePasswordActivity.this, R.drawable.form_style_login_false));
                        NotificationDiaLog.showDiaLogValidDate("Ui!! Bạn chưa nhập xác nhận mật khẩu mới.", ChangePasswordActivity.this);
                    }
                }else {
                    if (oldpass.equals(LoginActivity.userCurrent.getPassword())) {
                        if (newpass.equals(passconfirm)) {
                            LoginActivity.userCurrent.setPassword(passconfirm);
                            APIService.appService.editInfoUser(LoginActivity.userCurrent).enqueue(new Callback<User>() {
                                @Override
                                public void onResponse(Call<User> call, Response<User> response) {
                                    finish();
                                }

                                @Override
                                public void onFailure(Call<User> call, Throwable t) {
                                    Log.e("change pass", t.toString());
                                }
                            });
                        }else {
                            xacnhanMatKhauMoi.setBackground(ContextCompat.getDrawable(ChangePasswordActivity.this, R.drawable.form_style_login_false));
                            NotificationDiaLog.showDiaLogValidDate("Ui!! Mật khẩu và xác nhận mật khẩu không giống nhau.", ChangePasswordActivity.this);
                        }
                    }else {
                        matkhauCu.setBackground(ContextCompat.getDrawable(ChangePasswordActivity.this, R.drawable.form_style_login_false));
                        NotificationDiaLog.showDiaLogValidDate("Ui!! Nhập sai mật khẩu hiện tại.", ChangePasswordActivity.this);
                    }
                }
            }
        });
    }
}