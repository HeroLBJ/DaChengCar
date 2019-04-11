package com.bocweb.home.ui.fmt.main.activity;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bocweb.home.R;
import com.njh.common.core.RouterHub;
import com.njh.common.flux.base.BaseFluxFragment;

/**
 * @author libingjun
 * @date 2019/4/8
 */
@Route(path = RouterHub.HOME_ACTIVITY)
public class MainActivityFragment extends BaseFluxFragment {

    @Override
    public void initData(Bundle savedInstanceState) {
    }

    @Override
    public int getLayoutId() {
        return R.layout.home_fragment_activity;
    }
}