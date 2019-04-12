package com.bocweb.mine.ui.act.register;

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

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author niejiahuan
 * 注册界面
 */
@Route(path = RouterHub.Mine.REGISTER_ACT)
public class RegisterAct extends BaseFluxActivity {


    @BindView(R2.id.img_clear)
    ImageView imgClear;
    @BindView(R2.id.edt_phone)
    EditText edtPhone;
    @BindView(R2.id.edt_yzm)
    EditText edtYzm;
    @BindView(R2.id.edt_pwd)
    EditText edtPwd;
    @BindView(R2.id.edt_com_pwd)
    EditText edtComPwd;
    @BindView(R2.id.btn_login)
    Button btnLogin;

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
    }

    @Override
    public int getLayoutId() {
        return R.layout.mine_act_register;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
