package com.bocweb.home.ui.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.bocweb.home.ui.bean.MainSelectedItem;
import com.njh.common.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author libingjun
 * @date 2019/4/10
 */
public class SuperAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<IDelegateAdapter> mAdapterList = new ArrayList<>();

    private Context mContext;
    private List<MainSelectedItem> mSelectedItemList;

    public SuperAdapter(Context context, List<MainSelectedItem> selectedItemList) {
        mContext = context;
        mSelectedItemList = selectedItemList;
    }

    public void addDelegate(IDelegateAdapter delegateAdapter) {
        mAdapterList.add(delegateAdapter);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        IDelegateAdapter delegateAdapter = mAdapterList.get(viewType);
        RecyclerView.ViewHolder viewHolder = delegateAdapter.onCreateViewHolder(parent, viewType);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int viewType = holder.getItemViewType();
        IDelegateAdapter delegateAdapter = mAdapterList.get(viewType);
        delegateAdapter.onBindViewHolder(holder, position, mSelectedItemList.get(position));
    }

    @Override
    public int getItemViewType(int position) {
        MainSelectedItem mainSelectedItem = mSelectedItemList.get(position);
        for (IDelegateAdapter adapter : mAdapterList) {
            if (adapter.isForViewType(mainSelectedItem)) {
                return mAdapterList.indexOf(adapter);
            }
        }
        throw new RuntimeException("没有找到可以处理的委托Adapter");
    }

    @Override
    public int getItemCount() {
        return mSelectedItemList == null ? 0 : mSelectedItemList.size();
    }
}
