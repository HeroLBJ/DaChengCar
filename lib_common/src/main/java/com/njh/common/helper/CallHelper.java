package com.njh.common.helper;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.njh.common.R;

import androidx.appcompat.app.AlertDialog;

/**
 * @author libingjun
 * @date 2019/4/18
 */
public class CallHelper {

    private AlertDialog dialog;
    private final String defaultNum = "400-890-5678";
    private TextView tvContent;
    private String callPhone;

    public CallHelper(Context context) {
        View layout = LayoutInflater.from(context).inflate(R.layout.common_call_default, null);

        dialog = new AlertDialog.Builder(context)
                .setView(layout)
                .create();

        tvContent = layout.findViewById(R.id.tv2);

        layout.findViewById(R.id.tv_cancel).setOnClickListener(v -> dialog.dismiss());

        layout.findViewById(R.id.tv_call).setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            Uri data = Uri.parse("tel:" + callPhone);
            intent.setData(data);
            context.startActivity(intent);
        });
    }

    public void show() {
        show(defaultNum);
    }

    public void show(String phoneNum) {
        if (dialog != null) {
            callPhone = phoneNum;
            tvContent.setText("确定拨打" + phoneNum + "？");
            dialog.show();
        }
    }
}
