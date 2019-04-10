package com.njh.base.ui.act;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import com.alibaba.android.arouter.launcher.ARouter;
import com.gyf.barlibrary.ImmersionBar;
import com.njh.base.ui.fmt.LoadingFragment;
import com.njh.base.ui.view.BaseView;
import com.njh.base.utils.ActivityUtil;
import com.trello.rxlifecycle3.components.support.RxAppCompatActivity;

import org.loader.autohideime.HideIMEUtil;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author niejiahuan
 */
public abstract class BaseAct extends RxAppCompatActivity implements BaseView {
    protected ImmersionBar immersionBar;
    protected Unbinder unBinder;
    protected OperateActCompatDelegate operateCompatDelegate;
    private LoadingFragment loadingFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());

        //inject
        ARouter.getInstance().inject(this);
        //点击关闭软键盘
        HideIMEUtil.wrap(this);
        ActivityUtil.getInstance().addActivity(this);
        operateCompatDelegate=new OperateActCompatDelegate(this);
        unBinder = ButterKnife.bind(this);
        loadingFragment=new LoadingFragment();
        initStatusBar();
        initBus();
        initData(savedInstanceState);
        setListener();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void showToast(String msg) {
        operateCompatDelegate
                .getToastor()
                .showToast(msg);
    }

    @Override
    public void showLoading() {
        if (!loadingFragment.isShowing()){
            loadingFragment.show(getSupportFragmentManager(), "loging");
        }
    }

    @Override
    public void hideLoading() {
        if (loadingFragment.isShowing()){
            loadingFragment.dismiss();
        }
    }

    /**
     * 设置是否开启
     * @return
     */
    protected boolean isEnableStatusBar() {
        return true;
    }

    protected void initStatusBar() {
        if (isEnableStatusBar()) {
            if (null==immersionBar) {
                immersionBar = ImmersionBar.with(this);
            }
            immersionBar.statusBarDarkFont(true, 0.2f)
                    .navigationBarColorInt(Color.WHITE)
                    .navigationBarDarkIcon(true, 0.2f)
                    .init();
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (null!=unBinder){
            unBinder.unbind();
        }
        ActivityUtil.getInstance().removeActivity(this);
    }
}
