package com.bocweb.home.ui.fmt.main.activity;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bocweb.home.R;
import com.bocweb.home.ui.bean.ActivityListItem;
import com.njh.common.city.CityHelper;
import com.njh.common.core.RouterHub;
import com.njh.common.utils.LogUtil;
import com.njh.common.utils.img.GlideUtils;
import com.njh.common.utils.time.TimeUtil;
import com.njh.common.widget.CustomPopWindow;
import com.njh.common.widget.RoundAngleImageView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author libingjun
 * @date 2019/4/11
 */
public class ActivityAdapter extends RecyclerView.Adapter<ActivityAdapter.ViewHolder> {

    private Context context;
    private List<ActivityListItem> data;
    private LayoutInflater inflater;
    private CustomPopWindow popWindow;

    private TextView tvTab1, tvTab2, tvTab3, tvTab4, tvTab5;
    private List<TextView> selectList = new ArrayList<>();
    private int selectNum = 0;
    private String defaultCity = "杭州市";

    public ActivityAdapter(Context context, List<ActivityListItem> data) {
        this.context = context;
        this.data = data;
        inflater = LayoutInflater.from(context);

        initPop();
        initCity();
    }

    private void initPop() {
        View contentView = LayoutInflater.from(context).inflate(R.layout.home_pop_activity_select, null);
        tvTab1 = contentView.findViewById(R.id.tv_tab1);
        tvTab2 = contentView.findViewById(R.id.tv_tab2);
        tvTab3 = contentView.findViewById(R.id.tv_tab3);
        tvTab4 = contentView.findViewById(R.id.tv_tab4);
        tvTab5 = contentView.findViewById(R.id.tv_tab5);

        tvTab1.setOnClickListener(v -> onSelect(0));
        tvTab2.setOnClickListener(v -> onSelect(1));
        tvTab3.setOnClickListener(v -> onSelect(2));
        tvTab4.setOnClickListener(v -> onSelect(3));
        tvTab5.setOnClickListener(v -> onSelect(4));

        selectList.add(tvTab1);
        selectList.add(tvTab2);
        selectList.add(tvTab3);
        selectList.add(tvTab4);
        selectList.add(tvTab5);

        popWindow = new CustomPopWindow.PopupWindowBuilder(context)
                .setView(contentView)//显示的布局
                .create();//创建PopupWindow
    }

    private void onSelect(int select) {
        selectNum = select;
        for (int i = 0; i < selectList.size(); i++) {
            TextView textView = selectList.get(i);
            if (i == select) {
                textView.setTextColor(ContextCompat.getColor(context, R.color.res_red_selected));
            } else {
                textView.setTextColor(ContextCompat.getColor(context, R.color.res_gray_333));
            }
        }
        if (onSelectListener != null) {
            onSelectListener.onSelectTypeClick(select);
        }
        popWindow.dissmiss();
    }

    private OptionsPickerView pickerView;

    private void initCity() {
        pickerView = new OptionsPickerBuilder(context, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
//                String tx = CityHelper.getProvinceList().get(options1) +
//                        CityHelper.getCityList().get(options1).get(options2);
                String cityName = CityHelper.getCityList().get(options1).get(options2);
                defaultCity = cityName;
                if (onSelectListener != null) {
                    onSelectListener.onSelectCityClick(cityName);
                }
                notifyDataSetChanged();
            }
        }).setTitleText("城市选择")
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .build();
        pickerView.setPicker(CityHelper.getProvinceList(), CityHelper.getCityList());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout;
        if (viewType == 0) {
            layout = inflater.inflate(R.layout.home_adapter_activity_top, parent, false);
        } else {
            layout = inflater.inflate(R.layout.home_adapter_activity, parent, false);
        }
        return new ViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (position == 0) {
            holder.tvCity.setText(defaultCity);
            holder.tvCity.setOnClickListener(v -> {
                pickerView.show();
            });
            holder.ivSelect.setOnClickListener(v -> {
                onSelect(selectNum);
                popWindow.showAsDropDown(holder.ivSelect, 80, 0, Gravity.CENTER);
            });
        } else {
            ActivityListItem item = data.get(position - 1);
            holder.tvTitle.setText(item.getTitle());
            holder.tvTime.setText(TimeUtil.stampToDate(item.getTimelineStart(), TimeUtil.PATTERN_MONTH_DAY) + "-"
                    + TimeUtil.stampToDate(item.getTimelineEnd(), TimeUtil.PATTERN_MONTH_DAY)
                    + "活动时间");
            holder.tvStatus.setText(item.getActivityStatus(item.getStatusVal()));
            GlideUtils.getInstance().loadImg(context, item.getCoverVal(), holder.ivPhoto);

            holder.cardView.setOnClickListener(v -> ARouter.getInstance()
                    .build(RouterHub.Web.WEB)
                    .withString("url", "http://www.baidu.com")
                    .withString("title", "活动详情页面")
                    .navigation());
            holder.ivLove.setOnClickListener(v -> {

            });
        }
    }

    @Override
    public int getItemCount() {
        return data == null ? 1 : data.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return 0;
        } else {
            return 1;
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle;
        TextView tvTime;
        TextView tvStatus;
        RoundAngleImageView ivPhoto;
        CardView cardView;
        ImageView ivLove;
        TextView tvCity;
        ImageView ivSelect;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvTime = itemView.findViewById(R.id.tv_time);
            tvStatus = itemView.findViewById(R.id.tv_status);
            ivPhoto = itemView.findViewById(R.id.iv_photo);
            cardView = itemView.findViewById(R.id.cardView);
            ivLove = itemView.findViewById(R.id.iv_love);
            tvCity = itemView.findViewById(R.id.tv_city);
            ivSelect = itemView.findViewById(R.id.iv_select);
        }
    }

    private OnSelectListener onSelectListener;

    public void setOnSelectListener(OnSelectListener onSelectListener) {
        this.onSelectListener = onSelectListener;
    }

    public interface OnSelectListener {
        void onSelectCityClick(String city);

        void onSelectTypeClick(int type);
    }
}
