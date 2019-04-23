package com.bocai.service.ui.select;

import com.bocai.service.R;
import com.bocai.service.bean.Select;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import androidx.annotation.Nullable;

/**
 * @author libingjun
 * @date 2019/4/23
 */
public class SelectAdapter extends BaseQuickAdapter<Select, BaseViewHolder> {
    public SelectAdapter(@Nullable List<Select> data) {
        super(R.layout.service_adapter_select, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Select item) {
        helper.setText(R.id.tv_name, item.getPartsNumber());
        helper.setText(R.id.tv_detail, item.getTitle());
        helper.setText(R.id.tv_car, item.getCarTitle());
        helper.setText(R.id.tv_price, item.getPrice());
        helper.setText(R.id.tv_unit, "/" + item.getUnits());
    }
}
