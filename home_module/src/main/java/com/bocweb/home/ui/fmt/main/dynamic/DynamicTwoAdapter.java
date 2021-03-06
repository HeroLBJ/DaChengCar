package com.bocweb.home.ui.fmt.main.dynamic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.allen.library.CircleImageView;
import com.bocweb.home.R;
import com.bocweb.home.ui.adapter.IDelegateAdapter;
import com.bocweb.home.ui.bean.TargetInfo;
import com.bocweb.home.ui.bean.UserInfo;
import com.bocweb.home.ui.image.ShowImagesDialog;
import com.njh.common.core.RouterHub;
import com.njh.common.utils.img.GlideUtils;
import com.njh.common.utils.time.TimeUtil;
import com.njh.common.widget.RoundAngleAndSquareImageView;
import com.njh.common.widget.RoundAngleImageView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author libingjun
 * @date 2019/4/9
 */
public class DynamicTwoAdapter implements IDelegateAdapter<TargetInfo> {

    private Context mContext;
    private LayoutInflater mInflate;

    public DynamicTwoAdapter(Context context) {
        mContext = context;
        mInflate = LayoutInflater.from(mContext);
    }

    @Override
    public boolean isForViewType(TargetInfo item) {
        List<String> photoArr = item.getPhotoArr();
        if (photoArr == null) {
            return false;
        }
        return photoArr.size() == 2;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = mInflate.inflate(R.layout.home_adapter_just_now_two, parent, false);
        return new DynamicTwoAdapter.ViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position, TargetInfo item) {
        DynamicTwoAdapter.ViewHolder viewHolder = (DynamicTwoAdapter.ViewHolder) holder;
        viewHolder.tvContent.setText(item.getDescription());
        viewHolder.tvSee.setText(item.getViews());
        viewHolder.tvSay.setText(item.getReplies());
        viewHolder.tvZan.setText(item.getLikes());
        viewHolder.tvTime.setText(TimeUtil.stampToDate(item.getTimeline()));

        List<String> photoArr = item.getPhotoArr();
        if (photoArr != null && photoArr.size() == 2) {
            GlideUtils.getInstance().loadImg(mContext, photoArr.get(0), viewHolder.ivPhoto1);
            GlideUtils.getInstance().loadImg(mContext, photoArr.get(1), viewHolder.ivPhoto2);
        }

        viewHolder.ivPhoto1.setOnClickListener(v -> {
            new ShowImagesDialog(mContext, photoArr,0).show();
        });

        viewHolder.ivPhoto2.setOnClickListener(v -> {
            new ShowImagesDialog(mContext, photoArr,1).show();
        });

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
            ARouter.getInstance()
                    .build(RouterHub.Web.WEB)
                    .withString("url", "http://www.baidu.com")
                    .withString("title", "活动详情页面")
                    .navigation();
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
        RoundAngleAndSquareImageView ivPhoto1;
        RoundAngleAndSquareImageView ivPhoto2;
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
            ivPhoto1 = itemView.findViewById(R.id.iv_photo1);
            ivPhoto2 = itemView.findViewById(R.id.iv_photo2);
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
