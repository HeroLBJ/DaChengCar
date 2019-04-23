package com.njh.common.widget;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.njh.common.CommonApp;
import com.njh.common.R;

/**
 * @author libingjun
 * @date 2019/4/23
 */

public class CustomToast {
    private Toast mToast;
    private TextView mTvToast;

    private CustomToast() {
    }

    private static class CustomToastHolder {
        private static final CustomToast INSTANCE = new CustomToast();
    }

    public static CustomToast getInstance() {
        return CustomToastHolder.INSTANCE;
    }

    public void showToast(String content) {
        if (mToast == null) {
            mToast = new Toast(CommonApp.getContext());
            mToast.setGravity(Gravity.CENTER, 0, 0);//设置toast显示的位置，这是居中
            mToast.setDuration(Toast.LENGTH_SHORT);//设置toast显示的时长
            View root = LayoutInflater.from(CommonApp.getContext()).inflate(R.layout.common_custom_toast, null);//自定义样式，自定义布局文件
            mTvToast = root.findViewById(R.id.tvCustomToast);
            mToast.setView(root);//设置自定义的view
        }
        mTvToast.setText(content);//设置文本
        mToast.show();//展示toast
    }

    public void cancelToast() {
        if (mToast != null) {
            mToast.cancel();
            mToast = null;
        }
    }
}
