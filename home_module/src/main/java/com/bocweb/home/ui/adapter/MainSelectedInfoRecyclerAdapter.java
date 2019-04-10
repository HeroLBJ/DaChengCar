package com.bocweb.home.ui.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bocweb.home.R;
import com.bocweb.home.ui.bean.MainSelectedItem;
import com.bocweb.home.ui.bean.TargetInfo;
import com.njh.common.utils.LogUtil;
import com.njh.common.utils.img.GlideUtils;
import com.wuhenzhizao.titlebar.utils.ScreenUtils;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author libingjun
 * @date 2019/4/9
 */
public class MainSelectedInfoRecyclerAdapter implements IDelegateAdapter<MainSelectedItem> {

    private Context mContext;
    private LayoutInflater mInflate;

    public MainSelectedInfoRecyclerAdapter(Context context) {
        mContext = context;
        mInflate = LayoutInflater.from(mContext);
    }

    @Override
    public boolean isForViewType(MainSelectedItem mainSelectedItem) {
        return "3".equals(mainSelectedItem.getTargetType());
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = mInflate.inflate(R.layout.home_adapter_recycler_main_selected_info, parent, false);
        return new InfoViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position, MainSelectedItem mainSelectedItem) {
        InfoViewHolder viewHolder = (InfoViewHolder) holder;
        TargetInfo targetInfo = mainSelectedItem.getTargetInfo();
        if (targetInfo == null) {
            return;
        }
        viewHolder.tvTitle.setText(targetInfo.getTitle());
        viewHolder.tvSee.setText(targetInfo.getViews());
        viewHolder.tvSay.setText(targetInfo.getReplies());
        viewHolder.tvZan.setText(targetInfo.getLikes());

        int screenWidth = ScreenUtils.getScreenWidth(mContext);
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) viewHolder.ivPhoto.getLayoutParams();
        params.width = screenWidth;
        params.height = (screenWidth - ScreenUtils.dp2PxInt(mContext, 40)) * 3 / 5;
        viewHolder.ivPhoto.setLayoutParams(params);

        GlideUtils.getInstance().loadImg(mContext, targetInfo.getCoverVal(), viewHolder.ivPhoto);
    }

    class InfoViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle;
        TextView tvSee;
        TextView tvSay;
        TextView tvZan;
        ImageView ivPhoto;

        public InfoViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvSee = itemView.findViewById(R.id.tv_see);
            tvSay = itemView.findViewById(R.id.tv_say);
            tvZan = itemView.findViewById(R.id.tv_zan);
            ivPhoto = itemView.findViewById(R.id.iv_photo);
        }
    }
}
