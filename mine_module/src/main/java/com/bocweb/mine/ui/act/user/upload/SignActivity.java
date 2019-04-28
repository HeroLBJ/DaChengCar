package com.bocweb.mine.ui.act.user.upload;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
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
import com.njh.common.sp.user.UserHelper;
import com.njh.common.sp.user.UserInfo;

import butterknife.BindView;

/**
 * @author libingjun
 * @version 修改签名
 * @date 2019/4/28
 */
@Route(path = RouterHub.Mine.SIGN)
public class SignActivity extends BaseFluxActivity<MineStore, MineAction> {

    @BindView(R2.id.toolbar_back)
    ImageButton toolbarBack;
    @BindView(R2.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R2.id.toolbar_divider_line)
    View toolbarDividerLine;
    @BindView(R2.id.et_sign)
    EditText etSign;


    @Override
    public void initData(Bundle savedInstanceState) {
        initTitle();
        UserInfo userInfo = UserHelper.getUserInfo();
        sign = userInfo.getSightml();
        etSign.setText(sign);
    }

    private void initTitle() {
        toolbarBack.setOnClickListener(v -> finish());
        toolbarTitle.setText("修改签名");
        toolbarDividerLine.setVisibility(View.VISIBLE);
    }

    private String sign;

    public void onSubmit(View view) {
        sign = etSign.getText().toString().trim();
        showLoading();
        actionsCreator().uploadUserSign(this, sign);
    }

    @Override
    protected void updateView(Store.StoreChangeEvent event) {
        super.updateView(event);
        hideLoading();
        switch (event.url) {
            case ReqTag.Mine.MINE_UPLOAD_USER_SIGN:
                Intent intent = new Intent();
                intent.putExtra("sightml", sign);
                setResult(RESULT_OK, intent);
                break;
        }
    }

    @Override
    public void setListener() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.mine_activity_sign;
    }

    @Override
    protected boolean flux() {
        return true;
    }
}
