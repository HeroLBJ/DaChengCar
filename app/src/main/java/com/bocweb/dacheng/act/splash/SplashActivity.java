/*
 * Copyright 2018 JessYan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.bocweb.dacheng.act.splash;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bocweb.dacheng.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.njh.common.core.RouterHub;
import com.njh.common.flux.base.BaseFluxActivity;
import com.njh.common.utils.arouter.ArouterUtils;
import com.njh.common.utils.common.TimeCountUtil;

import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * 启动界面
 * @author niejiahuan
 */
@Route(path = RouterHub.APP_SPLASHACTIVITY)
public class SplashActivity extends BaseFluxActivity {
    @BindView(R.id.img_adv)
    ImageView imgAdv;
    @BindView(R.id.tv_count_down)
    TextView tvCountDown;

    TimeCountUtil countUtil;
    String skipAdv = null;


    public void loadAdv(String imgPath) {
        if (null == imgPath || TextUtils.isEmpty(imgPath)) {
            countUtil = new TimeCountUtil(4000, 1000);
            countUtil.setOnTimeCountListenerUtil(new TimeCountUtil.OnTimeCountListenerUtil() {
                @Override
                public void onfinishListener() {
                    tvCountDown.setText(skipAdv);
                ArouterUtils.getInstance().navigation(SplashActivity.this,
                        RouterHub.APP_MAINACTIVITY);
                    finish();
                }

                @Override
                public void onTick(long second) {
                    tvCountDown.setText(skipAdv + second);
                }
            });
            countUtil.start();
            return;
        }
        imgAdv.setVisibility(View.VISIBLE);
        countUtil = new TimeCountUtil(4000, 1000);
        countUtil.setOnTimeCountListenerUtil(new TimeCountUtil.OnTimeCountListenerUtil() {
            @Override
            public void onfinishListener() {
                tvCountDown.setText(skipAdv);
                ArouterUtils.getInstance().navigation(SplashActivity.this,
                        RouterHub.APP_MAINACTIVITY);
                finish();
            }

            @Override
            public void onTick(long second) {
                tvCountDown.setText(skipAdv + second);
            }
        });
        DrawableTransitionOptions transitionOptions = new DrawableTransitionOptions()
                .crossFade();
        Glide.with(this)
                .load(imgPath)
                .transition(transitionOptions)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model,
                                                Target<Drawable> target, boolean isFirstResource) {
                        imgAdv.setVisibility(View.GONE);
                        ArouterUtils.getInstance().navigation(SplashActivity.this,
                                RouterHub.APP_MAINACTIVITY);
                        finish();
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model,
                                                   Target<Drawable> target, DataSource dataSource,
                                                   boolean isFirstResource) {
                        tvCountDown.setVisibility(View.VISIBLE);
                        countUtil.start();
                        return false;
                    }
                }).into(imgAdv);
    }

    @Override
    public void setListener() {
        tvCountDown.setOnClickListener(view -> {
            countUtil.cancel();
            ArouterUtils.getInstance().navigation(SplashActivity.this,
                    RouterHub.APP_MAINACTIVITY);
            finish();
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }


    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        ButterKnife.bind(this);
//        int anInt = SPUtils.getInstance(Constants.SP_APP_STATE, this).getInt(Constants.SP_APP_START_STATISTICS, 0);
//        if (anInt==0){
//            startActivity(new Intent(this,AppIntroAct.class));
//            finish();
//        }else {
            setListener();
            skipAdv = getResources().getString(R.string.skip_adv);
            loadAdv("");
//            loadAdv("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1535369533321&di=2d87700061bab4a993e4068dc2e5871a&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F01978b58072c1aa84a0d304f2f4706.png");
//            SPUtils.getInstance(Constants.SP_APP_STATE, this).put(Constants.SP_APP_START_STATISTICS,anInt+1);
//        }

    }
}
