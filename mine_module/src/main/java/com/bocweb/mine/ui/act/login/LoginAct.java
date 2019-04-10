package com.bocweb.mine.ui.act.login;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bocweb.mine.R;
import com.bocweb.mine.R2;
import com.bocweb.mine.ui.actions.MineAction;
import com.bocweb.mine.ui.stores.MineStore;
import com.njh.common.core.Constants;
import com.njh.common.core.ReqTag;
import com.njh.common.core.RouterHub;
import com.njh.common.flux.base.BaseFluxActivity;
import com.njh.common.flux.stores.Store;
import com.njh.common.utils.arouter.ArouterUtils;
import com.njh.common.utils.http.AESUtils;

import butterknife.BindView;

/**
 * @author niejiahuan
 * 登录界面
 */
@Route(path = RouterHub.LOGIN_ACT)
public class LoginAct extends BaseFluxActivity<MineStore,MineAction> {

    @BindView(R2.id.edt_phone)
    EditText edtPhone;
    @BindView(R2.id.edt_pwd)
    EditText edtPwd;
    @BindView(R2.id.btn_login)
    Button btnLogin;
    @BindView(R2.id.tv_register)
    TextView tvRegister;
    @BindView(R2.id.tv_forget_pwd)
    TextView tvForgetPwd;
    @BindView(R2.id.img_clear)
    ImageView imgClear;

    @Override
    protected boolean flux() {
        return true;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        immersionBar.fitsSystemWindows(true);
        immersionBar.init();
    }

    @Override
    public void setListener() {
        imgClear.setOnClickListener(v -> finish());
        tvRegister.setOnClickListener(v ->
                ArouterUtils.getInstance().navigation(getContext()
                        ,RouterHub.REGISTER_ACT));
        tvForgetPwd.setOnClickListener(v ->
                ArouterUtils.getInstance().navigation(getContext()
                        ,RouterHub.FORGET_PWD_SMS_ACT));

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone= edtPhone.getText().toString().trim();
                String password=edtPwd.getText().toString().trim();

                if (TextUtils.isEmpty(phone)){
                    edtPhone.setFocusable(true);
                    showToast("请输入手机号");
                    return;
                }
                if (phone.length()!=11){
                    edtPhone.setFocusable(true);
                    showToast("请输入手机号11的手机号");
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    edtPwd.setFocusable(true);
                    showToast("请输入密码");
                    return;
                }
                login(phone,password);
            }
        });
    }

    /**
     * 用户登录
     * @param phone
     * @param password
     */
    public void login(String phone,String password){
        actionsCreator().signin(LoginAct.this,phone, AESUtils.encode(password));
    }
    @Override
    public int getLayoutId() {
        return R.layout.mine_act_login;
    }

    @Override
    protected void updateView(Store.StoreChangeEvent event) {
        super.updateView(event);
        if (event.returnCode== Constants.SUCC_CODE) {
            switch (event.url) {
                case ReqTag.REQ_TAG_POST_SIGNIN:
                    showToast("登录成功!");
                    break;
            }
        }
    }
}
