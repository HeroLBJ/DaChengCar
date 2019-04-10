package com.bocweb.mine.ui.act.forgetpwd;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bocweb.mine.R;
import com.bocweb.mine.R2;
import com.njh.common.core.RouterHub;
import com.njh.common.flux.base.BaseFluxActivity;
import com.njh.common.utils.arouter.ArouterUtils;

import butterknife.BindView;

/**
 * @author niejiahuan
 * 忘记密码界面
 */
@Route(path = RouterHub.FORGET_PWD_SMS_ACT)
public class ForgetPwdSMSAct extends BaseFluxActivity {

    @BindView(R2.id.img_clear)
    ImageView imgClear;
    @BindView(R2.id.edt_phone)
    EditText edtPhone;
    @BindView(R2.id.edt_yzm)
    EditText edtYzm;
    @BindView(R2.id.btn_next)
    Button btnNext;

    @Override
    public void initData(Bundle savedInstanceState) {
        immersionBar.fitsSystemWindows(true);
        immersionBar.init();
    }

    @Override
    public void setListener() {
        imgClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArouterUtils.getInstance().navigation(getContext()
                        ,RouterHub.FORGET_PWD_ACT);
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.mine_act_forgetpwd_sms;
    }

}
