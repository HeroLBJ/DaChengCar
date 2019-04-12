package com.bocweb.mine.ui.act;

import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.allen.library.SuperTextView;
import com.bocweb.mine.R;
import com.bocweb.mine.R2;
import com.njh.common.BuildConfig;
import com.njh.common.core.RouterHub;
import com.njh.common.flux.base.BaseFluxActivity;
import com.wuhenzhizao.titlebar.widget.CommonTitleBar;

import butterknife.BindView;

/**
 * @author niejiahuan
 */
@Route(path = RouterHub.Mine.SETTING_ACT)
public class SettingAct extends BaseFluxActivity {


    @BindView(R2.id.titlebar)
    CommonTitleBar titlebar;
    @BindView(R2.id.supTv_developer_options)
    SuperTextView supTvDeveloperOptions;

    @Override
    public void initData(Bundle savedInstanceState) {

        if (BuildConfig.IS_DEBUG){
            supTvDeveloperOptions.setVisibility(View.VISIBLE);
        }
        titlebar.getLeftImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void setListener() {
        supTvDeveloperOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.mine_act_setting;
    }

}
