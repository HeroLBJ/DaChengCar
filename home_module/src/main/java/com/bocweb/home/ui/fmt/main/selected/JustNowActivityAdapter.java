package com.bocweb.home.ui.fmt.main.selected;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bocweb.home.R;
import com.bocweb.home.ui.adapter.ChildPagerAdapter;
import com.bocweb.home.ui.adapter.IDelegateAdapter;
import com.bocweb.home.ui.bean.MainSelectedItem;
import com.bocweb.home.ui.bean.SelectedActivity;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

/**
 * @author libingjun
 * @date 2019/4/10
 */
public class JustNowActivityAdapter implements IDelegateAdapter<MainSelectedItem> {

    private Context mContext;
    private LayoutInflater mInflater;

    public JustNowActivityAdapter(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public boolean isForViewType(MainSelectedItem mainSelectedItem) {
        return "2".equals(mainSelectedItem.getTargetType());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = mInflater.inflate(R.layout.home_adapter_recycler_main_selected_activity, parent, false);
        return new ViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position, MainSelectedItem mainSelectedItem) {
        ViewHolder viewHolder = (ViewHolder) holder;

        List<SelectedActivity> list = mainSelectedItem.getTargetInfo().getList();

        ChildPagerAdapter adapter = new ChildPagerAdapter(mContext, list);
        viewHolder.viewPager.setAdapter(adapter);
        viewHolder.viewPager.setCurrentItem(10000);

        viewHolder.viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                adapter.setCurrentPage(position);
            }
        });
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ViewPager viewPager;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            viewPager = itemView.findViewById(R.id.child_view_pager);
        }
    }
}
