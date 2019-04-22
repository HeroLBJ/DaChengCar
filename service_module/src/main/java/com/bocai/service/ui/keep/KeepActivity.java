package com.bocai.service.ui.keep;

import android.os.Bundle;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bocai.service.R;
import com.bocai.service.R2;
import com.njh.common.core.RouterHub;
import com.njh.common.flux.base.BaseFluxActivity;
import com.njh.common.utils.CustomSlidingTablayout;
import com.njh.common.utils.arouter.ArouterUtils;

import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;

/**
 * @author libingjun
 * @date 2019/4/22
 */
@Route(path = RouterHub.Service.KEEP)
public class KeepActivity extends BaseFluxActivity {

    @BindView(R2.id.sliding_tab_layout)
    CustomSlidingTablayout mSlidingTabLayout;
    @BindView(R2.id.view_pager)
    ViewPager mViewPager;
    @BindView(R2.id.tv_money)
    TextView tvMoney;
    @BindView(R2.id.tv_next)
    TextView tvNext;

    @Override
    public void initData(Bundle savedInstanceState) {
        mViewPager.setAdapter(new KeepViewPagerAdapter(getSupportFragmentManager()));
        mSlidingTabLayout.setViewPager(mViewPager);
    }

    @Override
    public void setListener() {
        tvNext.setOnClickListener(v -> {
            // 选择完下一步
            ArouterUtils.getInstance().navigation(this, RouterHub.Service.KEEP_DETAIL);
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.service_activity_keep;
    }
}
