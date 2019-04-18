package com.bocweb.home.ui.adapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bocweb.home.R;
import com.bocweb.home.ui.bean.SelectedActivity;
import com.njh.common.core.RouterHub;
import com.njh.common.utils.LogUtil;
import com.njh.common.utils.img.GlideUtils;
import com.njh.common.utils.time.TimeUtil;
import com.njh.common.widget.RoundAngleImageView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.PagerAdapter;

/**
 * @author libingjun
 * @date 2019/4/10
 */
public class ChildPagerAdapter extends PagerAdapter {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<SelectedActivity> list;
    private int size;
    private int indexDefault = 10000;

    private SparseArray<TextView> tvArray = new SparseArray<>();

    public ChildPagerAdapter(Context context, List<SelectedActivity> list) {
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
        this.list = list;
        size = list.size();
    }

    public void setCurrentPage(int currentPage) {
        for (int i = 0; i < tvArray.size(); i++) {
            int position = tvArray.keyAt(i);
            TextView tv = tvArray.get(position);
            if (currentPage == position) {
                tv.setTextColor(ContextCompat.getColor(mContext, R.color.res_red_selected));
            } else {
                tv.setTextColor(ContextCompat.getColor(mContext, R.color.res_gray_888));
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list == null ? 0 : Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View layout = mInflater.inflate(R.layout.home_adapter_main_selected_activity_child, container, false);
        RoundAngleImageView ivPhoto = layout.findViewById(R.id.iv_photo);
        TextView tvTime = layout.findViewById(R.id.tv_time);
        TextView tvTitle = layout.findViewById(R.id.tv_title);
        CardView cardView = layout.findViewById(R.id.cardView);

        SelectedActivity item = list.get(position % size);

        GlideUtils.getInstance().loadImg(mContext, item.getCoverVal(), ivPhoto);

        tvTitle.setText(item.getTitle());
        tvTime.setText("报名时间：" + TimeUtil.stampToDate(item.getApplyStart(), TimeUtil.PATTERN_DATA)
                + "-" + TimeUtil.stampToDate(item.getApplyEnd(), TimeUtil.PATTERN_DATA));

        tvArray.put(position, tvTime);

        if (position == indexDefault) {
            tvTime.setTextColor(ContextCompat.getColor(mContext, R.color.res_red_selected));
            indexDefault = -1;
        }

        cardView.setOnClickListener(v -> {
            ARouter.getInstance()
                    .build(RouterHub.Web.WEB)
                    .withString("url", "http://www.baidu.com")
                    .withString("title", "活动详情页面")
                    .navigation();
        });

        container.addView(layout);
        return layout;
    }

}
