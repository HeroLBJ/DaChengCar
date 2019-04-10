package com.bocweb.home.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bocweb.home.R;
import com.bocweb.home.ui.bean.SelectedActivity;
import com.njh.common.utils.img.GlideUtils;
import com.njh.common.utils.time.TimeUtil;
import com.wuhenzhizao.titlebar.utils.ScreenUtils;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author libingjun
 * @date 2019/4/10
 */
public class MainSelectedActivityChildRecyclerAdapter extends RecyclerView.Adapter<MainSelectedActivityChildRecyclerAdapter.ViewHolder> {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<SelectedActivity> list;

    public MainSelectedActivityChildRecyclerAdapter(Context context, List<SelectedActivity> list) {
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = mInflater.inflate(R.layout.home_adapter_main_selected_activity_child, parent, false);
        return new ViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SelectedActivity item = list.get(position);

        int screenWidth = ScreenUtils.getScreenWidth(mContext);
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) holder.ivPhoto.getLayoutParams();
        params.width = screenWidth * 4 / 5;
        params.height = screenWidth * 12 / 25;
        holder.ivPhoto.setLayoutParams(params);

        GlideUtils.getInstance().loadImg(mContext, item.getCoverVal(), holder.ivPhoto);

        LinearLayout.LayoutParams params2 = (LinearLayout.LayoutParams) holder.tvTitle.getLayoutParams();
        params2.width = screenWidth * 4 / 5;
        holder.tvTitle.setLayoutParams(params2);

        holder.tvTitle.setText(item.getTitle());
        holder.tvTime.setText("报名时间：" + TimeUtil.stampToDate(item.getApplyStart(), TimeUtil.PATTERN_DATA)
                + "-" + TimeUtil.stampToDate(item.getApplyEnd(), TimeUtil.PATTERN_DATA));
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivPhoto;
        TextView tvTitle;
        TextView tvTime;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivPhoto = itemView.findViewById(R.id.iv_photo);
            tvTime = itemView.findViewById(R.id.tv_time);
            tvTitle = itemView.findViewById(R.id.tv_title);
        }
    }
}
