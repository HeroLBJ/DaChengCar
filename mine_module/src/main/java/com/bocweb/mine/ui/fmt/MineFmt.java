package com.bocweb.mine.ui.fmt;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.allen.library.CircleImageView;
import com.bocweb.mine.R;
import com.bocweb.mine.R2;
import com.bocweb.mine.api.MineAction;
import com.bocweb.mine.api.MineStore;
import com.njh.common.core.ReqTag;
import com.njh.common.core.RouterHub;
import com.njh.common.flux.base.BaseFluxFragment;
import com.njh.common.flux.stores.Store;
import com.njh.common.sp.user.UserHelper;
import com.njh.common.sp.user.UserInfo;
import com.njh.common.utils.LogUtil;
import com.njh.common.utils.arouter.ArouterUtils;
import com.njh.common.utils.img.GlideUtils;

import androidx.annotation.RequiresApi;
import androidx.core.widget.NestedScrollView;
import butterknife.BindView;

/**
 * @author libingjun
 * @version 用户 root fragment
 * @date 2019/4/28
 */
@Route(path = RouterHub.Mine.MINE_FMT)
public class MineFmt extends BaseFluxFragment<MineStore, MineAction> {

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
    @BindView(R2.id.scrollView)
    NestedScrollView scrollView;
    @BindView(R2.id.tv_score_grow)
    TextView tvScoreGrow;
    @BindView(R2.id.tv_score_score)
    TextView tvScoreScore;
    @BindView(R2.id.tv_score_follow)
    TextView tvScoreFollow;
    @BindView(R2.id.tv_score_fans)
    TextView tvScoreFans;
    @BindView(R2.id.tv_user_say)
    TextView tvUserSay;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void initData(Bundle savedInstanceState) {

        rlMyCar.setPadding(0, 200, 0, 0);

        scrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                LogUtil.e(scrollY + " - " + oldScrollY);
            }
        });
        request();
    }

    private void initUserInfo() {
        UserHelper.putUserInfo(userInfo);

        GlideUtils.getInstance().loadImg(getActivity(), userInfo.getAvatar(), civUserPhoto);
        tvUserSay.setText(userInfo.getSightml());
        tvScoreGrow.setText(userInfo.getGrowthValue());
        tvScoreScore.setText(userInfo.getIntegral());
        tvScoreFollow.setText(userInfo.getFocus());
        tvScoreFans.setText(userInfo.getFans());
        LogUtil.e(userInfo.getAvatar());
        LogUtil.e(userInfo.getWxxAvatarurl());
    }

    private void request() {
        showLoading();
        actionsCreator().getMemberCenter(this);
    }

    private UserInfo userInfo;

    @Override
    protected void updateView(Store.StoreChangeEvent event) {
        super.updateView(event);
        hideLoading();
        switch (event.url) {
            case ReqTag.Mine.MINE_MEMBER_CENTER:
                userInfo = (UserInfo) event.data;
                initUserInfo();
                break;
        }
    }

    @Override
    public void setListener() {
        rlMyCar.setOnClickListener(v -> ArouterUtils.getInstance().navigation(getContext(), RouterHub.Mine.MY_CAR));
        civUserPhoto.setOnClickListener(v -> {
            ARouter.getInstance().build(RouterHub.Mine.USER_CENTER)
                    .withSerializable("UserInfo", userInfo)
                    .withBoolean("self", true)
                    .navigation();
        });
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
        ArouterUtils.getInstance().navigation(getContext(), RouterHub.Mine.BIND_PHONE);
    }

    @Override
    public int getLayoutId() {
        return R.layout.mine_fmt_mine;
    }

    @Override
    protected boolean flux() {
        return true;
    }
}
