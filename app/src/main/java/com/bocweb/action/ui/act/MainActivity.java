package com.bocweb.action.ui.act;

import android.os.Bundle;
import android.util.SparseArray;
import android.view.KeyEvent;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bocai.dachengcar.service.module.ServiceFmt;
import com.bocweb.action.R;
import com.bocweb.community.ui.fmt.CommunityFmt;
import com.bocweb.home.ui.fmt.HomeFmt;
import com.bocweb.mall.ui.fmt.MallFmt;
import com.bocweb.mine.ui.fmt.MineFmt;
import com.njh.base.utils.ActivityUtil;
import com.njh.common.core.RouterHub;
import com.njh.common.flux.base.BaseFluxActivity;
import com.njh.common.flux.base.BaseFluxFragment;
import com.njh.common.flux.stores.Store;
import com.wuhenzhizao.titlebar.widget.CommonTitleBar;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import butterknife.BindView;
import me.majiajie.pagerbottomtabstrip.NavigationController;
import me.majiajie.pagerbottomtabstrip.PageNavigationView;
import me.majiajie.pagerbottomtabstrip.item.BaseTabItem;
import me.majiajie.pagerbottomtabstrip.item.NormalItemView;
import me.majiajie.pagerbottomtabstrip.listener.OnTabItemSelectedListener;

/**
 * @author niejiahuan
 */
@Route(path = RouterHub.APP_MAINACTIVITY)
public class MainActivity extends BaseFluxActivity {

    @BindView(R.id.tab)
    PageNavigationView tab;

    private long mPressedTime;
    private long mIntervalTime = 2000;
    private FragmentManager mFragmentManager = getSupportFragmentManager();
    SparseArray<BaseFluxFragment> mTestFragments;
    NavigationController mNavigationController;

    @Override
    public void initData(Bundle savedInstanceState) {
        mNavigationController = tab.custom()
                .addItem(newItem(R.drawable.app_tab_2_default, R.drawable.app_tab_1_select, getString(R.string.home_tab_txt)))
                .addItem(newItem(R.drawable.app_tab_2_default, R.drawable.app_tab_1_select, getString(R.string.service_tab_ext)))
                .addItem(newItem(R.drawable.app_tab_3_default, R.drawable.app_tab_1_select, getString(R.string.mall_tab_txt)))
                .addItem(newItem(R.drawable.app_tab_4_default, R.drawable.app_tab_1_select, getString(R.string.community_tab_txt)))
                .addItem(newItem(R.drawable.app_tab_5_default, R.drawable.app_tab_1_select, getString(R.string.mine_tab_txt)))
                .build();
        this.mTestFragments = new SparseArray<>();
        HomeFmt homeFmt = (HomeFmt) ARouter.getInstance()
                .build(RouterHub.Home.ROOT)
                .navigation();
        this.mTestFragments.put(0, homeFmt);

        ServiceFmt serviceFmt = (ServiceFmt) ARouter.getInstance()
                .build(RouterHub.Service.ROOT)
                .navigation();
        mTestFragments.put(1, serviceFmt);

        CommunityFmt communityFmt = (CommunityFmt) ARouter.getInstance()
                .build(RouterHub.COMMUNITY_FMT)
                .navigation();
        this.mTestFragments.put(2, communityFmt);

        MallFmt mallFmt = (MallFmt) ARouter.getInstance()
                .build(RouterHub.MALL_fmt)
                .navigation();
        this.mTestFragments.put(3, mallFmt);

        MineFmt mineFmt = (MineFmt) ARouter.getInstance()
                .build(RouterHub.Mine.MINE_FMT)
                .navigation();
        this.mTestFragments.put(4, mineFmt);
        initFragment();
    }

    //创建一个Item
    private BaseTabItem newItem(int drawable, int checkedDrawable, String text) {
        NormalItemView normalItemView = new NormalItemView(this);
        normalItemView.initialize(drawable, checkedDrawable, text);
        normalItemView.setTextDefaultColor(getResources().getColor(R.color.TextDefaultColor));
        normalItemView.setTextCheckedColor(getResources().getColor(R.color.TextCheckedColor));
        return normalItemView;
    }

    @Override
    public void setListener() {
        mNavigationController.addTabItemSelectedListener(new OnTabItemSelectedListener() {
            @Override
            public void onSelected(int index, int old) {
                changeFragment(mTestFragments.get(index));
            }

            @Override
            public void onRepeat(int index) {

            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //获取第一次按键时间
            long mNowTime = System.currentTimeMillis();
            //比较两次按键时间差
            if ((mNowTime - mPressedTime) > mIntervalTime) {
                showToast(getString(R.string.press_again_txt));
                mPressedTime = mNowTime;
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    private void initFragment() {
        /* 默认显示home  fragment*/
        changeFragment(this.mTestFragments.get(0));
    }

    /*添加fragment*/
    private void addFragment(Fragment fragment) {
        /*判断该fragment是否已经被添加过  如果没有被添加  则添加*/
        if (!fragment.isAdded()) {
            getSupportFragmentManager().beginTransaction().add(R.id.fl_content, fragment).commitAllowingStateLoss();
        }
    }


    /*显示fragment*/
    private void showFragment(Fragment fragment) {
        for (int i = 0; i < mTestFragments.size(); i++) {
            BaseFluxFragment baseFluxFragment = mTestFragments.valueAt(i);
            if (baseFluxFragment != fragment) {
                /*先隐藏其他fragment*/
                mFragmentManager.beginTransaction().hide(baseFluxFragment).commitAllowingStateLoss();
            }
        }
        getSupportFragmentManager().beginTransaction().show(fragment).commitAllowingStateLoss();
    }

    private void changeFragment(Fragment fragment) {
        addFragment(fragment);
        showFragment(fragment);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ActivityUtil.getInstance().exitApp();
    }

    @Override
    protected void updateView(Store.StoreChangeEvent event) {
        super.updateView(event);
    }
}
