package com.bocweb.home.ui.fmt.main.dynamic;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bocweb.home.R;
import com.bocweb.home.R2;
import com.bocweb.home.ui.adapter.SuperAdapter;
import com.bocweb.home.ui.api.MainAction;
import com.bocweb.home.ui.api.MainStore;
import com.bocweb.home.ui.bean.MainComentList;
import com.bocweb.home.ui.bean.StatusResponse;
import com.bocweb.home.ui.bean.TargetInfo;
import com.bocweb.home.ui.bean.UserInfo;
import com.bocweb.home.ui.util.TopSmoothScroller;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.njh.common.constant.Constant;
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
@Route(path = RouterHub.Home.HOME_DYNAMIC)
public class MainDynamicFragment extends BaseFluxFragment<MainStore, MainAction>
        implements OnRefreshLoadMoreListener, DynamicZeroAdapter.OnStatusListener,
        DynamicOneAdapter.OnStatusListener, DynamicTwoAdapter.OnStatusListener,
        DynamicMoreAdapter.OnStatusListener, DynamicFourAdapter.OnStatusListener {

    @BindView(R2.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R2.id.refresh_layout)
    SmartRefreshLayout mRefresh;
    @BindView(R2.id.fab_top)
    FloatingActionButton fabTop;

    private SuperAdapter mSuperAdapter;

    private List<TargetInfo> mMainSelectedItemList = new ArrayList<>();
    private LinearLayoutManager llm;

    @Override
    public void initData(Bundle savedInstanceState) {
        initData();
        initAdapter();
        initRequest();
    }

    private void initData() {
        llm = new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(llm);
    }

    private void initAdapter() {
        mSuperAdapter = new SuperAdapter(getContext(), mMainSelectedItemList);

        DynamicZeroAdapter zero = new DynamicZeroAdapter(getContext());
        DynamicOneAdapter one = new DynamicOneAdapter(getContext());
        DynamicTwoAdapter two = new DynamicTwoAdapter(getContext());
        DynamicFourAdapter four = new DynamicFourAdapter(getContext());
        DynamicMoreAdapter more = new DynamicMoreAdapter(getContext());

        mSuperAdapter.addDelegate(zero);
        mSuperAdapter.addDelegate(one);
        mSuperAdapter.addDelegate(two);
        mSuperAdapter.addDelegate(four);
        mSuperAdapter.addDelegate(more);

        mRecyclerView.setAdapter(mSuperAdapter);

        zero.setOnStatusListener(this);
        one.setOnStatusListener(this);
        two.setOnStatusListener(this);
        four.setOnStatusListener(this);
        more.setOnStatusListener(this);
    }

    private void initRequest() {
        mRefresh.autoRefresh();
        actionsCreator().getMomentList(this, "1");
    }

    @Override
    protected void updateView(Store.StoreChangeEvent event) {
        super.updateView(event);
        hideLoading();
        mRefresh.finishRefresh();
        mRefresh.finishLoadMore();
        switch (event.url) {
            case ReqTag.REQ_TAG_GET_HOME_MOMENT_LIST:
                MainComentList item = (MainComentList) event.data;
                mMainSelectedItemList.clear();
                mMainSelectedItemList.addAll(item.getList());
                mSuperAdapter.notifyDataSetChanged();
                maxNum = item.getCount();
                break;
            case ReqTag.REQ_TAG_POST_HOME_MOMENT_FOLLOW:
                StatusResponse statusResponse = (StatusResponse) event.data;
                int status = statusResponse.getStatus();

                for (TargetInfo followItem : mMainSelectedItemList) {
                    if (followItem == null) {
                        continue;
                    }
                    UserInfo userInfo = followItem.getUserInfo();
                    if (userInfo == null) {
                        continue;
                    }
                    if (followId.equals(followItem.getAccountId())) {
                        userInfo.setIsFollow(status);
                    }
                }
                mSuperAdapter.notifyDataSetChanged();
                break;
            case ReqTag.REQ_TAG_GET_HOME_ACTIVITY_ACTIVITY_LIST:
                StatusResponse zanResponse = (StatusResponse) event.data;
                int zanStatus = zanResponse.getStatus();

                for (TargetInfo zanItem : mMainSelectedItemList) {
                    if (zanItem == null) {
                        continue;
                    }

                    if (followId.equals(zanItem.getAccountId())) {
                        zanItem.setIszan(zanStatus + "");
                    }
                }
                mSuperAdapter.notifyDataSetChanged();
                break;
        }
    }

    private int scrollDistance;

    @Override
    public void setListener() {
        mRefresh.setOnRefreshLoadMoreListener(this);
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
        return R.layout.home_fragment_dynamic;
    }

    private int currentPage = Constant.Num.NUM_1;
    private int maxNum;

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        if (maxNum > currentPage * Constant.Num.NUM_10) {
            currentPage++;
            initRequest();
        } else {
            mRefresh.finishLoadMore();
        }
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        currentPage = Constant.Num.NUM_1;
        initRequest();
    }

    private String followId;

    @Override
    public void onStatusClick(String id) {
        followId = id;
        showLoading();
        actionsCreator().postMomentFollow(this, id);
    }

    @Override
    public void onZanClick(String id) {
        followId = id;
        showLoading();
        actionsCreator().postActivityActivityZan(this, id);
    }
}