package com.bocai.service.ui.keep;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bocai.service.R;
import com.bocai.service.bean.ServicePackage;
import com.bocai.service.bean.ServicePackageDetail;
import com.bocai.service.ui.keep.select.SelectShopAdapter;
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
        List<ServicePackageDetail> details = item.getDetails();

        TextView tvDetail = helper.getView(R.id.tv_detail);
        if (details == null || details.size() == 0) {
            tvDetail.setVisibility(View.INVISIBLE);
        } else {
            StringBuilder builder = new StringBuilder();
            for (ServicePackageDetail d : details) {
                builder.append(d.getTitle() + "、");
            }
            String auto = builder.toString().substring(0, builder.length() - 1);
            tvDetail.setText(auto);
        }

        TextView textView = helper.getView(R.id.textView);
        TextView tvMoney = helper.getView(R.id.tv_money);
        ImageView ivPhoto = helper.getView(R.id.iv_photo);
        if ("1".equals(item.getType())) {
            textView.setVisibility(View.GONE);
            tvMoney.setVisibility(View.GONE);
            ivPhoto.setVisibility(View.VISIBLE);
        } else {
            helper.setText(R.id.tv_money, item.getPrice());
            textView.setVisibility(View.VISIBLE);
            tvMoney.setVisibility(View.VISIBLE);
            ivPhoto.setVisibility(View.GONE);
        }
        ImageView ivSelect = helper.getView(R.id.iv_select);
        if (item.isSelect()) {
            ivSelect.setBackgroundResource(R.drawable.service_keep_select);
        } else {
            ivSelect.setBackgroundResource(R.drawable.service_keep_default);
        }
        helper.getView(R.id.iv_select).setOnClickListener(v -> {
            if (onSelectOneListener != null) {
                onSelectOneListener.onSelectOneClick(item, item.isSelect(), "1".equals(item.getType()));
            }
        });
    }

    private OnSelectOneListener onSelectOneListener;

    public void setOnSelectOneListener(OnSelectOneListener onSelectOneListener) {
        this.onSelectOneListener = onSelectOneListener;
    }

    public interface OnSelectOneListener {
        void onSelectOneClick(ServicePackage item, boolean select, boolean isOne);
    }
}
