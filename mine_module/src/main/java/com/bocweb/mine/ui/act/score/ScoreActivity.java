package com.bocweb.mine.ui.act.score;

import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bocweb.mine.R;
import com.bocweb.mine.R2;
import com.bocweb.mine.api.MineAction;
import com.bocweb.mine.api.MineStore;
import com.bocweb.mine.bean.MyScore;
import com.bocweb.mine.bean.SignUp;
import com.njh.common.core.ReqTag;
import com.njh.common.core.RouterHub;
import com.njh.common.flux.base.BaseFluxActivity;
import com.njh.common.flux.stores.Store;
import com.njh.common.utils.LogUtil;
import com.njh.common.utils.arouter.ArouterUtils;
import com.njh.common.widget.CustomPopWindow;
import com.wuhenzhizao.titlebar.widget.CommonTitleBar;

import androidx.core.content.ContextCompat;
import butterknife.BindView;

/**
 * @author libingjun
 * @version 我的积分
 * @date 2019/4/12
 */
@Route(path = RouterHub.Mine.SCORE)
public class ScoreActivity extends BaseFluxActivity<MineStore, MineAction> implements CommonTitleBar.OnTitleBarListener {

    @BindView(R2.id.tv_total_score)
    TextView tvTotalScore;
    @BindView(R2.id.tv1_score)
    TextView tv1Score;
    @BindView(R2.id.tv1_click)
    TextView tv1Click;
    @BindView(R2.id.tv2_score)
    TextView tv2Score;
    @BindView(R2.id.tv2_click)
    TextView tv2Click;
    @BindView(R2.id.tv3_score)
    TextView tv3Score;
    @BindView(R2.id.tv4_score)
    TextView tv4Score;
    @BindView(R2.id.tv5_score)
    TextView tv5Score;
    @BindView(R2.id.tv4_click)
    TextView tv4Click;
    @BindView(R2.id.tv5_click)
    TextView tv5Click;
    @BindView(R2.id.tv6_score)
    TextView tv6Score;
    @BindView(R2.id.tv7_score)
    TextView tv7Score;
    @BindView(R2.id.tv7_click)
    TextView tv7Click;
    @BindView(R2.id.tv8_score)
    TextView tv8Score;
    @BindView(R2.id.tv8_click)
    TextView tv8Click;
    @BindView(R2.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R2.id.toolbar_back)
    ImageButton toolbarBack;

    @Override
    public void initData(Bundle savedInstanceState) {
        initTitle();
        request();
    }

    private void initTitle() {
        toolbarTitle.setText("积分");
        toolbarBack.setOnClickListener(v -> finish());
    }

    private void request() {
        showLoading();
        actionsCreator().getIntegralInfo(this);
    }

    private MyScore mScore;

    @Override
    protected void updateView(Store.StoreChangeEvent event) {
        super.updateView(event);
        hideLoading();
        switch (event.url) {
            case ReqTag.Mine.MINE_INTEGRAL_INFO:
                mScore = (MyScore) event.data;
                uploadView();
                break;
            case ReqTag.Mine.MINE_USER_SIGN_UP:

                SignUp signUp = (SignUp) event.data;
                uploadSign(signUp);
                break;
        }
    }

    private void uploadSign(SignUp signUp) {
        tv1Click.setEnabled(false);
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
                .showAtLocation(tv1Click, Gravity.CENTER, 0, 0);
        // TODO 缺少过3s后自动消失
    }

