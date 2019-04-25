package com.bocai.service.ui.feedback;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bocai.service.R;
import com.bocai.service.api.ServiceAction;
import com.bocai.service.api.ServiceStore;
import com.njh.common.core.RouterHub;
import com.njh.common.flux.base.BaseFluxActivity;

/**
 * @author libingjun
 * @version 投诉与建议
 * @date 2019/4/25
 */
@Route(path = RouterHub.Service.FEEDBACK)
public class FeedbackActivity extends BaseFluxActivity<ServiceStore, ServiceAction> {
    @Override
    public void initData(Bundle savedInstanceState) {
        request();
    }

    private void request() {
    }

    @Override
    public void setListener() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.service_activity_feedback;
    }

    @Override
    protected boolean flux() {
        return true;
    }
}
