package com.bocai.dachengcar.service.module;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.njh.common.core.RouterHub;
import com.njh.common.flux.base.BaseFluxFragment;

/**
 * @author libingjun
 * @date 2019/4/8
 */
@Route(path = RouterHub.SERVICE_FMT)
public class ServiceFmt extends BaseFluxFragment {
    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.service_fragment;
    }
}
