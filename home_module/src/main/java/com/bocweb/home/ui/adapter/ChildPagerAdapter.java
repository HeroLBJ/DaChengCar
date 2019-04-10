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
import androidx.viewpager.widget.PagerAdapter;

/**
 * @author libingjun
 * @date 2019/4/10
 */
public class ChildPagerAdapter extends PagerAdapter {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<SelectedActivity> list;

    public ChildPagerAdapter(Context context, List<SelectedActivity> list) {
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
        this.list = list;
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
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
        View layout = mInflater.inflate(R.layout.home_adapter_main_selected_activity_child, container,false);
        ImageView ivPhoto = layout.findViewById(R.id.iv_photo);
        TextView tvTime = layout.findViewById(R.id.tv_time);
        TextView tvTitle = layout.findViewById(R.id.tv_title);

        SelectedActivity item = list.get(position);

//        int screenWidth = ScreenUtils.getScreenWidth(mContext);
//        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) ivPhoto.getLayoutParams();
//        params.width = screenWidth * 4 / 5;
//        params.height = screenWidth * 12 / 25;
//        ivPhoto.setLayoutParams(params);

        GlideUtils.getInstance().loadImg(mContext, item.getCoverVal(), ivPhoto);

//        LinearLayout.LayoutParams params2 = (LinearLayout.LayoutParams) tvTitle.getLayoutParams();
//        params2.width = screenWidth * 4 / 5;
//        tvTitle.setLayoutParams(params2);

        tvTitle.setText(item.getTitle());
        tvTime.setText("报名时间：" + TimeUtil.stampToDate(item.getApplyStart(), TimeUtil.PATTERN_DATA)
                + "-" + TimeUtil.stampToDate(item.getApplyEnd(), TimeUtil.PATTERN_DATA));
        container.addView(layout);
        return layout;
    }
}
