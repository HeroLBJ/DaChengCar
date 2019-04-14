package com.bocweb.home.ui.fmt.main.info;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bocweb.home.R;
import com.bocweb.home.R2;
import com.bocweb.home.ui.api.MainAction;
import com.bocweb.home.ui.api.MainStore;
import com.bocweb.home.ui.bean.ActivityPreviewsList;
import com.bocweb.home.ui.bean.Previews;
import com.bocweb.home.ui.bean.PreviewsItem;
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
@Route(path = RouterHub.Home.HOME_INFO)
public class MainInfoFragment extends BaseFluxFragment<MainStore, MainAction>
        implements OnRefreshLoadMoreListener {

    @BindView(R2.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R2.id.refresh_layout)
    SmartRefreshLayout mRefreshLayout;

    private PreviewsRecyclerAdapter mAdapter;
    private List<Previews> mList = new ArrayList<>();

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
        actionsCreator().getPreviewsList(this, "1", "");
    }

    @Override
    protected void updateView(Store.StoreChangeEvent event) {
        super.updateView(event);
        hideLoading();
        if (event.url.equals(ReqTag.REQ_TAG_GET_HOME_ACTIVITY_PREVIEWS_LIST)) {
            ActivityPreviewsList item = (ActivityPreviewsList) event.data;

            PreviewsItem data = item.getData();
            if (data != null) {
                mList.clear();
                mList.addAll(item.getData().getList());
                mAdapter = new PreviewsRecyclerAdapter(getContext(), data.getList(), item.getFlag().getList());
                mRecyclerView.setAdapter(mAdapter);
            }
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.home_fragment_info;
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
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {

    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {

    }
}
