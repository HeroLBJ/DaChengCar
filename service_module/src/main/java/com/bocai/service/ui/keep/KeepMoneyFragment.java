package com.bocai.service.ui.keep;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bocai.service.R;
import com.bocai.service.R2;
import com.bocai.service.api.ServiceAction;
import com.bocai.service.api.ServiceStore;
import com.bocai.service.bean.ServicePackage;
import com.bocai.service.bean.SuperServiceBean;
import com.njh.common.core.ReqTag;
import com.njh.common.core.RouterHub;
import com.njh.common.flux.base.BaseFluxFragment;
import com.njh.common.flux.stores.Store;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

/**
 * @author libingjun
 * @date 2019/4/22
 */
@Route(path = RouterHub.Service.KEEP_MONEY)
public class KeepMoneyFragment extends BaseFluxFragment<ServiceStore, ServiceAction> {

    @BindView(R2.id.refresh_layout)
    SmartRefreshLayout mRefresh;
    @BindView(R2.id.recyclerView)
    RecyclerView recyclerView;

    private List<ServicePackage> mList = new ArrayList<>();
    private FreeAdapter mAdapter;

    @Override
    public void initData(Bundle savedInstanceState) {
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(mAdapter);
        request();
    }

    @Override
    public int getLayoutId() {
        return R.layout.service_fragment_keep_free;
    }

    private void request() {
        actionsCreator().getServicePackage(this, 0, "2");
    }

    @Override
    protected void updateView(Store.StoreChangeEvent event) {
        super.updateView(event);
        switch (event.url) {
            case ReqTag.Service.SERVICE_PACKAGE:
                SuperServiceBean<ServicePackage> bean1 = (SuperServiceBean<ServicePackage>) event.data;
                List<ServicePackage> list = bean1.getList();
                mList.clear();
                mList.addAll(list);
                break;
        }
    }

    @Override
    protected boolean flux() {
        return true;
    }
}
