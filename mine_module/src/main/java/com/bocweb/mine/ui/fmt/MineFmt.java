package com.bocweb.mine.ui.fmt;


import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bocweb.mine.R;
import com.bocweb.mine.R2;
import com.njh.common.core.RouterHub;
import com.njh.common.flux.base.BaseFluxFragment;
import com.wuhenzhizao.titlebar.widget.CommonTitleBar;

import butterknife.BindView;

/**
 * 个人中心
 *
 * @author niejiahuan
 */
@Route(path = RouterHub.MINE_FMT)
public class MineFmt extends BaseFluxFragment {

    @BindView(R2.id.titlebar)
    CommonTitleBar titlebar;

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public void setListener() {
        titlebar.getRightImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance()
                        .build(RouterHub.SETTING_ACT)
                        .navigation();
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.mine_fmt_mine;
    }

}
