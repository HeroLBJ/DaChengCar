package com.bocweb.mine.ui.act.follow;

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
 * @version 1.0
 * @date 2019/4/29
 */
public class FollowViewPagerAdapter extends FragmentStatePagerAdapter {

    private List<BaseFluxFragment> fmtList = new ArrayList<>();
    private final String[] mTitles = {"关注", "粉丝"};

    public FollowViewPagerAdapter(FragmentManager fm) {
        super(fm);
        fmtList.add((FollowFragment) ARouter.getInstance()
                .build(RouterHub.Mine.FOLLOW_FOLLOW)
                .navigation());
        fmtList.add((FansFragment) ARouter.getInstance()
                .build(RouterHub.Mine.FOLLOW_FANS)
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
