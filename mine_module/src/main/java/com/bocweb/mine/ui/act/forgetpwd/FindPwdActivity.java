package com.bocweb.mine.ui.act.forgetpwd;

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
@Route(path = RouterHub.Mine.FIND_PWD)
public class FindPwdActivity extends BaseFluxActivity<MineStore, MineAction> {
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
        actionsCreator().checkPhone(this, phone, code);
    }

    public void onCode(View view) {
        String phone = etPhone.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            toast("请输入手机号");
            return;
        }
        showLoading();
        actionsCreator().forgetPwdCode(this, phone);

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
            case ReqTag.Mine.MINE_FORGET_PWD_CODE:
                // 忘记密码发送验证码
                break;
            case ReqTag.Mine.MINE_CHECK_PHONE:
                // 验证手机号进入下一步

                break;
        }
    }

    @Override
    public void setListener() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.mine_activity_find_pwd;
    }

    @Override
    protected boolean flux() {
        return true;
    }
}
