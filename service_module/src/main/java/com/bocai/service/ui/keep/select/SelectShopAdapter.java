package com.bocai.service.ui.keep.select;

import com.bocai.service.R;
import com.bocai.service.bean.ServiceDealers;
import com.bocai.service.widget.FlexibleRatingBar;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.njh.common.utils.LogUtil;

import java.util.List;

import androidx.annotation.Nullable;

/**
 * @author libingjun
 * @date 2019/4/23
 */
public class SelectShopAdapter extends BaseQuickAdapter<ServiceDealers, BaseViewHolder> {
    public SelectShopAdapter(@Nullable List data) {
        super(R.layout.service_adapter_select_shop, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ServiceDealers item) {
        helper.setText(R.id.tv_name, item.getTitle());
        helper.setText(R.id.tv_detail, item.getFullTitle());
        helper.setText(R.id.tv_call, item.getTelphone());
        FlexibleRatingBar ratingBar = helper.getView(R.id.ratingBar);

        ratingBar.setRating(Float.valueOf(item.getStar()));
        helper.setText(R.id.tv_distance, item.getDistance());
        helper.getView(R.id.rl_root).setOnClickListener(v -> {
            if (onSelectListener != null) {
                onSelectListener.onSelectClick(item.getId(), item.getTitle());
            }
        });
    }

    private OnSelectListener onSelectListener;

    public void setOnSelectListener(OnSelectListener onSelectListener) {
        this.onSelectListener = onSelectListener;
    }

    public interface OnSelectListener {
        void onSelectClick(String id, String title);
    }
}
