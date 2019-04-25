package com.bocai.service.ui.feedback;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bocai.service.R;
import com.bocai.service.R2;
import com.bocai.service.api.ServiceAction;
import com.bocai.service.api.ServiceStore;
import com.bocai.service.bean.FeedbackType;
import com.bocai.service.bean.SuperServiceBean;
import com.njh.common.core.ReqTag;
import com.njh.common.core.RouterHub;
import com.njh.common.flux.base.BaseFluxActivity;
import com.njh.common.flux.stores.Store;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

/**
 * @author libingjun
 * @version 投诉建议列表
 * @date 2019/4/25
 */
@Route(path = RouterHub.Service.FEEDBACK_TYPE)
public class FeedbackTypeActivity extends BaseFluxActivity<ServiceStore, ServiceAction> {

    @BindView(R2.id.recyclerView)
    RecyclerView recyclerView;

    private FeedBackFatherAdapter mAdapter;
    private List<FeedbackType> mList = new ArrayList<>();

    @Override
    public void initData(Bundle savedInstanceState) {
        LinearLayoutManager llm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(llm);
        mAdapter = new FeedBackFatherAdapter(mList);
        recyclerView.setAdapter(mAdapter);

        request();
    }

    private void request() {
        showLoading();
        actionsCreator().getFeedbackType(this);
    }

    @Override
    protected void updateView(Store.StoreChangeEvent event) {
        super.updateView(event);
        hideLoading();
        switch (event.url) {
            case ReqTag.Service.SERVICE_FEEDBACK_TYPE:
                SuperServiceBean<FeedbackType> bean1 = (SuperServiceBean<FeedbackType>) event.data;
                List<FeedbackType> feedbackTypeList = bean1.getList();
                mList.clear();
                mList.addAll(feedbackTypeList);
                mAdapter.notifyDataSetChanged();
                break;
        }
    }

    @Override
    public void setListener() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.service_activity_feedback_type;
    }

    @Override
    protected boolean flux() {
        return true;
    }
}