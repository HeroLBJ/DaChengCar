package com.bocweb.home.ui.fmt.main.activity;

import com.bocweb.home.R;
import com.bocweb.home.ui.bean.ActivityListItem;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.njh.common.utils.img.GlideUtils;
import com.njh.common.utils.time.TimeUtil;

import java.util.List;

import androidx.annotation.Nullable;

/**
 * @author libingjun
 * @date 2019/4/11
 */
public class ActivityAdapter extends BaseQuickAdapter<ActivityListItem, BaseViewHolder> {

    public ActivityAdapter(@Nullable List<ActivityListItem> data) {
        super(R.layout.home_adapter_activity, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ActivityListItem item) {
        helper.setText(R.id.tv_title, item.getTitle());
        helper.setText(R.id.tv_time,
                TimeUtil.stampToDate(item.getTimelineStart(), TimeUtil.PATTERN_MONTH_DAY) + "-"
                        + TimeUtil.stampToDate(item.getTimelineEnd(), TimeUtil.PATTERN_MONTH_DAY)
                        + "活动时间");
        helper.setText(R.id.tv_status, item.getActivityStatus(item.getStatusVal()));
        GlideUtils.getInstance().loadImg(mContext, item.getCoverVal(), helper.getView(R.id.iv_photo));
    }
}
