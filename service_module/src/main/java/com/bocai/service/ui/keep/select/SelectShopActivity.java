package com.bocai.service.ui.keep.select;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bocai.service.R;
import com.bocai.service.R2;
import com.bocai.service.api.ServiceAction;
import com.bocai.service.api.ServiceStore;
import com.bocai.service.bean.ServiceDealers;
import com.bocai.service.bean.SuperServiceBean;
import com.njh.common.constant.Constant;
import com.njh.common.core.ReqTag;
import com.njh.common.core.RouterHub;
import com.njh.common.flux.base.BaseFluxActivity;
import com.njh.common.flux.stores.Store;
import com.njh.common.simple.SimpleRecyclerItemListener;
import com.njh.common.utils.LogUtil;
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
 * @date 2019/4/23
 */
@Route(path = RouterHub.Service.SELECT_SHOP)
public class SelectShopActivity extends BaseFluxActivity<ServiceStore, ServiceAction>
        implements SelectShopAdapter.OnSelectListener, OnRefreshLoadMoreListener {

    @BindView(R2.id.et_search)
    EditText etSearch;
    @BindView(R2.id.refresh_layout)
    SmartRefreshLayout mRefresh;
    @BindView(R2.id.recyclerView)
    RecyclerView recyclerView;

    private SelectShopAdapter mAdapter;
    private List<ServiceDealers> mList = new ArrayList<>();
    private double mCurrentLon, mCurrentLat;
    private String cityName, keyword;

    @Override
    public void initData(Bundle savedInstanceState) {
        LinearLayoutManager llm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(llm);
        mAdapter = new SelectShopAdapter(mList);
        recyclerView.setAdapter(mAdapter);

        request();
    }

    private void request() {
        mRefresh.autoRefresh();
        actionsCreator().getServiceDealers(this, currentPage, mCurrentLon, mCurrentLat, cityName, keyword);
    }

    @Override
    protected void updateView(Store.StoreChangeEvent event) {
        super.updateView(event);
        mRefresh.finishRefresh();
        mRefresh.finishLoadMore();
        switch (event.url) {
            case ReqTag.Service.SERVICE_DEALERS:
                SuperServiceBean<ServiceDealers> bean1 = (SuperServiceBean<ServiceDealers>) event.data;
                List<ServiceDealers> serviceDealersList = bean1.getList();

                mList.clear();
                mList.addAll(serviceDealersList);
                mAdapter.notifyDataSetChanged();
                maxNum = bean1.getCount();
                break;
        }
    }

    @Override
    public void setListener() {
        mAdapter.setOnSelectListener(this);
        mRefresh.setOnRefreshLoadMoreListener(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.service_activity_select_shop;
    }

    @Override
    protected boolean flux() {
        return true;
    }

    @Override
    public void onSelectClick(String id, String title) {
        Intent intent = new Intent();
        intent.putExtra("serviceId", id);
        intent.putExtra("serviceName", title);
        setResult(RESULT_OK, intent);
        finish();
    }

    private int currentPage = Constant.Num.NUM_1;
    private int maxNum;

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        if (maxNum > currentPage * Constant.Num.NUM_10) {
            currentPage++;
            request();
        } else {
            mRefresh.finishLoadMore();
        }
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        currentPage = Constant.Num.NUM_1;
        request();
    }
}
