package com.bocweb.home.ui.adapter;

import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

/**
 * @author libingjun
 * @date 2019/4/10
 */
public interface IDelegateAdapter<T> {
    boolean isForViewType(T t);

    RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType);

    void onBindViewHolder(RecyclerView.ViewHolder holder, int position, T t);
}
