package com.bocweb.mall.ui.fmt;


import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bocweb.home.R;
import com.bocweb.home.R2;
import com.njh.common.core.RouterHub;
import com.njh.common.flux.base.BaseFluxFragment;
import com.njh.common.widget.X5WebView;

import butterknife.BindView;

/**
 * 商城
 * @author niejiahuan
 */
@Route(path = RouterHub.MALL_fmt)
public class MallFmt extends BaseFluxFragment {

    @BindView(R2.id.webView)
    X5WebView webView;

    @Override
    public void initData(Bundle savedInstanceState) {
        webView.loadUrl("https://www.hao123.com/");

    }

    @Override
    public int getLayoutId() {
        return R.layout.mall_fmt;
    }
}
