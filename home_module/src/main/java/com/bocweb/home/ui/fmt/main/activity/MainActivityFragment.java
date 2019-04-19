package com.bocweb.home.ui.fmt.main.activity;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bocweb.home.R;
import com.bocweb.home.R2;
import com.bocweb.home.ui.api.MainAction;
import com.bocweb.home.ui.api.MainStore;
import com.bocweb.home.ui.bean.ActivityList;
import com.bocweb.home.ui.bean.ActivityListItem;
import com.bocweb.home.ui.util.TopSmoothScroller;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.njh.common.core.ReqTag;
import com.njh.common.core.RouterHub;
import com.njh.common.flux.base.BaseFluxFragment;
import com.njh.common.flux.stores.Store;
import com.njh.common.utils.common.ScreenUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

/**
 * @author libingjun
 * @date 2019/4/8
 */
@Route(path = RouterHub.Home.HOME_ACTIVITY)
public class MainActivityFragment extends BaseFluxFragment<MainStore, MainAction>
        implements OnRefreshLoadMoreListener, ActivityAdapter.OnSelectListener {

    @BindView(R2.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R2.id.refresh_layout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R2.id.fab_top)
    FloatingActionButton fabTop;

    private ActivityAdapter mAdapter;
    private List<ActivityListItem> mList = new ArrayList<>();
    private LinearLayoutManager llm;

    private String defaultCity = "杭州市";
    private String defaultType = "0";
    private int currentPage = 1;

    @Override
    public void initData(Bundle savedInstanceState) {
        initData();
        initRequest();
    }

    private void initData() {
        llm = new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(llm);
        mAdapter = new ActivityAdapter(getContext(), mList);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void initRequest() {
        showLoading();
        actionsCreator().getActivityList(this, currentPage, defaultCity, defaultCity);
    }

    @Override
    protected void updateView(Store.StoreChangeEvent event) {
        super.updateView(event);
        hideLoading();
        if (event.url.equals(ReqTag.REQ_TAG_GET_HOME_ACTIVITY_ACTIVITY_LIST)) {
            ActivityList item = (ActivityList) event.data;
            mList.clear();
            mList.addAll(item.getList());
            mAdapter.notifyDataSetChanged();
        }
    }

    private int scrollDistance;

    @Override
    public void setListener() {
        mRefreshLayout.setOnRefreshLoadMoreListener(this);
        fabTop.setOnClickListener(v -> onTop());
        mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                scrollDistance += dy;
                if (scrollDistance >= ScreenUtil.getScreenHeight(getContext())) {
                    fabTop.show();
                } else {
                    fabTop.hide();
                }
            }
        });

        mAdapter.setOnSelectListener(this);
    }

    private void onTop() {
        LinearSmoothScroller s1 = new TopSmoothScroller(getActivity());
        s1.setTargetPosition(0);
        llm.startSmoothScroll(s1);
    }

    @Override
    protected boolean flux() {
        return true;
    }

    @Override
    public int getLayoutId() {
        return R.layout.home_fragment_activity;
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {

    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {

    }

    @Override
    public void onSelectCityClick(String city) {
        defaultCity = city;
        initRequest();
    }

    @Override
    public void onSelectTypeClick(int type) {
        defaultType = type + "";
        initRequest();
    }
}
