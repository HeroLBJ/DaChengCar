package com.bocai.service.ui.keep.detail;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bocai.service.R;
import com.bocai.service.R2;
import com.bocai.service.api.ServiceAction;
import com.bocai.service.api.ServiceStore;
import com.njh.common.core.RouterHub;
import com.njh.common.flux.base.BaseFluxActivity;

import butterknife.BindView;

/**
 * @author libingjun
 * @date 2019/4/22
 */
@Route(path = RouterHub.Service.KEEP_DETAIL)
public class KeepDetailActivity extends BaseFluxActivity<ServiceStore, ServiceAction> {

    @BindView(R2.id.rl_city)
    RelativeLayout rlCity;
    @BindView(R2.id.rl_shop)
    RelativeLayout rlShop;
    @BindView(R2.id.rl_date)
    RelativeLayout rlDate;
    @BindView(R2.id.rl_time)
    RelativeLayout rlTime;
    @BindView(R2.id.tv_city)
    TextView tvCity;
    @BindView(R2.id.tv_shop)
    TextView tvShop;
    @BindView(R2.id.tv_date)
    TextView tvDate;
    @BindView(R2.id.tv_time)
    TextView tvTime;
    @BindView(R2.id.et_username)
    EditText etUsername;
    @BindView(R2.id.et_phone)
    EditText etPhone;
    @BindView(R2.id.tv_submit)
    TextView tvSubmit;

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public void setListener() {
        tvSubmit.setOnClickListener(v -> {
            // 提交维修信息
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.service_activity_keep_detail;
    }

    @Override
    protected boolean flux() {
        return true;
    }
}
