package com.bocweb.home.ui.fmt.main.selected;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bocweb.home.R;
import com.bocweb.home.ui.adapter.IDelegateAdapter;
import com.bocweb.home.ui.bean.MainSelectedItem;
import com.bocweb.home.ui.bean.TargetInfo;
import com.bocweb.home.ui.image.ShowImagesDialog;
import com.njh.common.core.RouterHub;
import com.njh.common.utils.img.GlideUtils;
import com.njh.common.widget.RoundAngleImageView;
import com.wuhenzhizao.titlebar.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author libingjun
 * @date 2019/4/9
 */
public class JustNowInfoAdapter implements IDelegateAdapter<MainSelectedItem> {

    private Context mContext;
    private LayoutInflater mInflate;

    public JustNowInfoAdapter(Context context) {
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

        viewHolder.ivPhoto.setOnClickListener(v -> {
            List<String> list = new ArrayList<>();
            list.add(targetInfo.getCoverVal());
            new ShowImagesDialog(mContext, list).show();
        });

        viewHolder.rlRoot.setOnClickListener(v -> {
            ARouter.getInstance()
                    .build(RouterHub.Web.WEB)
                    .withString("url", "http://www.baidu.com")
                    .withString("title", "咨询详情页面")
                    .navigation();
        });

        viewHolder.tvZan.setOnClickListener(v -> {
            if (onStatusListener != null) {
                onStatusListener.onInfoZanClick(targetInfo.getAccountId());
            }
        });
    }

    class InfoViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle;
        TextView tvSee;
        TextView tvSay;
        TextView tvZan;
        RoundAngleImageView ivPhoto;
        RelativeLayout rlRoot;

        public InfoViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvSee = itemView.findViewById(R.id.tv_see);
            tvSay = itemView.findViewById(R.id.tv_say);
            tvZan = itemView.findViewById(R.id.tv_zan);
            ivPhoto = itemView.findViewById(R.id.iv_photo);
            rlRoot = itemView.findViewById(R.id.rl_root);
        }
    }

    private OnStatusListener onStatusListener;

    public void setOnStatusListener(OnStatusListener onStatusListener) {
        this.onStatusListener = onStatusListener;
    }

    public interface OnStatusListener {
        void onInfoZanClick(String id);
    }
}
