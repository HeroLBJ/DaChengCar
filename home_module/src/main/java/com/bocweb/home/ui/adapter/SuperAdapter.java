package com.bocweb.home.ui.adapter;

import android.content.Context;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author libingjun
 * @date 2019/4/10
 */
public class SuperAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<IDelegateAdapter> mAdapterList = new ArrayList<>();

    private Context mContext;
    private List<T> mList;

    public SuperAdapter(Context context, List<T> list) {
        mContext = context;
        mList = list;
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
        delegateAdapter.onBindViewHolder(holder, position, mList.get(position));
    }

    @Override
    public int getItemViewType(int position) {
        T item = mList.get(position);
        for (IDelegateAdapter adapter : mAdapterList) {
            if (adapter.isForViewType(item)) {
                return mAdapterList.indexOf(adapter);
            }
        }
        throw new RuntimeException("没有找到可以处理的委托Adapter");
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }
}
