package com.bocweb.home.ui.adapter;

import android.view.ViewGroup;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bocweb.home.ui.fmt.main.activity.MainActivityFragment;
import com.bocweb.home.ui.fmt.main.dynamic.MainDynamicFragment;
import com.bocweb.home.ui.fmt.main.info.MainInfoFragment;
import com.bocweb.home.ui.fmt.main.selected.MainSelectedFragment;
import com.njh.common.core.RouterHub;
import com.njh.common.flux.base.BaseFluxFragment;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

/**
 * @author libingjun
 * @date 2019/4/8
 */
public class HomeViewPagerAdapter extends FragmentStatePagerAdapter {

    private List<BaseFluxFragment> fmtList = new ArrayList<>();
    private final String[] mTitles = {"精选", "动态", "活动", "资讯"};

    public HomeViewPagerAdapter(FragmentManager fm) {
        super(fm);
        fmtList.add((MainSelectedFragment) ARouter.getInstance()
                .build(RouterHub.Home.HOME_SELECTED)
                .navigation());
        fmtList.add((MainDynamicFragment) ARouter.getInstance()
                .build(RouterHub.Home.HOME_DYNAMIC)
                .navigation());
        fmtList.add((MainActivityFragment) ARouter.getInstance()
                .build(RouterHub.Home.HOME_ACTIVITY)
                .navigation());
        fmtList.add((MainInfoFragment) ARouter.getInstance()
                .build(RouterHub.Home.HOME_INFO)
                .navigation());
    }

    @Override
    public Fragment getItem(int position) {
        return fmtList.get(position);
    }

    @Override
    public int getCount() {
        return fmtList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

    }
}
