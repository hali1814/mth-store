package com.example.mthshop.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        matkhauCu = findViewById(R.id.aChangePass_edPass);
        matkhauMoi = findViewById(R.id.aChangePass_edNewPass);
        xacnhanMatKhauMoi = findViewById(R.id.aChangePass_edNewPassConfirm);
        btnChangePass = findViewById(R.id.aChangePass_btnOk);

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
                }
            }
        });
    }
}