package com.bocai.service.ui.submit;

import android.os.Bundle;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bocai.service.R;
import com.bocai.service.R2;
import com.njh.common.core.RouterHub;
import com.njh.common.flux.base.BaseFluxActivity;
import com.njh.common.utils.arouter.ArouterUtils;

import butterknife.BindView;

/**
 * @author libingjun
 * @date 2019/4/23
 */
@Route(path = RouterHub.Service.SUBMIT_SUCCESS)
public class SubmitSuccessActivity extends BaseFluxActivity {

    @BindView(R2.id.tv_look)
    TextView tvLook;
    @BindView(R2.id.tv_main)
    TextView tvMain;

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public void setListener() {
        tvLook.setOnClickListener(v -> {
            // 查看预约订单
        });
        tvMain.setOnClickListener(v -> {
            // 去首页
            ArouterUtils.getInstance().navigation(this, RouterHub.APP_MAINACTIVITY);
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.service_activity_submit;
    }

    @Override
    public void onBackPressed() {

    }
}
