package com.bocweb.mine.ui.act.car;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bocweb.mine.R;
import com.njh.common.core.RouterHub;
import com.njh.common.flux.base.BaseFluxActivity;

/**
 * @author libingjun
 * @date 2019/4/11
 */
@Route(path = RouterHub.Mine.MY_CAR)
public class MyCarActivity extends BaseFluxActivity {
    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public void setListener() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.mine_activity_my_car;
    }

    @Override
    protected boolean flux() {
        return true;
    }
}
