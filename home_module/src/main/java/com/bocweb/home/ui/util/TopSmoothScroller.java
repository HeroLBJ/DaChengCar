package com.bocweb.home.ui.util;

import android.content.Context;

import androidx.recyclerview.widget.LinearSmoothScroller;

/**
 * @author libingjun
 * @date 2019/4/17
 */
public class TopSmoothScroller extends LinearSmoothScroller {
    public TopSmoothScroller(Context context) {
        super(context);
    }

    @Override
    protected int getHorizontalSnapPreference() {
        return SNAP_TO_START;
    }

    @Override
    protected int getVerticalSnapPreference() {
        return SNAP_TO_START;
    }
}
