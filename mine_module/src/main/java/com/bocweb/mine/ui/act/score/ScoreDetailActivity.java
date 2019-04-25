package com.bocweb.mine.ui.act.score;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bocweb.mine.R;
import com.njh.common.core.RouterHub;
import com.njh.common.flux.base.BaseFluxActivity;

/**
 * @author libingjun
 * @version 积分详情
 * @date 2019/4/25
 */
@Route(path = RouterHub.Mine.SCORE_DETAIL)
public class ScoreDetailActivity extends BaseFluxActivity {
    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public void setListener() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.mine_activity_score_detail;
    }
}
