package com.bocweb.home.ui.fmt.main.info;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bocweb.home.R;
import com.bocweb.home.ui.bean.Previews;
import com.njh.common.utils.LogUtil;
import com.njh.common.utils.img.GlideUtils;
import com.njh.common.utils.time.TimeUtil;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

/**
 * @author libingjun
 * @date 2019/4/11
 */
public class PreviewsRecyclerAdapter extends RecyclerView.Adapter<PreviewsRecyclerAdapter.ViewHolder> {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<Previews> mDataList;
    private List<Previews> mFlagList;

    public PreviewsRecyclerAdapter(Context context, List<Previews> dataList, List<Previews> flagList) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mDataList = dataList;
        mFlagList = flagList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout;
        if (viewType == 0) {
            layout = mInflater.inflate(R.layout.home_adapter_recycler_previews_2, parent, false);
        } else {
            layout = mInflater.inflate(R.layout.home_adapter_recycler_previews, parent, false);
        }
        return new ViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (position == 0) {
            MyViewPagerAdapter adapter = new MyViewPagerAdapter(mContext, mFlagList);
            holder.viewPager.setAdapter(adapter);
            holder.viewPager.setCurrentItem(10000);
        } else {
            Previews previews = mDataList.get(position - 1);
            if (previews != null) {
                GlideUtils.getInstance().loadImg(mContext, previews.getCoverVal(), holder.ivPhoto);
                holder.tvTitle.setText(previews.getTitle());
                holder.tvTime.setText(TimeUtil.stampToDate(previews.getTimeline(), TimeUtil.PATTERN_DATA));
                holder.tvSee.setText(previews.getViews());
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public int getItemCount() {
        if (mFlagList == null) {
            if (mDataList == null) {
                return 0;
            } else {
                return mDataList.size();
            }
        } else {
            if (mDataList == null) {
                return 1;
            } else {
                return 1 + mDataList.size();
            }
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ViewPager viewPager;
        ImageView ivPhoto;
        TextView tvTitle;
        TextView tvTime;
        TextView tvSee;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            viewPager = itemView.findViewById(R.id.view_pager);
            ivPhoto = itemView.findViewById(R.id.iv_photo);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvTime = itemView.findViewById(R.id.tv_time);
            tvSee = itemView.findViewById(R.id.tv_see);
        }
    }
}
