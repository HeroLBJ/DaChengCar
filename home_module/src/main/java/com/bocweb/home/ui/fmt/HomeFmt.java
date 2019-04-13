package com.bocweb.home.ui.fmt;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bocweb.home.R;
import com.bocweb.home.R2;
import com.bocweb.home.ui.adapter.HomeViewPagerAdapter;
import com.bocweb.home.ui.util.CustomSlidingTablayout;
import com.njh.common.core.RouterHub;
import com.njh.common.flux.base.BaseFluxFragment;
import com.tencent.smtt.sdk.QbSdk;

import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;

/**
 * @author niejiahuan
 */
@Route(path = RouterHub.HOME_FMT)
public class HomeFmt extends BaseFluxFragment {

    @BindView(R2.id.sliding_tab_layout)
    CustomSlidingTablayout mSlidingTabLayout;
    @BindView(R2.id.view_pager)
    ViewPager mViewPager;
    @BindView(R2.id.iv_add)
    ImageView ivAdd;

    @Override
    public void initData(Bundle savedInstanceState) {
        mViewPager.setAdapter(new HomeViewPagerAdapter(getChildFragmentManager()));
        mSlidingTabLayout.setViewPager(mViewPager);
    }

    @Override
    public int getLayoutId() {
        return R.layout.home_fragment;
    }
}
