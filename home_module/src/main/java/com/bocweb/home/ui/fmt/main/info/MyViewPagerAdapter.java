package com.bocweb.home.ui.fmt.main.info;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bocweb.home.R;
import com.bocweb.home.ui.bean.Previews;
import com.njh.common.utils.img.GlideUtils;
import com.njh.common.utils.time.TimeUtil;
import com.njh.common.widget.RoundAngleImageView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

/**
 * @author libingjun
 * @date 2019/4/12
 */
public class MyViewPagerAdapter extends PagerAdapter {

    private Context mContext;
    private List<Previews> mList;
    private LayoutInflater inflater;
    private int count;

    public MyViewPagerAdapter(Context context, List<Previews> list) {
        mContext = context;
        mList = list;
        inflater = LayoutInflater.from(context);
        count = list == null ? 0 : list.size();
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
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
        View layout = inflater.inflate(R.layout.home_adapter_pager_info, container, false);
        RoundAngleImageView ivPhoto = layout.findViewById(R.id.iv_photo);
        TextView tvTitle = layout.findViewById(R.id.tv_title);
        TextView tvTime = layout.findViewById(R.id.tv_time);

        Previews item = mList.get(position % count);

        GlideUtils.getInstance().loadImg(mContext,item.getCoverVal(),ivPhoto);
        tvTitle.setText(item.getTitle());
        tvTime.setText(TimeUtil.stampToDate(item.getTimeline(),TimeUtil.PATTERN_DATA));
        container.addView(layout);
        return layout;
    }
}
