package com.bocweb.home.ui.fmt.main.selected;

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
import com.bocweb.home.ui.bean.MainSelectedItem;
import com.bocweb.home.ui.bean.TargetInfo;
import com.bocweb.home.ui.bean.UserInfo;
import com.bocweb.home.ui.image.ShowImagesDialog;
import com.njh.common.utils.img.GlideUtils;
import com.njh.common.utils.time.TimeUtil;
import com.njh.common.widget.RoundAngleImageView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author libingjun
 * @date 2019/4/9
 */
public class JustNowTwoAdapter implements IDelegateAdapter<MainSelectedItem> {

    private Context mContext;
    private LayoutInflater mInflate;

    public JustNowTwoAdapter(Context context) {
        mContext = context;
        mInflate = LayoutInflater.from(mContext);
    }

    @Override
    public boolean isForViewType(MainSelectedItem mainSelectedItem) {
        if ("1".equals(mainSelectedItem.getTargetType())) {
            TargetInfo targetInfo = mainSelectedItem.getTargetInfo();
            if (targetInfo == null) {
                return false;
            }
            List<String> photoArr = targetInfo.getPhotoArr();
            if (photoArr == null) {
                return false;
            }
            return photoArr.size() == 2;
        }
        return false;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = mInflate.inflate(R.layout.home_adapter_just_now_two, parent, false);
        return new JustNowTwoAdapter.ViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position, MainSelectedItem mainSelectedItem) {
        JustNowTwoAdapter.ViewHolder viewHolder = (JustNowTwoAdapter.ViewHolder) holder;
        TargetInfo targetInfo = mainSelectedItem.getTargetInfo();
        if (targetInfo == null) {
            return;
        }
        viewHolder.tvContent.setText(targetInfo.getDescription());
        viewHolder.tvSee.setText(targetInfo.getViews());
        viewHolder.tvSay.setText(targetInfo.getReplies());
        viewHolder.tvZan.setText(targetInfo.getLikes());
        viewHolder.tvTime.setText(TimeUtil.stampToDate(targetInfo.getTimeline()));

        List<String> photoArr = targetInfo.getPhotoArr();
        if (photoArr != null && photoArr.size() == 2) {
            GlideUtils.getInstance().loadImg(mContext, photoArr.get(0), viewHolder.ivPhoto1);
            GlideUtils.getInstance().loadImg(mContext, photoArr.get(1), viewHolder.ivPhoto2);
        }

        UserInfo userInfo = targetInfo.getUserInfo();
        if (userInfo != null) {
            viewHolder.tvName.setText(userInfo.getNickname());
            GlideUtils.getInstance().loadImg(mContext, userInfo.getAvatar(), viewHolder.civPhoto);
        }

        viewHolder.ivPhoto1.setOnClickListener(v -> {
            new ShowImagesDialog(mContext, photoArr).show();
        });

        viewHolder.ivPhoto2.setOnClickListener(v -> {
            new ShowImagesDialog(mContext, photoArr,1).show();
        });

        viewHolder.civPhoto.setOnClickListener(v -> {
            Toast.makeText(mContext, "进入个人主页", Toast.LENGTH_SHORT).show();
        });

        viewHolder.tvAdd.setOnClickListener(v -> {
            if (onStatusListener != null) {
                onStatusListener.onStatusClick(targetInfo.getAccountId());
            }
        });

        viewHolder.rlRoot.setOnClickListener(v -> {
            Toast.makeText(mContext, "进入详情页面+评论页面", Toast.LENGTH_SHORT).show();
        });

        viewHolder.tvZan.setOnClickListener(v -> {
            if (onStatusListener != null) {
                onStatusListener.onZanClick(targetInfo.getAccountId());
            }
        });
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvTime;
        TextView tvContent;
        TextView tvSee;
        TextView tvSay;
        TextView tvZan;
        RoundAngleImageView ivPhoto1;
        RoundAngleImageView ivPhoto2;
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

    public interface OnStatusListener {
        void onStatusClick(String id);
        void onZanClick(String id);
    }
}
