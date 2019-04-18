package com.bocweb.home.ui.fmt.main.selected;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bocweb.home.R;
import com.bocweb.home.R2;
import com.bocweb.home.ui.adapter.SuperAdapter;
import com.bocweb.home.ui.api.MainAction;
import com.bocweb.home.ui.api.MainStore;
import com.bocweb.home.ui.bean.StatusResponse;
import com.bocweb.home.ui.bean.MainSelectedFlag;
import com.bocweb.home.ui.bean.MainSelectedItem;
import com.bocweb.home.ui.bean.TargetInfo;
import com.bocweb.home.ui.bean.UserInfo;
import com.bocweb.home.ui.util.TopSmoothScroller;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.njh.common.core.ReqTag;
import com.njh.common.core.RouterHub;
import com.njh.common.flux.base.BaseFluxFragment;
import com.njh.common.flux.stores.Store;
import com.njh.common.utils.LogUtil;
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
@Route(path = RouterHub.Home.HOME_SELECTED)
public class MainSelectedFragment extends BaseFluxFragment<MainStore, MainAction>
        implements OnRefreshLoadMoreListener, JustNowZeroAdapter.OnStatusListener
        , JustNowOneAdapter.OnStatusListener, JustNowTwoAdapter.OnStatusListener
        , JustNowMoreAdapter.OnStatusListener, JustNowFourAdapter.OnStatusListener {

    @BindView(R2.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R2.id.refresh_layout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R2.id.fab_top)
    FloatingActionButton fabTop;

    private SuperAdapter mSuperAdapter;
    private List<MainSelectedItem> mMainSelectedItemList = new ArrayList<>();
    private LinearLayoutManager llm;

    private int currentPage = 1;
    private int pageNum = 10;
    private int maxNum;

    @Override
    public void initData(Bundle savedInstanceState) {
        initData();
        initAdapter();
        initRequest();
    }

    private void initData() {
        fabTop.hide();
        llm = new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(llm);


    }

    private void initAdapter() {
        mSuperAdapter = new SuperAdapter(getContext(), mMainSelectedItemList);
        JustNowZeroAdapter zero = new JustNowZeroAdapter(getContext());
        JustNowOneAdapter one = new JustNowOneAdapter(getContext());
        JustNowTwoAdapter two = new JustNowTwoAdapter(getContext());
        JustNowFourAdapter four = new JustNowFourAdapter(getContext());
        JustNowMoreAdapter more = new JustNowMoreAdapter(getContext());

        mSuperAdapter.addDelegate(new JustNowInfoAdapter(getContext()));
        mSuperAdapter.addDelegate(new JustNowActivityAdapter(getContext()));
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
        showLoading();
        actionsCreator().getSelectedFlag(this, "10", currentPage + "", "");
    }


    @Override
    protected void updateView(Store.StoreChangeEvent event) {
        super.updateView(event);
        hideLoading();
        switch (event.url) {
            case ReqTag.REQ_TAG_GET_HOME_MOMENT_SELECTED_FLAG:
                MainSelectedFlag flag = (MainSelectedFlag) event.data;
                mMainSelectedItemList.clear();
                mMainSelectedItemList.addAll(flag.getList());
                mSuperAdapter.notifyDataSetChanged();
                maxNum = flag.getCount();
                break;
            case ReqTag.REQ_TAG_POST_HOME_MOMENT_FOLLOW:
                StatusResponse statusResponse = (StatusResponse) event.data;
                int status = statusResponse.getStatus();
                LogUtil.e("status:" + status);
                for (MainSelectedItem item : mMainSelectedItemList) {
                    TargetInfo targetInfo = item.getTargetInfo();
                    if (targetInfo == null) {
                        continue;
                    }
                    UserInfo userInfo = targetInfo.getUserInfo();
                    if (userInfo == null) {
                        continue;
                    }
                    if (followId.equals(targetInfo.getAccountId())) {
                        userInfo.setIsFollow(status);
                    }
                }
                mSuperAdapter.notifyDataSetChanged();
                break;
            case ReqTag.REQ_TAG_GET_HOME_ACTIVITY_ACTIVITY_LIST:
                StatusResponse zanResponse = (StatusResponse) event.data;
                int zanStatus = zanResponse.getStatus();
                LogUtil.e("zanStatus:" + zanStatus);
                for (MainSelectedItem item : mMainSelectedItemList) {
                    TargetInfo targetInfo = item.getTargetInfo();
                    if (targetInfo == null) {
                        continue;
                    }

                    if (followId.equals(targetInfo.getAccountId())) {
                        targetInfo.setIszan(zanStatus + "");
                    }
                }
                mSuperAdapter.notifyDataSetChanged();
                break;
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
        return R.layout.home_fragment_selected;
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        if (maxNum > currentPage * pageNum) {
            currentPage++;
            initRequest();
        }
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        currentPage = 0;
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
