package com.bocai.service.ui.feedback;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bocai.service.R;
import com.bocai.service.bean.FeedbackType;
import com.bocai.service.bean.Second;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.njh.common.core.RouterHub;
import com.njh.common.utils.arouter.ArouterUtils;

import java.util.List;

import androidx.annotation.Nullable;

/**
 * @author libingjun
 * @version 1.0
 * @date 2019/4/25
 */
public class FeedBackFatherAdapter extends BaseQuickAdapter<FeedbackType, BaseViewHolder> {


    public FeedBackFatherAdapter(@Nullable List<FeedbackType> data) {
        super(R.layout.service_adapter_feedback_father, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FeedbackType item) {
        helper.setText(R.id.tv_title, item.getTitle());
        LinearLayout llFather = helper.getView(R.id.ll_father);
        llFather.removeAllViews();
        for (Second second : item.getSecond()) {
            View layout = mLayoutInflater.inflate(R.layout.service_adapter_feedback_son, null, false);
            TextView tvSon = layout.findViewById(R.id.tv_son);
            tvSon.setText(second.getTitle());
            llFather.addView(layout);
            tvSon.setOnClickListener(v -> {
                ArouterUtils.getInstance().navigation(mContext, RouterHub.Service.FEEDBACK);
            });
        }
    }
}
