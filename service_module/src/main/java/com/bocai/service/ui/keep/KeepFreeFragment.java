package com.bocai.service.ui.keep;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bocai.service.R;
import com.bocai.service.R2;
import com.bocai.service.api.ServiceAction;
import com.bocai.service.api.ServiceStore;
import com.bocai.service.bean.ServicePackage;
import com.bocai.service.bean.SuperServiceBean;
import com.njh.common.constant.Constant;
import com.njh.common.core.ReqTag;
import com.njh.common.core.RouterHub;
import com.njh.common.flux.base.BaseFluxFragment;
import com.njh.common.flux.stores.Store;
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
 * @date 2019/4/22
 */
@Route(path = RouterHub.Service.KEEP_FREE)
public class KeepFreeFragment extends BaseFluxFragment<ServiceStore, ServiceAction>
        implements OnRefreshLoadMoreListener, FreeAdapter.OnSelectOneListener {

    @BindView(R2.id.refresh_layout)
    SmartRefreshLayout mRefresh;
    @BindView(R2.id.recyclerView)
    RecyclerView recyclerView;

    private List<ServicePackage> mList = new ArrayList<>();
    private FreeAdapter mAdapter;

    @Override
    public void initData(Bundle savedInstanceState) {
        mAdapter = new FreeAdapter(mList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mAdapter);
        request();
    }

    @Override
    public int getLayoutId() {
        return R.layout.service_fragment_keep_free;
    }

    private void request() {
        mRefresh.autoRefresh();
        actionsCreator().getServicePackage(this, currentPage, "1");
    }

    @Override
    protected void updateView(Store.StoreChangeEvent event) {
        super.updateView(event);
        hideLoading();
        mRefresh.finishLoadMore();
        mRefresh.finishRefresh();
        switch (event.url) {
            case ReqTag.Service.SERVICE_PACKAGE:
                SuperServiceBean<ServicePackage> bean1 = (SuperServiceBean<ServicePackage>) event.data;
                List<ServicePackage> list = bean1.getList();
                if (currentPage == 1) {
                    mList.clear();
                }
                mList.addAll(list);
                mAdapter.notifyDataSetChanged();
                maxNum = bean1.getCount();
                break;
        }
    }

    @Override
    protected boolean flux() {
        return true;
    }

    @Override
    public void setListener() {
        mAdapter.setOnSelectOneListener(this);
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

    private int selectOneCount = 0;
    private int selectMoreCount = 0;

    @Override
    public void onSelectOneClick(ServicePackage item, boolean select, boolean isOne) {
        if (!isOne) {
            return;
        }
        for (ServicePackage pack : mList) {
            if (item.getId().equals(pack.getId())) {
                pack.setSelect(!select);
            } else {
                pack.setSelect(false);
            }
        }
        mAdapter.notifyDataSetChanged();

        if (select) {
            selectOneCount = 0;
        } else {
            selectOneCount = 1;
        }

        LogUtil.e("selectCount = " + selectOneCount);

        if (onKeepFreeListener != null) {
            LogUtil.e("=========================");
            onKeepFreeListener.onKeepFreeClick(item, select);
        }
    }

    private OnKeepFreeListener onKeepFreeListener;

    public void setOnKeepFreeListener(OnKeepFreeListener onKeepFreeListener) {
        this.onKeepFreeListener = onKeepFreeListener;
    }

    public interface OnKeepFreeListener {
        void onKeepFreeClick(ServicePackage item, boolean select);
    }
}
