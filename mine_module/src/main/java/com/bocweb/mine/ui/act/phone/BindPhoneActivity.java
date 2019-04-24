package com.bocweb.mine.ui.act.phone;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bocweb.mine.R;
import com.bocweb.mine.R2;
import com.bocweb.mine.api.MineAction;
import com.bocweb.mine.api.MineStore;
import com.njh.common.core.ReqTag;
import com.njh.common.core.RouterHub;
import com.njh.common.flux.base.BaseFluxActivity;
import com.njh.common.flux.stores.Store;
import com.njh.common.utils.common.TimeCountUtil;

import butterknife.BindView;

/**
 * @author libingjun
 * @date 2019/4/24
 */
@Route(path = RouterHub.Mine.BIND_PHONE)
public class BindPhoneActivity extends BaseFluxActivity<MineStore, MineAction> {

    @BindView(R2.id.et_phone)
    EditText etPhone;
    @BindView(R2.id.et_code)
    EditText etCode;
    @BindView(R2.id.tv_code)
    TextView tvCode;

    private TimeCountUtil countUtil;

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    public void onSubmit(View view) {
        String phone = etPhone.getText().toString().trim();
        String code = etCode.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            toast("请输入手机号");
            return;
        }
        if (TextUtils.isEmpty(code)) {
            toast("请输入验证码");
            return;
        }
        // 请求
        showLoading();
        actionsCreator().updateNewPhone(this, phone, code, "4", "", "");
    }

    public void onCode(View view) {
        String phone = etPhone.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            toast("请输入手机号");
            return;
        }
        showLoading();
        actionsCreator().registerCode(this, phone);

        countUtil = new TimeCountUtil(60100, 1000);
        countUtil.setOnTimeCountListenerUtil(new TimeCountUtil.OnTimeCountListenerUtil() {
            @Override
            public void onFinishListener() {
                tvCode.setText("获取验证码");
                tvCode.setEnabled(true);
            }

            @Override
            public void onTick(long second) {
                tvCode.setText(second + "s");
            }
        });
        countUtil.start();
    }

    @Override
    protected void updateView(Store.StoreChangeEvent event) {
        super.updateView(event);
        switch (event.url) {
            case ReqTag.Mine.MINE_UPLOAD_NEW_PHONE:
                // 手机号更新完毕
                break;
            case ReqTag.Mine.MINE_LOGIN_REGISTER_CODE:
                // 短信验证码
                break;
        }
    }

    @Override
    public void setListener() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.mine_activity_bind_phone;
    }

    @Override
    protected boolean flux() {
        return true;
    }
}
