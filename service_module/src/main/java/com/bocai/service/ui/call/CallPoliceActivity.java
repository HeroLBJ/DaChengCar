package com.bocai.service.ui.call;

import android.os.Bundle;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bocai.service.R;
import com.bocai.service.R2;
import com.njh.common.core.RouterHub;
import com.njh.common.flux.base.BaseFluxActivity;
import com.njh.common.helper.CallHelper;

import butterknife.BindView;

/**
 * @author libingjun
 * @date 2019/4/23
 */
@Route(path = RouterHub.Service.CALL_POLICE)
public class CallPoliceActivity extends BaseFluxActivity{

    @BindView(R2.id.iv_call_dc)
    ImageView ivCallDc;
    @BindView(R2.id.iv_call_110)
    ImageView ivCall110;
    @BindView(R2.id.iv_call_119)
    ImageView ivCall119;
    @BindView(R2.id.iv_call_120)
    ImageView ivCall120;
    @BindView(R2.id.iv_call_122)
    ImageView ivCall122;

    private CallHelper helper;

    @Override
    public void initData(Bundle savedInstanceState) {
        helper = new CallHelper(this);
    }

    @Override
    public void setListener() {
        ivCallDc.setOnClickListener(v -> helper.show());
        ivCall110.setOnClickListener(v -> helper.show("110"));
        ivCall119.setOnClickListener(v -> helper.show("119"));
        ivCall120.setOnClickListener(v -> helper.show("120"));
        ivCall122.setOnClickListener(v -> helper.show("122"));
    }

    @Override
    public int getLayoutId() {
        return R.layout.service_activity_call_police;
    }
}
