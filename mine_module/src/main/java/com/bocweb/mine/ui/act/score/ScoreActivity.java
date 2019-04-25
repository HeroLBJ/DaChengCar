package com.bocweb.mine.ui.act.score;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bocweb.mine.R;
import com.bocweb.mine.R2;
import com.bocweb.mine.api.MineAction;
import com.bocweb.mine.api.MineStore;
import com.bocweb.mine.bean.MyScore;
import com.njh.common.core.ReqTag;
import com.njh.common.core.RouterHub;
import com.njh.common.flux.base.BaseFluxActivity;
import com.njh.common.flux.stores.Store;

import androidx.core.content.ContextCompat;
import butterknife.BindView;

/**
 * @author libingjun
 * @version 我的积分
 * @date 2019/4/12
 */
@Route(path = RouterHub.Mine.SCORE)
public class ScoreActivity extends BaseFluxActivity<MineStore, MineAction> {

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

    @Override
    public void initData(Bundle savedInstanceState) {
        request();
    }

    private void request() {
        showLoading();
        actionsCreator().getIntegralInfo(this);
    }

    @Override
    protected void updateView(Store.StoreChangeEvent event) {
        super.updateView(event);
        hideLoading();
        switch (event.url) {
            case ReqTag.Mine.MINE_INTEGRAL_INFO:
                MyScore bean1 = (MyScore) event.data;
                uploadView(bean1);
                break;
        }
    }

    private void uploadView(MyScore score) {
        tvTotalScore.setText(score.getIntegral());
        MyScore.DaySign daySign = score.getDaySign();
        if (daySign != null) {
            if ("1".equals(daySign.getTodaySign())) {
                tv1Score.setTextColor(ContextCompat.getColor(this, R.color.res_gray_999));
                tv1Click.setText("已签到");
                tv1Click.setEnabled(false);
                tv1Click.setBackgroundResource(R.drawable.mine_score_btn_default);
                tv1Click.setTextColor(ContextCompat.getColor(this, R.color.res_gray_999));
            } else {
                tv1Score.setTextColor(ContextCompat.getColor(this, R.color.res_follow_end));
                tv1Click.setText("未签到");
                tv1Click.setEnabled(true);
                tv1Click.setBackgroundResource(R.drawable.mine_score_btn_select);
                tv1Click.setTextColor(ContextCompat.getColor(this, R.color.res_follow_end));
            }
            tv1Score.setText("+" + daySign.getIntegral());
        }

        MyScore.DayForward dayForward = score.getDayForward();
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
            tv1Score.setText("+" + dayForward.getIntegral());
        }

        String birth = score.getBirth();
        if (TextUtils.isEmpty(birth)) {
            tv3Score.setVisibility(View.INVISIBLE);
        } else {
            tv3Score.setVisibility(View.VISIBLE);
            tv3Score.setText("+" + birth);
        }

        MyScore.FirstService firstService = score.getFirstService();
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
            tv1Score.setText("+" + firstService.getIntegral());
        }

        MyScore.FiveStart fiveStart = score.getFiveStart();
        if (fiveStart != null) {
            if ("1".equals(fiveStart.getExist())) {
                tv5Click.setText("去评价");
            } else {
                tv5Click.setText("去维保");
            }
            tv5Score.setText("+" + fiveStart.getIntegral());
        }

        String register = score.getRegister();
        if (!TextUtils.isEmpty(register)) {
            tv6Score.setText("+" + register);
        }

        MyScore.MemberInfo memberInfo = score.getMemberInfo();
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

        MyScore.CarOwner carOwner = score.getCarOwner();
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
}
