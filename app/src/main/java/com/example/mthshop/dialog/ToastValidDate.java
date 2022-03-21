package com.example.mthshop.dialog;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mthshop.R;

public class ToastValidDate {
    private static Toast toast;

    private static Toast getToast(Context context) {
        if (toast == null)
            toast = new Toast(context);

        return toast;
    }
    public static void showDiaLogValidDate(String content, Context context) {
        getToast(context).setDuration(Toast.LENGTH_SHORT);
        getToast(context).setGravity(Gravity.CENTER_VERTICAL, 0 ,0);
        View view = ((Activity)context).getLayoutInflater().inflate(R.layout.dialog_validate_form, null);
        ((TextView)view.findViewById(R.id.toastValid_tvContent)).setText(content);
        getToast(context).setView(view);

        getToast(context).show();
    }
}
