package com.njh.base.ui.fmt;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.njh.base.R;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;


/**
 * Created by kaizen on 8/24/16.
 */
public class LoadingFragment extends DialogFragment {

    private Context mContext;
    private boolean isShow = false;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(mContext, R.style.base_FragmentDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.base_loading);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                isShow = false;
            }
        });
        Window window = dialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.gravity = Gravity.CENTER;
        window.setAttributes(lp);
        return dialog;
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        isShow = true;
        super.show(manager, tag);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Override
    public void dismiss() {
        if (this.isAdded())
            super.dismiss();
        isShow = false;
    }

    public void setShow(boolean show) {
        isShow = show;
    }

    public boolean isShowing() {
        return isShow;
    }

}
