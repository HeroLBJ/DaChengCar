package com.bocai.service;

import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;


import com.bocai.service.ui.navigation.NavigationActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.njh.common.core.RouterHub;
import com.njh.common.flux.base.BaseFluxFragment;
import com.njh.common.helper.CallHelper;
import com.njh.common.utils.arouter.ArouterUtils;
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
    @BindView(R2.id.fab_call3)
    FloatingActionButton fabCall3;

    private CallHelper call;

    private final int maintain = 10;
    private final int repair = 11;
    private final int call2 = 12;
    private final int select = 13;
    private final int feedback = 14;

    @Override
    public void initData(Bundle savedInstanceState) {
        call = new CallHelper(getContext());
    }

    @Override
    public int getLayoutId() {
        return R.layout.service_fragment;
    }

    @Override
    public void setListener() {
        rlMaintain.setOnClickListener(v -> onCheck(maintain));
        rlRepair.setOnClickListener(v -> onCheck(repair));
        cvCallRescue.setOnClickListener(v -> onCheck(call2));
        tvSelect.setOnClickListener(v -> onCheck(select));
        tvFeedback.setOnClickListener(v -> onCheck(feedback));
        fabCall1.setOnClickListener(v -> call.show());
        fabCall3.setOnClickListener(v -> call.show());
        cvCall110.setOnClickListener(v -> {
            // 去一键报案页面
            ArouterUtils.getInstance().navigation(getContext(),RouterHub.Service.CALL_POLICE);
        });
        tvMap.setOnClickListener(v -> {
            // 去一键导航页面
            ArouterUtils.getInstance().navigation(getContext(), RouterHub.Service.NAVIGATION);
        });
    }

    private void onCheck(int flag) {
        // 1.判断是否是认证车主

        // 1.1 如果不是，则弹出弹框

        // 1.2 如果是则进行下一步

        switch (flag) {
            case maintain:
                // 去预约保养页面
                ArouterUtils.getInstance().navigation(getContext(), RouterHub.Service.KEEP);
                break;
            case repair:
                // 去预约维修页面
                ArouterUtils.getInstance().navigation(getContext(), RouterHub.Service.REPAIR);
                break;
            case call2:
                // 去一键救援页面
                ArouterUtils.getInstance().navigation(getContext(), RouterHub.Service.NAVIGATION);
                break;
            case select:
                // 去配件查询页面
                ArouterUtils.getInstance().navigation(getContext(), RouterHub.Service.SELECT);
                break;
            case feedback:
                // 去投诉意见页面
                break;
        }
    }
}
