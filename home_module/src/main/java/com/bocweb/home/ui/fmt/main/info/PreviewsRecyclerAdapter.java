package com.bocweb.home.ui.fmt.main.info;

import com.bocweb.home.R;
import com.bocweb.home.ui.bean.Previews;
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
public class PreviewsRecyclerAdapter extends BaseQuickAdapter<Previews, BaseViewHolder> {

    public PreviewsRecyclerAdapter(@Nullable List<Previews> data) {
        super(R.layout.home_adapter_recycler_previews, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Previews item) {
        helper.setText(R.id.tv_title, item.getTitle());
        helper.setText(R.id.tv_time, TimeUtil.stampToDate(item.getTimeline(), TimeUtil.PATTERN_DATA));
        helper.setText(R.id.tv_see,item.getViews());
        GlideUtils.getInstance().loadImg(mContext,item.getCoverVal(),helper.getView(R.id.iv_photo));
    }
}
