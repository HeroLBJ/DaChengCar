package com.bocweb.home.ui.fmt;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bocweb.home.R;
import com.bocweb.home.R2;
import com.bocweb.home.ui.adapter.MainSelectedActivityRecyclerAdapter;
import com.bocweb.home.ui.adapter.MainSelectedInfoRecyclerAdapter;
import com.bocweb.home.ui.adapter.MoreJustNowAdapter;
import com.bocweb.home.ui.adapter.OneJustNowAdapter;
import com.bocweb.home.ui.adapter.SuperAdapter;
import com.bocweb.home.ui.adapter.TwoJustNowAdapter;
import com.bocweb.home.ui.bean.MainSelectedFlag;
import com.bocweb.home.ui.bean.MainSelectedItem;
import com.bocweb.home.ui.fmt.actions.MainSelectedAction;
import com.bocweb.home.ui.fmt.stores.MainSelectedStore;
import com.njh.common.core.ReqTag;
import com.njh.common.core.RouterHub;
import com.njh.common.flux.base.BaseFluxFragment;
import com.njh.common.flux.stores.Store;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

/**
 * @author libingjun
 * @date 2019/4/8
 */
@Route(path = RouterHub.HOME_SELECTED)
public class MainSelectedFragment extends BaseFluxFragment<MainSelectedStore, MainSelectedAction>
        implements OnRefreshLoadMoreListener {

    @BindView(R2.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R2.id.refresh_layout)
    SmartRefreshLayout mRefreshLayout;

    private SuperAdapter mSuperAdapter;
    private List<MainSelectedItem> mMainSelectedItemList = new ArrayList<>();

    @Override
    public void initData(Bundle savedInstanceState) {
        initData();
        initRequest();
    }

    private void initData() {
        LinearLayoutManager llm = new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(llm);
    }

    private void initRequest() {
        showLoading();
        actionsCreator().getSelectedFlag(this, "10", "1", "");
    }

    @Override
    protected void updateView(Store.StoreChangeEvent event) {
        super.updateView(event);
        hideLoading();
        if (event.url.equals(ReqTag.REQ_TAG_GET_HOME_MOMENT_SELECTED_FLAG)) {
            MainSelectedFlag flag = (MainSelectedFlag) event.data;
            mMainSelectedItemList.clear();
            mMainSelectedItemList.addAll(flag.getList());
            mSuperAdapter = new SuperAdapter(getContext(), mMainSelectedItemList);
            mSuperAdapter.addDelegate(new MainSelectedInfoRecyclerAdapter(getContext()));
            mSuperAdapter.addDelegate(new MainSelectedActivityRecyclerAdapter(getContext()));
            mSuperAdapter.addDelegate(new OneJustNowAdapter(getContext()));
            mSuperAdapter.addDelegate(new TwoJustNowAdapter(getContext()));
            mSuperAdapter.addDelegate(new MoreJustNowAdapter(getContext()));
            mRecyclerView.setAdapter(mSuperAdapter);
        }
    }

    @Override
    public void setListener() {
        mRefreshLayout.setOnRefreshLoadMoreListener(this);
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

    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {

    }
}
