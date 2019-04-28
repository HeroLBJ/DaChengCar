package com.bocai.service.ui.keep;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import com.alibaba.android.arouter.facade.annotation.Route;

import com.bocai.service.R;
import com.bocai.service.R2;
import com.bocai.service.bean.ServicePackage;
import com.njh.common.core.RouterHub;
import com.njh.common.flux.base.BaseFluxActivity;
import com.njh.common.flux.base.BaseFluxFragment;
import com.njh.common.utils.CustomSlidingTablayout;
import com.njh.common.utils.arouter.ArouterUtils;

import java.util.ArrayList;
import java.util.List;

import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;

/**
 * @author libingjun
 * @date 2019/4/22
 */
@Route(path = RouterHub.Service.KEEP)
public class KeepActivity extends BaseFluxActivity
        implements KeepFreeFragment.OnKeepFreeListener, KeepMoneyFragment.OnKeepMoneyListener {

    @BindView(R2.id.sliding_tab_layout)
    CustomSlidingTablayout mSlidingTabLayout;
    @BindView(R2.id.view_pager)
    ViewPager mViewPager;
    @BindView(R2.id.tv_money)
    TextView tvMoney;
    @BindView(R2.id.tv_next)
    TextView tvNext;
    @BindView(R2.id.toolbar_back)
    ImageButton toolbarBack;
    @BindView(R2.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R2.id.toolbar_divider_line)
    View toolbarDividerLine;
    @BindView(R2.id.tv_desc)
    TextView tvDesc;

    private KeepFreeFragment freeFragment;
    private KeepMoneyFragment moneyFragment;
    private List<BaseFluxFragment> fmtList = new ArrayList<>();

    @Override
    public void initData(Bundle savedInstanceState) {
        initTitle();

        freeFragment = new KeepFreeFragment();
        moneyFragment = new KeepMoneyFragment();
        fmtList.add(freeFragment);
        fmtList.add(moneyFragment);

        mViewPager.setAdapter(new KeepViewPagerAdapter(getSupportFragmentManager(), fmtList));
        mSlidingTabLayout.setViewPager(mViewPager);
        toolbarDividerLine.setVisibility(View.VISIBLE);
    }

    private void initTitle() {
        toolbarBack.setOnClickListener(v -> finish());
        toolbarTitle.setText("预约保养专区");
    }

    @Override
    public void setListener() {
        freeFragment.setOnKeepFreeListener(this);
        moneyFragment.setOnKeepMoneyListener(this);
        tvNext.setOnClickListener(v -> {
            // 选择完下一步
            ArouterUtils.getInstance().navigation(this, RouterHub.Service.KEEP_DETAIL);
        });

        mViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                if (position == 0) {
                    tvDesc.setText("*除首保外，免保项目仅针对享受3年6万公里免保车型。");
                } else {
                    tvDesc.setText("*公示价格为参考价，不含工时费，实际价格请以服务站结算价格为准。");
                }
            }
        });
    }

    private List<ServicePackage> selectList = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.service_activity_keep;
    }

    @Override
    public void onKeepFreeClick(ServicePackage item, boolean select) {
        if (!select) {
            selectList.add(item);
        } else {
            selectList.remove(item);
        }
        setPrice();
    }

    @Override
    public void onKeepMoneyClick(ServicePackage item, boolean select) {
        if (!select) {
            selectList.add(item);
        } else {
            selectList.remove(item);
        }
        setPrice();
    }

    private void setPrice() {
        float total = 0;
        for (ServicePackage a : selectList) {
            total += Float.valueOf(a.getPrice());
        }
        tvMoney.setText(""+total);
    }
}
