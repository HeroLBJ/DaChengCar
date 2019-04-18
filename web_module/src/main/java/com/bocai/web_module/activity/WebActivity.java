package com.bocai.web_module.activity;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.bocai.web_module.R;
import com.njh.common.core.RouterHub;
import com.njh.common.flux.base.BaseFluxActivity;
import com.njh.common.widget.X5WebView;

import butterknife.BindView;

/**
 * @author libingjun
 * @date 2019/4/18
 */
@Route(path = RouterHub.Web.WEB)
public class WebActivity extends BaseFluxActivity {

    private X5WebView mWebView;

    @Autowired
    String url;
    @Autowired
    String title;

    @Override
    public void initData(Bundle savedInstanceState) {
        mWebView = findViewById(R.id.webView);
        mWebView.loadUrl(url);
    }

    @Override
    public void setListener() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.web_activity_web;
    }
}
