package com.bocweb.home.ui.fmt;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bocweb.home.R;
import com.bocweb.home.R2;
import com.bocweb.home.ui.adapter.HomeViewPagerAdapter;
import com.bocweb.home.ui.util.CustomSlidingTablayout;
import com.njh.common.core.RouterHub;
import com.njh.common.flux.base.BaseFluxFragment;
import com.njh.common.utils.arouter.ArouterUtils;
import com.njh.common.widget.CustomPopWindow;

import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;

/**
 * @author niejiahuan
 */
@Route(path = RouterHub.Home.ROOT)
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
    public void setListener() {
        ivAdd.setOnClickListener(v -> onShowData());
    }

    private void onShowData() {
        View contentView = LayoutInflater.from(getContext()).inflate(R.layout.home_pop_main_click, null);
        TextView tvClick1 = contentView.findViewById(R.id.tv_click1);
        TextView tvClick2 = contentView.findViewById(R.id.tv_click2);
        tvClick1.setOnClickListener(v -> onClick1());
        tvClick2.setOnClickListener(v -> onClick2());
        CustomPopWindow popWindow = new CustomPopWindow.PopupWindowBuilder(getContext())
                .setOnDissmissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        ivAdd.setBackgroundResource(R.drawable.home_ic_add);
                    }
                })
                .setView(contentView)//显示的布局
                .create()//创建PopupWindow
                .showAsDropDown(ivAdd, 0, 10);//显示PopupWindow
        ivAdd.setBackgroundResource(R.drawable.home_ic_del);
    }

    private void onClick1() {
        ArouterUtils.getInstance().navigation(getContext(),RouterHub.Home.ADD_FRIEND);
    }

    private void onClick2() {
        ArouterUtils.getInstance().navigation(getContext(),RouterHub.Home.SEND_DYNAMIC);
    }

    @Override
    public int getLayoutId() {
        return R.layout.home_fragment;
    }
}
