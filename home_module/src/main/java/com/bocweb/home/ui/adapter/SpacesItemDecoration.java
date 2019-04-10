package com.bocweb.home.ui.adapter;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

/**
 * @author libingjun
 * @date 2019/4/10
 */
public class SpacesItemDecoration extends RecyclerView.ItemDecoration {
    private int space;

    public SpacesItemDecoration(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view,
                               RecyclerView parent, RecyclerView.State state) {
        outRect.right = space;
    }
}