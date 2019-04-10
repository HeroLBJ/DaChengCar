package com.njh.base.ui.act;

import android.content.Context;
import android.os.Bundle;

import com.njh.base.ui.view.BaseView;
import com.njh.base.utils.ActivityUtil;
import com.trello.rxlifecycle3.components.support.RxFragmentActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 基类Activity
 * 备注:所有的Activity都继承自此Activity
 * 1.规范团队开发
 * 2.统一处理Activity所需配置,初始化
 *
 * @author niejiahuan
 */
public abstract class BaseFmtAct extends RxFragmentActivity implements BaseView {

    protected Context mContext;
    protected Unbinder unBinder;
    protected OperateFmtActCompatDelegate compatDelegate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ActivityUtil.getInstance().addActivity(this);
        mContext = this;
        unBinder = ButterKnife.bind(this);
        compatDelegate = new OperateFmtActCompatDelegate(this);
        initBus();
        initData(savedInstanceState);
        setListener();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (null != unBinder) {
            unBinder.unbind();
        }
        ActivityUtil.getInstance().removeActivity(this);
    }
}