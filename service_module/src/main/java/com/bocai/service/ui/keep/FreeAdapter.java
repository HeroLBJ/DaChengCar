package com.bocai.service.ui.keep;

import android.view.View;
import android.widget.TextView;

import com.bocai.service.R;
import com.bocai.service.bean.ServicePackage;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import androidx.annotation.Nullable;

/**
 * @author libingjun
 * @date 2019/4/22
 */
public class FreeAdapter extends BaseQuickAdapter<ServicePackage, BaseViewHolder> {

    public FreeAdapter(@Nullable List<ServicePackage> data) {
        super(R.layout.service_adapter_free, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ServicePackage item) {
        helper.setText(R.id.tv_name, item.getTitle());
        helper.setText(R.id.tv_detail, "这是详情。。。");
        TextView textView = helper.getView(R.id.textView);
        TextView tvMoney = helper.getView(R.id.tv_money);
        if ("1".equals(item.getType())) {
            textView.setVisibility(View.GONE);
            tvMoney.setVisibility(View.GONE);
        } else {
            helper.setText(R.id.tv_money, item.getPrice());
            textView.setVisibility(View.VISIBLE);
            tvMoney.setVisibility(View.VISIBLE);
        }
        helper.getView(R.id.iv_select).setOnClickListener(v -> {

        });
    }
}
