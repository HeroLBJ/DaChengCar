package com.bocweb.mine.ui.act.score;

import com.bocweb.mine.R;
import com.bocweb.mine.bean.ScoreDetail;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.njh.common.utils.time.TimeUtil;

import java.util.List;

import androidx.annotation.Nullable;

/**
 * @author libingjun
 * @version 1.0
 * @date 2019/4/26
 */
public class ScoreGetAdapter extends BaseQuickAdapter<ScoreDetail.ScoreDetailList, BaseViewHolder> {
    public ScoreGetAdapter(@Nullable List<ScoreDetail.ScoreDetailList> data) {
        super(R.layout.mine_adapter_score_get, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ScoreDetail.ScoreDetailList item) {
        helper.setText(R.id.tv_name, item.getReson());
        helper.setText(R.id.tv_time, TimeUtil.stampToDate(item.getTimeline(), "MM.dd HH:mm"));
        helper.setText(R.id.tv_score, "+ " + item.getCredit());
    }
}
