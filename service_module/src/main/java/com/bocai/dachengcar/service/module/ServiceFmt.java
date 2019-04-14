package com.bocai.dachengcar.service.module;

import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.njh.common.core.RouterHub;
import com.njh.common.flux.base.BaseFluxFragment;
import com.wuhenzhizao.titlebar.widget.CommonTitleBar;

import androidx.cardview.widget.CardView;
import butterknife.BindView;

/**
 * @author libingjun
 * @date 2019/4/8
 */
@Route(path = RouterHub.Service.ROOT)
public class ServiceFmt extends BaseFluxFragment {

    @BindView(R2.id.titleBar)
    CommonTitleBar titleBar;
    @BindView(R2.id.rl_maintain)
    RelativeLayout rlMaintain;
    @BindView(R2.id.rl_repair)
    RelativeLayout rlRepair;
    @BindView(R2.id.fab_call1)
    FloatingActionButton fabCall1;
    @BindView(R2.id.fab_call2)
    FloatingActionButton fabCall2;
    @BindView(R2.id.cv_call_rescue)
    CardView cvCallRescue;
    @BindView(R2.id.cv_call_110)
    CardView cvCall110;
    @BindView(R2.id.tv_map)
    TextView tvMap;
    @BindView(R2.id.tv_select)
    TextView tvSelect;
    @BindView(R2.id.tv_feedback)
    TextView tvFeedback;

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.service_fragment;
    }
}
