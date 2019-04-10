package com.bocweb.mall.ui.fmt;


import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bocweb.home.R;
import com.bocweb.home.R2;
import com.njh.common.core.RouterHub;
import com.njh.common.flux.base.BaseFluxFragment;
import com.njh.common.utils.arouter.ArouterUtils;

import butterknife.BindView;

/**
 * 商城
 * @author niejiahuan
 */
@Route(path = RouterHub.MALL_fmt)
public class MallFmt extends BaseFluxFragment {

    @BindView(R2.id.tv)
    TextView tv;

    @Override
    public void initData(Bundle savedInstanceState) {
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArouterUtils.getInstance().navigation(getActivity(),
                        RouterHub.HOME_FMT);
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.mall_fmt;
    }
}
