package com.bocweb.home.ui.fmt.main.dynamic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.allen.library.CircleImageView;
import com.bocweb.home.R;
import com.bocweb.home.ui.adapter.IDelegateAdapter;
import com.bocweb.home.ui.bean.TargetInfo;
import com.bocweb.home.ui.bean.UserInfo;
import com.njh.common.utils.img.GlideUtils;
import com.njh.common.utils.time.TimeUtil;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author libingjun
 * @date 2019/4/9
 */
public class DynamicZeroAdapter implements IDelegateAdapter<TargetInfo> {

    private Context mContext;
    private LayoutInflater mInflate;

    public DynamicZeroAdapter(Context context) {
        mContext = context;
        mInflate = LayoutInflater.from(mContext);
    }

    @Override
    public boolean isForViewType(TargetInfo item) {
        List<String> photoArr = item.getPhotoArr();
        return photoArr == null || photoArr.size() == 0;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = mInflate.inflate(R.layout.home_adapter_just_now_zero, parent, false);
        return new DynamicZeroAdapter.ViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position, TargetInfo item) {
        DynamicZeroAdapter.ViewHolder viewHolder = (DynamicZeroAdapter.ViewHolder) holder;

        viewHolder.tvContent.setText(item.getDescription());
        viewHolder.tvSee.setText(item.getViews());
        viewHolder.tvSay.setText(item.getReplies());
        viewHolder.tvZan.setText(item.getLikes());
        viewHolder.tvTime.setText(TimeUtil.stampToDate(item.getTimeline()));

        UserInfo userInfo = item.getUserInfo();
        if (userInfo != null) {
            viewHolder.tvName.setText(userInfo.getNickname());
            GlideUtils.getInstance().loadImg(mContext, userInfo.getAvatar(), viewHolder.civPhoto);
        }

        viewHolder.civPhoto.setOnClickListener(v -> {
            Toast.makeText(mContext, "进入个人主页", Toast.LENGTH_SHORT).show();
        });

        viewHolder.tvAdd.setOnClickListener(v -> {
            if (onStatusListener != null) {
                onStatusListener.onStatusClick(item.getAccountId());
            }
        });

        viewHolder.rlRoot.setOnClickListener(v -> {
            Toast.makeText(mContext, "进入详情页面+评论页面", Toast.LENGTH_SHORT).show();
        });

        viewHolder.tvZan.setOnClickListener(v -> {
            if (onStatusListener != null) {
                onStatusListener.onZanClick(item.getAccountId());
            }
        });
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvTime;
        TextView tvContent;
        TextView tvSee;
        TextView tvSay;
        TextView tvZan;
        CircleImageView civPhoto;
        TextView tvName;
        TextView tvAdd;
        RelativeLayout rlRoot;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvContent = itemView.findViewById(R.id.tv_content);
            tvSee = itemView.findViewById(R.id.tv_see);
            tvSay = itemView.findViewById(R.id.tv_say);
            tvZan = itemView.findViewById(R.id.tv_zan);
            tvTime = itemView.findViewById(R.id.tv_time);
            civPhoto = itemView.findViewById(R.id.civ_photo);
            tvName = itemView.findViewById(R.id.tv_nickname);
            tvAdd = itemView.findViewById(R.id.tv_add);
            rlRoot = itemView.findViewById(R.id.rl_layout);
        }
    }

    private OnStatusListener onStatusListener;

    public void setOnStatusListener(OnStatusListener onStatusListener) {
        this.onStatusListener = onStatusListener;
    }

    interface OnStatusListener {
        void onStatusClick(String id);
        void onZanClick(String id);
    }
}