    private void uploadView() {
        tvTotalScore.setText(mScore.getIntegral());
        MyScore.DaySign daySign = mScore.getDaySign();
        if (daySign != null) {
            if ("1".equals(daySign.getTodaySign())) {
                tv1Score.setTextColor(ContextCompat.getColor(this, R.color.res_gray_999));
                tv1Click.setText("已签到");
                tv1Click.setEnabled(false);
                tv1Click.setBackgroundResource(R.drawable.mine_score_btn_default);
                tv1Click.setTextColor(ContextCompat.getColor(this, R.color.res_gray_999));
            } else {
                tv1Score.setTextColor(ContextCompat.getColor(this, R.color.res_follow_end));
                tv1Click.setText("签到");
                tv1Click.setEnabled(true);
                tv1Click.setBackgroundResource(R.drawable.mine_score_btn_select);
                tv1Click.setTextColor(ContextCompat.getColor(this, R.color.res_follow_end));
            }
            LogUtil.e("daysign:" + daySign.toString());
            tv1Score.setText("+" + daySign.getIntegral());
        }

        MyScore.DayForward dayForward = mScore.getDayForward();
        if (dayForward != null) {
            if ("1".equals(dayForward.getMax())) {
                tv2Score.setTextColor(ContextCompat.getColor(this, R.color.res_gray_999));
                tv2Click.setText("已分享");
                tv2Click.setEnabled(false);
                tv2Click.setBackgroundResource(R.drawable.mine_score_btn_default);
                tv2Click.setTextColor(ContextCompat.getColor(this, R.color.res_gray_999));
            } else {
                tv2Score.setTextColor(ContextCompat.getColor(this, R.color.res_follow_end));
                tv2Click.setText("去分享");
                tv2Click.setEnabled(true);
                tv2Click.setBackgroundResource(R.drawable.mine_score_btn_select);
                tv2Click.setTextColor(ContextCompat.getColor(this, R.color.res_follow_end));
            }
            tv2Score.setText("+" + dayForward.getIntegral());
        }

        String birth = mScore.getBirth();
        if (TextUtils.isEmpty(birth)) {
            tv3Score.setVisibility(View.INVISIBLE);
        } else {
            tv3Score.setVisibility(View.VISIBLE);
            tv3Score.setText("+" + birth);
        }

        MyScore.FirstService firstService = mScore.getFirstService();
        if (firstService != null) {
            if ("1".equals(firstService.getComplete())) {
                tv4Score.setTextColor(ContextCompat.getColor(this, R.color.res_gray_999));
                tv4Click.setText("已完成");
                tv4Click.setEnabled(false);
                tv4Click.setBackgroundResource(R.drawable.mine_score_btn_default);
                tv4Click.setTextColor(ContextCompat.getColor(this, R.color.res_gray_999));
            } else {
                tv4Score.setTextColor(ContextCompat.getColor(this, R.color.res_follow_end));
                tv4Click.setText("去维保");
                tv4Click.setEnabled(true);
                tv4Click.setBackgroundResource(R.drawable.mine_score_btn_select);
                tv4Click.setTextColor(ContextCompat.getColor(this, R.color.res_follow_end));
            }
            tv4Score.setText("+" + firstService.getIntegral());
        }

        MyScore.FiveStart fiveStart = mScore.getFiveStart();
        if (fiveStart != null) {
            if ("1".equals(fiveStart.getExist())) {
                tv5Click.setText("去评价");
            } else {
                tv5Click.setText("去维保");
            }
            tv5Score.setText("+" + fiveStart.getIntegral());
        }

        String register = mScore.getRegister();
        if (!TextUtils.isEmpty(register)) {
            tv6Score.setText("+" + register);
        }

        MyScore.MemberInfo memberInfo = mScore.getMemberInfo();
        if (memberInfo != null) {
            if ("1".equals(memberInfo.getIsUpdate())) {
                tv7Score.setTextColor(ContextCompat.getColor(this, R.color.res_gray_999));
                tv7Click.setText("已完善");
                tv7Click.setEnabled(false);
                tv7Click.setBackgroundResource(R.drawable.mine_score_btn_default);
                tv7Click.setTextColor(ContextCompat.getColor(this, R.color.res_gray_999));
            } else {
                tv7Score.setTextColor(ContextCompat.getColor(this, R.color.res_follow_end));
                tv7Click.setText("未完善");
                tv7Click.setEnabled(true);
                tv7Click.setBackgroundResource(R.drawable.mine_score_btn_select);
                tv7Click.setTextColor(ContextCompat.getColor(this, R.color.res_follow_end));
            }
            tv7Score.setText("+" + memberInfo.getIntegral());
        }

        MyScore.CarOwner carOwner = mScore.getCarOwner();
        if (carOwner != null) {
            if ("1".equals(carOwner.getIsCarOwner())) {
                tv8Score.setTextColor(ContextCompat.getColor(this, R.color.res_gray_999));
                tv8Click.setText("已认证");
                tv8Click.setEnabled(false);
                tv8Click.setBackgroundResource(R.drawable.mine_score_btn_default);
                tv8Click.setTextColor(ContextCompat.getColor(this, R.color.res_gray_999));
            } else {
                tv8Score.setTextColor(ContextCompat.getColor(this, R.color.res_follow_end));
                tv8Click.setText("未认证");
                tv8Click.setEnabled(true);
                tv8Click.setBackgroundResource(R.drawable.mine_score_btn_select);
                tv8Click.setTextColor(ContextCompat.getColor(this, R.color.res_follow_end));
            }
            tv8Score.setText("+" + carOwner.getIntegral());
        }
    }

    /**
     * 去签到
     */
    public void onSignUp(View view) {
        showLoading();
        actionsCreator().getSignUp(this);
    }

    /**
     * 去分享
     */
    public void onShare(View view) {

    }

    /**
     * 去维保
     */
    public void onKeep(View view) {

    }

    /**
     * 去维保,去评论
     */
    public void onKeep2(View view) {

    }

    /**
     * 积分明细
     */
    public void goDetail(View view) {
        ArouterUtils.getInstance().navigation(this, RouterHub.Mine.SCORE_DETAIL);
    }

    @Override
    public void setListener() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.mine_activity_score;
    }

    @Override
    protected boolean flux() {
        return true;
    }

    @Override
    public void onClicked(View v, int action, String extra) {

    }
}
