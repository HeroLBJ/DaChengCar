package com.bocai.service.ui.keep;

import android.view.ViewGroup;

import com.alibaba.android.arouter.launcher.ARouter;
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
 * @date 2019/4/22
 */
public class KeepViewPagerAdapter extends FragmentStatePagerAdapter {

    private List<BaseFluxFragment> fmtList = new ArrayList<>();
    private final String[] mTitles = {"免费项目", "自费项目"};

    public KeepViewPagerAdapter(FragmentManager fm, List<BaseFluxFragment> fmtList) {
        super(fm);
        this.fmtList = fmtList;
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
