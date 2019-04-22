package com.bocweb.mine.ui.fmt;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.allen.library.CircleImageView;
import com.bocweb.mine.R;
import com.bocweb.mine.R2;
import com.njh.common.core.RouterHub;
import com.njh.common.flux.base.BaseFluxFragment;
import com.njh.common.utils.arouter.ArouterUtils;
import com.wuhenzhizao.titlebar.widget.CommonTitleBar;

import butterknife.BindView;

/**
 * 个人中心
 *
 * @author niejiahuan
 */
@Route(path = RouterHub.Mine.MINE_FMT)
public class MineFmt extends BaseFluxFragment {

    @BindView(R2.id.titleBar)
    CommonTitleBar titleBar;
    @BindView(R2.id.tv_car)
    TextView tvCar;
    @BindView(R2.id.tv_go)
    TextView tvGo;
    @BindView(R2.id.civ_user_photo)
    CircleImageView civUserPhoto;
    @BindView(R2.id.civ_user_level)
    CircleImageView civUserLevel;
    @BindView(R2.id.tv_sign)
    TextView tvSign;
    @BindView(R2.id.tv_user_name)
    TextView tvUserName;
    @BindView(R2.id.iv_user_sex)
    ImageView ivUserSex;
    @BindView(R2.id.rl_my_car)
    RelativeLayout rlMyCar;
    @BindView(R2.id.rl_fans)
    RelativeLayout rlFans;
    @BindView(R2.id.rl_score)
    RelativeLayout rlScore;
    @BindView(R2.id.rl_follow)
    RelativeLayout rlFollow;
    @BindView(R2.id.rl_grow)
    RelativeLayout rlGrow;
    @BindView(R2.id.scrollView)
    ScrollView scrollView;
    @BindView(R2.id.rl_activity)
    RelativeLayout rlActivity;
    @BindView(R2.id.rl_release)
    RelativeLayout rlRelease;
    @BindView(R2.id.rl_info)
    RelativeLayout rlInfo;
    @BindView(R2.id.rl_collect)
    RelativeLayout rlCollect;
    @BindView(R2.id.rl_score_bill)
    RelativeLayout rlScoreBill;
    @BindView(R2.id.rl_car_bill)
    RelativeLayout rlCarBill;
    @BindView(R2.id.rl_service_bill)
    RelativeLayout rlServiceBill;
    @BindView(R2.id.rl_comment)
    RelativeLayout rlComment;
    @BindView(R2.id.tv_complain)
    TextView tvComplain;
    @BindView(R2.id.tv_support)
    TextView tvSupport;

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public void setListener() {
        rlMyCar.setOnClickListener(v -> ArouterUtils.getInstance().navigation(getContext(), RouterHub.Mine.MY_CAR));
        civUserPhoto.setOnClickListener(v -> ArouterUtils.getInstance().navigation(getContext(), RouterHub.Mine.USER_CENTER));
        tvSign.setOnClickListener(v -> onSignUp());
        rlFans.setOnClickListener(v -> ArouterUtils.getInstance().navigation(getContext(), RouterHub.Mine.FANS));
        rlScore.setOnClickListener(v -> ArouterUtils.getInstance().navigation(getContext(), RouterHub.Mine.SCORE));
        rlFollow.setOnClickListener(v -> ArouterUtils.getInstance().navigation(getContext(), RouterHub.Mine.FOLLOW));
        rlGrow.setOnClickListener(v -> ArouterUtils.getInstance().navigation(getContext(), RouterHub.Mine.GROW));
        rlActivity.setOnClickListener(v -> ArouterUtils.getInstance().navigation(getContext(), RouterHub.Mine.ACTIVITY));
        rlRelease.setOnClickListener(v -> ArouterUtils.getInstance().navigation(getContext(), RouterHub.Mine.RELEASE));
        rlInfo.setOnClickListener(v -> ArouterUtils.getInstance().navigation(getContext(), RouterHub.Mine.INFO));
        rlCollect.setOnClickListener(v -> ArouterUtils.getInstance().navigation(getContext(), RouterHub.Mine.INFO));
        rlScoreBill.setOnClickListener(v -> ArouterUtils.getInstance().navigation(getContext(), RouterHub.Mine.SCORE_BILL));
        rlCarBill.setOnClickListener(v -> ArouterUtils.getInstance().navigation(getContext(), RouterHub.Mine.CAR_BILL));
        rlServiceBill.setOnClickListener(v -> ArouterUtils.getInstance().navigation(getContext(), RouterHub.Mine.SERVICE_BILL));
        rlComment.setOnClickListener(v -> ArouterUtils.getInstance().navigation(getContext(), RouterHub.Mine.COMMENT));
        tvComplain.setOnClickListener(v -> ArouterUtils.getInstance().navigation(getContext(), RouterHub.Mine.COMPLAIN));
        tvSupport.setOnClickListener(v -> ArouterUtils.getInstance().navigation(getContext(), RouterHub.Mine.SUPPORT));
    }

    private void onSignUp() {
        ArouterUtils.getInstance().navigation(getContext(), RouterHub.Mine.LOGIN);
    }

    @Override
    public int getLayoutId() {
        return R.layout.mine_fmt_mine;
    }
}
