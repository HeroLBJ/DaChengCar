package com.njh.base.ui.fmt;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.launcher.ARouter;
import com.gyf.barlibrary.ImmersionBar;
import com.njh.base.ui.view.BaseView;
import com.trello.rxlifecycle3.components.support.RxFragment;

import androidx.annotation.Nullable;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author niejiahuan
 */
public abstract class BaseFmt extends RxFragment implements BaseView {

    protected ImmersionBar immersionBar;

    protected Context mContext;
    protected View mContentView;
    protected OperateFmtCompatDelegate compatDelegate;
    private LoadingFragment loadingFragment;
    protected Unbinder unBinder;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @androidx.annotation.Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @androidx.annotation.Nullable ViewGroup container,
                             @androidx.annotation.Nullable Bundle savedInstanceState) {
        if (null == mContentView) {
            mContentView = inflater.inflate(getLayoutId(), container, false);
            unBinder = ButterKnife.bind(this, mContentView);
        }
        super.onCreateView(inflater, container, savedInstanceState);
        return mContentView;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //inject
        ARouter.getInstance().inject(this);
        //点击关闭软键盘
//        HideIMEUtil.wrap(this);
        compatDelegate = new OperateFmtCompatDelegate(this);
        loadingFragment = new LoadingFragment();
        initStatusBar();
        initData(savedInstanceState);
        setListener();
    }

    /**
     * 设置是否开启
     *
     * @return
     */
    protected boolean isEnableStatusBar() {
        return true;
    }

    protected void initStatusBar() {
        if (isEnableStatusBar()) {
            if (null == immersionBar) {
                immersionBar = ImmersionBar.with(this);
            }
            immersionBar.statusBarDarkFont(true, 0.2f)
                    .navigationBarColorInt(Color.WHITE)
                    .navigationBarDarkIcon(true, 0.2f)
                    .init();
        }
    }

    @Override
    public void showToast(String msg) {
        compatDelegate
                .getToastor()
                .showToast(msg);
    }

    @Override
    public void showLoading() {
        if (!loadingFragment.isShowing()) {
            loadingFragment.show(getChildFragmentManager(), "loging");
        }
    }

    @Override
    public void hideLoading() {
        if (loadingFragment.isShowing()) {
            loadingFragment.dismiss();
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (hidden) {
            if (null != immersionBar) {
                immersionBar.init();
            }
        }
        super.onHiddenChanged(hidden);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (null != unBinder) {
            unBinder.unbind();
        }
    }
}
