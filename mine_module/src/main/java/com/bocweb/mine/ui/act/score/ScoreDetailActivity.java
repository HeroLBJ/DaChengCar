package com.bocweb.mine.ui.act.score;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bocweb.mine.R;
import com.bocweb.mine.R2;
import com.bocweb.mine.api.MineAction;
import com.bocweb.mine.api.MineStore;
import com.njh.common.core.RouterHub;
import com.njh.common.flux.base.BaseFluxActivity;
import com.njh.common.utils.CustomSlidingTablayout;

import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;

/**
 * @author libingjun
 * @version 积分详情
 * @date 2019/4/25
 */
@Route(path = RouterHub.Mine.SCORE_DETAIL)
public class ScoreDetailActivity extends BaseFluxActivity {

    @BindView(R2.id.sliding_tab_layout)
    CustomSlidingTablayout mSlidingTabLayout;
    @BindView(R2.id.viewPager)
    ViewPager mViewPager;
    @BindView(R2.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R2.id.toolbar_back)
    ImageButton toolbarBack;

    @Override
    public void initData(Bundle savedInstanceState) {
        initTitle();
        mViewPager.setAdapter(new ScoreDetailViewPagerAdapter(getSupportFragmentManager()));
        mSlidingTabLayout.setViewPager(mViewPager);
    }

    private void initTitle() {
        toolbarBack.setOnClickListener(v -> finish());
        toolbarTitle.setText("我的积分");
    }

    @Override
    public void setListener() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.mine_activity_score_detail;
    }
}
