package com.bocweb.mine.ui.act.follow;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bocweb.mine.R;
import com.bocweb.mine.R2;
import com.bocweb.mine.ui.act.score.ScoreDetailViewPagerAdapter;
import com.njh.common.core.RouterHub;
import com.njh.common.flux.base.BaseFluxActivity;
import com.njh.common.utils.CustomSlidingTablayout;

import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;

/**
 * @author libingjun
 * @version 我的关注
 * @date 2019/4/11
 */
@Route(path = RouterHub.Mine.FOLLOW)
public class FollowActivity extends BaseFluxActivity {

    @BindView(R2.id.toolbar_back)
    ImageButton toolbarBack;
    @BindView(R2.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R2.id.sliding_tab_layout)
    CustomSlidingTablayout mSlidingTabLayout;
    @BindView(R2.id.viewPager)
    ViewPager mViewPager;

    @Override
    public void initData(Bundle savedInstanceState) {
        initTitle();
        mViewPager.setAdapter(new ScoreDetailViewPagerAdapter(getSupportFragmentManager()));
        mSlidingTabLayout.setViewPager(mViewPager);
    }

    private void initTitle() {
        toolbarBack.setOnClickListener(v -> finish());
        toolbarTitle.setText("我的");
    }

    @Override
    public void setListener() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.mine_activity_follow;
    }
}
