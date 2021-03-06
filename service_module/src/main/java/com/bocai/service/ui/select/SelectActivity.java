package com.bocai.service.ui.select;

import android.os.Bundle;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bocai.service.R;
import com.bocai.service.R2;
import com.bocai.service.api.ServiceAction;
import com.bocai.service.api.ServiceStore;
import com.bocai.service.bean.Select;
import com.bocai.service.bean.SuperServiceBean;
import com.njh.common.constant.Constant;
import com.njh.common.core.ReqTag;
import com.njh.common.core.RouterHub;
import com.njh.common.flux.base.BaseFluxActivity;
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
 * @date 2019/4/23
 */
@Route(path = RouterHub.Service.SELECT)
public class SelectActivity extends BaseFluxActivity<ServiceStore, ServiceAction>
implements OnRefreshLoadMoreListener {

    @BindView(R2.id.tv_select)
    TextView tvSelect;
    @BindView(R2.id.et_search)
    TextView etSearch;
    @BindView(R2.id.refresh_layout)
    SmartRefreshLayout mRefresh;
    @BindView(R2.id.recyclerView)
    RecyclerView recyclerView;

    private SelectAdapter mAdapter;
    private List<Select> mList = new ArrayList<>();

    private String keyword;
    private String carId = "0";

    @Override
    public void initData(Bundle savedInstanceState) {
        LinearLayoutManager llm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(llm);
        mAdapter = new SelectAdapter(mList);
        recyclerView.setAdapter(mAdapter);

        request();
    }

    private void request() {
        mRefresh.autoRefresh();
        actionsCreator().getServiceParts(this, currentPage, keyword, carId);
    }

    @Override
    public void setListener() {
        tvSelect.setOnClickListener(v -> {

        });
    }

    @Override
    protected void updateView(Store.StoreChangeEvent event) {
        super.updateView(event);
        mRefresh.finishRefresh();
        mRefresh.finishLoadMore();
        switch (event.url) {
            case ReqTag.Service.SERVICE_PARTS:
                SuperServiceBean<Select> bean1 = (SuperServiceBean<Select>) event.data;
                List<Select> list = bean1.getList();
                if (list != null) {
                    mList.clear();
                    mList.addAll(list);
                    mAdapter.notifyDataSetChanged();
                }
                maxNum = bean1.getCount();
                break;
        }
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

    @Override
    public int getLayoutId() {
        return R.layout.service_activity_select;
    }

    @Override
    protected boolean flux() {
        return true;
    }
}
