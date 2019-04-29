package com.bocweb.mine.ui.fmt;

import android.os.Build;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.Gravity;
import android.view.LayoutInflater;
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
import com.bocweb.mine.bean.SignUp;
import com.njh.common.core.ReqTag;
import com.njh.common.core.RouterHub;
import com.njh.common.flux.base.BaseFluxFragment;
import com.njh.common.flux.stores.Store;
import com.njh.common.sp.user.UserHelper;
import com.njh.common.sp.user.UserInfo;
import com.njh.common.utils.LogUtil;
import com.njh.common.utils.arouter.ArouterUtils;
import com.njh.common.utils.img.GlideUtils;
import com.njh.common.widget.CustomPopWindow;

import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
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

        // 签到按钮
        if ("1".equals(userInfo.getTodaySign())) {
            tvSign.setEnabled(false);
        } else {
            tvSign.setEnabled(true);
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (!isVisibleToUser || userInfo == null) {
            return;
        }

        initUserInfo();


        // 头像
        // 签名
        // 性别
        // 座驾号
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
            case ReqTag.Mine.MINE_USER_SIGN_UP:
                SignUp signUp = (SignUp) event.data;
                uploadSignUp(signUp);
                break;
        }
    }

    private void uploadSignUp(SignUp signUp) {
        tvSign.setEnabled(false);
        View layout = LayoutInflater.from(getContext()).inflate(R.layout.mine_pop_sign_up, null);
        TextView tvDay = layout.findViewById(R.id.tv_day);
        TextView tvScore = layout.findViewById(R.id.tv_score);

        tvDay.setText("连续签到" + signUp.getSignDay() + "天");
        SpannableString ss1 = new SpannableString(signUp.getForward());
        ForegroundColorSpan span = new ForegroundColorSpan(ContextCompat.getColor(getContext(), R.color.res_follow_end));

        ss1.setSpan(span, 0, signUp.getForward().length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        tvScore.setText("获得奖励 " + ss1 + " 积分");

        new CustomPopWindow.PopupWindowBuilder(getContext())
                .setView(layout)
                .create()
                .showAtLocation(tvSign, Gravity.CENTER, 0, 0);
        // TODO 缺少过3s后自动消失
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

        // 签到
        tvSign.setOnClickListener(v -> onSignUp());

        // 成长值
        tvScoreGrow.setOnClickListener(v -> {
            toast("敬请期待");
            // ArouterUtils.getInstance().navigation(getContext(), RouterHub.Mine.GROW)
        });

        // 积分
        tvScoreScore.setOnClickListener(v -> {
            ArouterUtils.getInstance().navigation(getContext(), RouterHub.Mine.SCORE);
        });

        // 关注与粉丝
        tvScoreFollow.setOnClickListener(v -> {
            ArouterUtils.getInstance().navigation(getContext(), RouterHub.Mine.FOLLOW);
        });
        // 关注与粉丝
        tvScoreFans.setOnClickListener(v -> {
            ArouterUtils.getInstance().navigation(getContext(), RouterHub.Mine.FOLLOW);
        });

        //我的活动
        rlActivity.setOnClickListener(v -> {
            ArouterUtils.getInstance().navigation(getContext(), RouterHub.Mine.ACTIVITY);
        });

        // 我的发布
        rlRelease.setOnClickListener(v -> {
            ArouterUtils.getInstance().navigation(getContext(), RouterHub.Mine.RELEASE);
        });

        // 我的消息
        rlInfo.setOnClickListener(v -> {
            ArouterUtils.getInstance().navigation(getContext(), RouterHub.Mine.INFO);
        });

        // 我的收藏
        rlCollect.setOnClickListener(v -> {
            ArouterUtils.getInstance().navigation(getContext(), RouterHub.Mine.INFO);
        });

        // 积分账单
        rlScoreBill.setOnClickListener(v -> {
            toast("敬请期待");
//            ArouterUtils.getInstance().navigation(getContext(), RouterHub.Mine.SCORE_BILL);
        });

        // 车辆订单
        rlCarBill.setOnClickListener(v -> {
            toast("敬请期待");
//            ArouterUtils.getInstance().navigation(getContext(), RouterHub.Mine.CAR_BILL);
        });

        // 服务订单
        rlServiceBill.setOnClickListener(v -> {
            ArouterUtils.getInstance().navigation(getContext(), RouterHub.Mine.SERVICE_BILL);
        });

        // 我的评论
        rlComment.setOnClickListener(v -> {
            ArouterUtils.getInstance().navigation(getContext(), RouterHub.Mine.COMMENT);
        });

        // 投诉意见
        tvComplain.setOnClickListener(v -> ArouterUtils.getInstance().navigation(getContext(), RouterHub.Mine.COMPLAIN));

        // 积分商城
        tvSupport.setOnClickListener(v -> ArouterUtils.getInstance().navigation(getContext(), RouterHub.Mine.SUPPORT));
    }

    private void onSignUp() {
        showLoading();
        actionsCreator().getSignUp(this);
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
