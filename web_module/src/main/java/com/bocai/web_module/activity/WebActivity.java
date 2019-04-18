package com.bocai.web_module.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.bocai.web_module.R;
import com.njh.common.core.RouterHub;
import com.njh.common.flux.base.BaseFluxActivity;
import com.njh.common.widget.X5WebView;

/**
 * @author libingjun
 * @date 2019/4/18
 */
@Route(path = RouterHub.Web.WEB)
public class WebActivity extends BaseFluxActivity {

    private X5WebView mWebView;
    private TextView tvTitle;

    @Autowired
    String url;
    @Autowired
    String title;

    @Override
    public void initData(Bundle savedInstanceState) {
        mWebView = findViewById(R.id.webView);
        tvTitle = findViewById(R.id.tv_title);
        mWebView.loadUrl(url);
        tvTitle.setText(title);
    }

    @Override
    public void setListener() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.web_activity_web;
    }
}
