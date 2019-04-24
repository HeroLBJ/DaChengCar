package com.bocweb.mine.ui.act.forgetpwd;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.bocweb.mine.R;
import com.bocweb.mine.R2;
import com.bocweb.mine.api.MineAction;
import com.bocweb.mine.api.MineStore;
import com.njh.common.core.ReqTag;
import com.njh.common.core.RouterHub;
import com.njh.common.flux.base.BaseFluxActivity;
import com.njh.common.flux.stores.Store;

import butterknife.BindView;

/**
 * @author libingjun
 * @date 2019/4/24
 */
@Route(path = RouterHub.Mine.SET_NEW_PWD)
public class SetPwdActivity extends BaseFluxActivity<MineStore, MineAction> {
    @BindView(R2.id.et_password)
    EditText etPassword;

    @Autowired
    String phone;

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    public void onSubmit(View view) {
        String password = etPassword.getText().toString().trim();

        // 检测密码是否合法

        showLoading();
        actionsCreator().setNewPwd(this, phone, password);
    }

    @Override
    protected void updateView(Store.StoreChangeEvent event) {
        super.updateView(event);
        switch (event.url) {
            case ReqTag.Mine.MINE_SET_NEW_PWD:
                // 设置新密码
                break;
        }
    }

    @Override
    public void setListener() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.mine_activity_set_new_pwd;
    }

    @Override
    protected boolean flux() {
        return true;
    }
}
