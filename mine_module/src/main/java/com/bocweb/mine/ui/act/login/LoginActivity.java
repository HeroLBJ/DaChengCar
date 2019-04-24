package com.bocweb.mine.ui.act.login;

import android.os.Bundle;
import android.text.InputType;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

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
import com.njh.common.utils.common.TimeCountUtil;
import com.njh.network.utils.TokenManager;
import com.orhanobut.hawk.Hawk;

import androidx.core.content.ContextCompat;
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
    @BindView(R2.id.tv_login_pwd)
    TextView tvLoginPwd;
    @BindView(R2.id.tv_login_phone)
    TextView tvLoginPhone;
    @BindView(R2.id.tv_code)
    TextView tvCode;
    @BindView(R2.id.iv_login_pwd_line)
    ImageView ivLoginPwdLine;
    @BindView(R2.id.iv_login_phone_line)
    ImageView ivLoginPhoneLine;
    @BindView(R2.id.rl_info)
    RelativeLayout rlInfo;
    @BindView(R2.id.ll_three)
    LinearLayout llThree;
    @BindView(R2.id.tv_info)
    TextView tvInfo;
    @BindView(R2.id.tv_forget_pwd)
    TextView tvForgetPwd;

    private TimeCountUtil countUtil;

    @Override
    public void initData(Bundle savedInstanceState) {
        initTextColor();

    }

    @Override
    public void setListener() {
        tvCode.setOnClickListener(v -> onGetCode());

        tvLoginPwd.setOnClickListener(v -> onSelectPwdLogin());
        tvLoginPhone.setOnClickListener(v -> onSelectPhoneLogin());
    }

    private void initTextColor() {
        SpannableString ss = new SpannableString("《用户注册协议》并注册成为大乘汽车用户");
        ForegroundColorSpan colorSpan1 = new ForegroundColorSpan(ContextCompat.getColor(this, R.color.res_login_select_color));
        ForegroundColorSpan colorSpan2 = new ForegroundColorSpan(ContextCompat.getColor(this, R.color.res_gray_999));
        ss.setSpan(colorSpan1, 0, 8, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        ss.setSpan(colorSpan2, 8, ss.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        tvInfo.setText(ss);
    }

    private void onGetCode() {
        String phone = etPhone.getText().toString().trim();
        if (phone.length() != 11) {
            toast("请输入正确的手机号");
            return;
        }
        showLoading();
        actionsCreator().registerCode(this, phone);

        tvCode.setEnabled(false);
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

    private void onSelectPwdLogin() {
        tvLoginPwd.setTextColor(ContextCompat.getColor(this, R.color.res_login_select_color));
        tvLoginPhone.setTextColor(ContextCompat.getColor(this, R.color.res_black));
        ivLoginPwdLine.setVisibility(View.VISIBLE);
        ivLoginPhoneLine.setVisibility(View.GONE);
        tvCode.setVisibility(View.VISIBLE);
        llThree.setVisibility(View.GONE);
        rlInfo.setVisibility(View.VISIBLE);
        etCode.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
        tvForgetPwd.setVisibility(View.INVISIBLE);
    }

    private void onSelectPhoneLogin() {
        tvLoginPhone.setTextColor(ContextCompat.getColor(this, R.color.res_login_select_color));
        tvLoginPwd.setTextColor(ContextCompat.getColor(this, R.color.res_black));
        ivLoginPhoneLine.setVisibility(View.VISIBLE);
        ivLoginPwdLine.setVisibility(View.GONE);
        tvCode.setVisibility(View.GONE);
        llThree.setVisibility(View.VISIBLE);
        rlInfo.setVisibility(View.GONE);
        etCode.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD|InputType.TYPE_CLASS_TEXT);
        tvForgetPwd.setVisibility(View.VISIBLE);
    }

    public void onLogin(View view) {
        // 登录
        String phone = etPhone.getText().toString().trim();
        String code = etCode.getText().toString().trim();
        actionsCreator().register(this, phone, code);
    }

    public void loginQQ(View view) {
        // qq登录
    }

    public void loginWx(View view) {
        // 微信登录
    }

    public void onInfo(View vew) {
        // 打开用户注册协议
    }

    @Override
    protected void updateView(Store.StoreChangeEvent event) {
        super.updateView(event);
        hideLoading();
        switch (event.url) {
            case ReqTag.Mine.MINE_LOGIN_REGISTER:
                LoginInfo bean1 = (LoginInfo) event.data;
                Hawk.put(TokenManager.SP_TOKEN, bean1.getToken());

                break;
            case ReqTag.Mine.MINE_LOGIN_REGISTER_CODE:

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
