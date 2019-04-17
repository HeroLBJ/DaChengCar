package com.bocweb.home.ui.fmt.main.dynamic;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bocweb.home.R;
import com.bocweb.home.R2;
import com.bocweb.home.ui.adapter.SuperAdapter;
import com.bocweb.home.ui.api.MainAction;
import com.bocweb.home.ui.api.MainStore;
import com.bocweb.home.ui.bean.MainComentList;
import com.bocweb.home.ui.bean.TargetInfo;
import com.njh.common.core.ReqTag;
import com.njh.common.core.RouterHub;
import com.njh.common.flux.base.BaseFluxFragment;
import com.njh.common.flux.stores.Store;
import com.njh.common.utils.LogUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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
@Route(path = RouterHub.Home.HOME_DYNAMIC)
public class MainDynamicFragment extends BaseFluxFragment<MainStore, MainAction>
        implements OnRefreshLoadMoreListener {

    @BindView(R2.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R2.id.refresh_layout)
    SmartRefreshLayout mRefreshLayout;

    private SuperAdapter mSuperAdapter;
    private List<TargetInfo> mMainSelectedItemList = new ArrayList<>();

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
        actionsCreator().getMomentList(this, "1");
    }

    @Override
    protected void updateView(Store.StoreChangeEvent event) {
        super.updateView(event);
        hideLoading();
        if (event.url.equals(ReqTag.REQ_TAG_GET_HOME_MOMENT_LIST)) {
            MainComentList item = (MainComentList) event.data;
            mMainSelectedItemList.clear();
            mMainSelectedItemList.addAll(item.getList());
            mSuperAdapter = new SuperAdapter(getContext(), mMainSelectedItemList);
            mSuperAdapter.addDelegate(new DynamicZeroAdapter(getContext()));
            mSuperAdapter.addDelegate(new DynamicOneAdapter(getContext()));
            mSuperAdapter.addDelegate(new DynamicTwoAdapter(getContext()));
            mSuperAdapter.addDelegate(new DynamicMoreAdapter(getContext()));
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
        return R.layout.home_fragment_dynamic;
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {

    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {

    }

//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void onEvent(Integer num) {
//        //处理逻辑
//        LogUtil.e("num:"+num);
//    }
}