package com.bocweb.home.ui.fmt.main.selected;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bocweb.home.R;
import com.bocweb.home.R2;
import com.bocweb.home.ui.adapter.SuperAdapter;
import com.bocweb.home.ui.api.MainAction;
import com.bocweb.home.ui.api.MainStore;
import com.bocweb.home.ui.bean.MainSelectedFlag;
import com.bocweb.home.ui.bean.MainSelectedItem;
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
@Route(path = RouterHub.Home.HOME_SELECTED)
public class MainSelectedFragment extends BaseFluxFragment<MainStore, MainAction>
        implements OnRefreshLoadMoreListener {

    @BindView(R2.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R2.id.refresh_layout)
    SmartRefreshLayout mRefreshLayout;

    private SuperAdapter mSuperAdapter;
    private List<MainSelectedItem> mMainSelectedItemList = new ArrayList<>();

    private int currentPage = 1;
    private int pageNum = 10;
    private int maxNum;

    @Override
    public void initData(Bundle savedInstanceState) {
        initData();
        initRequest();
    }

    private void initData() {
        LinearLayoutManager llm = new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(llm);

        mSuperAdapter = new SuperAdapter(getContext(), mMainSelectedItemList);
        mSuperAdapter.addDelegate(new JustNowInfoAdapter(getContext()));
        mSuperAdapter.addDelegate(new JustNowActivityAdapter(getContext()));
        mSuperAdapter.addDelegate(new JustNowOneAdapter(getContext()));
        mSuperAdapter.addDelegate(new JustNowTwoAdapter(getContext()));
        mSuperAdapter.addDelegate(new JustNowMoreAdapter(getContext()));
        mSuperAdapter.addDelegate(new JustNowZeroAdapter(getContext()));
        mRecyclerView.setAdapter(mSuperAdapter);
    }

    private void initRequest() {
        showLoading();
        actionsCreator().getSelectedFlag(this, "10",currentPage+"", "");
    }

    @Override
    protected void updateView(Store.StoreChangeEvent event) {
        super.updateView(event);
        hideLoading();
        if (event.url.equals(ReqTag.REQ_TAG_GET_HOME_MOMENT_SELECTED_FLAG)) {
            MainSelectedFlag flag = (MainSelectedFlag) event.data;
            mMainSelectedItemList.clear();
            mMainSelectedItemList.addAll(flag.getList());
            mSuperAdapter.notifyDataSetChanged();
            maxNum = flag.getCount();
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
}
