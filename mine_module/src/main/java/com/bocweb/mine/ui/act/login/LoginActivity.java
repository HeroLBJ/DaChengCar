package com.bocweb.mine.ui.act.login;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bocweb.mine.R;
import com.bocweb.mine.R2;
import com.bocweb.mine.api.MineAction;
import com.bocweb.mine.api.MineStore;
import com.bocweb.mine.bean.LoginInfo;
import com.njh.common.core.ReqTag;
import com.njh.common.core.RouterHub;
import com.njh.common.flux.base.BaseFluxActivity;
import com.njh.common.flux.stores.Store;
import com.njh.network.utils.TokenManager;
import com.orhanobut.hawk.Hawk;

import butterknife.BindView;

/**
 * @author libingjun
 * @date 2019/4/17
 */
@Route(path = RouterHub.Mine.LOGIN)
public class LoginActivity extends BaseFluxActivity<MineStore, MineAction> {

    @BindView(R2.id.et_phone)
    EditText etPhone;
    @BindView(R2.id.et_code)
    EditText etCode;
    @BindView(R2.id.btn_login)
    Button btnLogin;
    @BindView(R2.id.btn_code)
    Button btnCode;

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public void setListener() {
        btnLogin.setOnClickListener(v -> {
            String phone = etPhone.getText().toString().trim();
            String code = etCode.getText().toString().trim();
            actionsCreator().register(this, phone, code);
        });

        btnCode.setOnClickListener(v -> {
            String phone = etPhone.getText().toString().trim();
            actionsCreator().registerCode(this, phone);
        });
    }

    @Override
    protected void updateView(Store.StoreChangeEvent event) {
        super.updateView(event);
        switch (event.url) {
            case ReqTag.Mine.MINE_LOGIN_REGISTER:
                LoginInfo bean1 = (LoginInfo) event.data;
                Hawk.put(TokenManager.SP_TOKEN, bean1.getToken());
                break;
            case ReqTag.Mine.MINE_LOGIN_REGISTER_CODE:
                LoginInfo bean2 = (LoginInfo) event.data;

                break;
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.mine_activity_login;
    }

    @Override
    protected boolean flux() {
        return true;
    }
}
