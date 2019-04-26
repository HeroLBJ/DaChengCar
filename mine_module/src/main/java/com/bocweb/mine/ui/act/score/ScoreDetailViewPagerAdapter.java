package com.bocweb.mine.ui.act.score;

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
 * @version 积分明细 ViewPager Adapter
 * @date 2019/4/26
 */
public class ScoreDetailViewPagerAdapter extends FragmentStatePagerAdapter {

    private List<BaseFluxFragment> fmtList = new ArrayList<>();
    private final String[] mTitles = {"收入明细", "支出明细"};

    public ScoreDetailViewPagerAdapter(FragmentManager fm) {
        super(fm);
        fmtList.add((ScoreGetFragment) ARouter.getInstance()
                .build(RouterHub.Mine.SCORE_GET)
                .navigation());
        fmtList.add((ScorePayFragment) ARouter.getInstance()
                .build(RouterHub.Mine.SCORE_PAY)
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
