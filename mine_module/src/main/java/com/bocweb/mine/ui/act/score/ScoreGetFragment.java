package com.bocweb.mine.ui.act.score;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bocweb.mine.R;
import com.bocweb.mine.R2;
import com.bocweb.mine.api.MineAction;
import com.bocweb.mine.api.MineStore;
import com.bocweb.mine.bean.ScoreDetail;
import com.njh.common.core.ReqTag;
import com.njh.common.core.RouterHub;
import com.njh.common.flux.base.BaseFluxFragment;
import com.njh.common.flux.stores.Store;
import com.njh.common.utils.LogUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

/**
 * @author libingjun
 * @version 积分收入明细
 * @date 2019/4/26
 */
@Route(path = RouterHub.Mine.SCORE_GET)
public class ScoreGetFragment extends BaseFluxFragment<MineStore, MineAction> {

    @BindView(R2.id.refresh_layout)
    SmartRefreshLayout mRefresh;
    @BindView(R2.id.recyclerView)
    RecyclerView recyclerView;

    private ScoreGetAdapter mAdapter;
    private List<ScoreDetail.ScoreDetailList> mList = new ArrayList<>();

    @Override
    public void initData(Bundle savedInstanceState) {
        mAdapter = new ScoreGetAdapter(mList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mAdapter);
        request();
    }

    private void request() {
        showLoading();
        actionsCreator().getScoreGetList(this, 1);
    }

    @Override
    protected void updateView(Store.StoreChangeEvent event) {
        super.updateView(event);
        hideLoading();
        switch (event.url) {
            case ReqTag.Mine.MINE_SCORE_GET:
                ScoreDetail bean1 = (ScoreDetail) event.data;
                ScoreDetail.ScoreDetailCount list = bean1.getList();
                if (list != null) {
                    List<ScoreDetail.ScoreDetailList> list1 = list.getList();
                    mList.addAll(list1);
                    mAdapter.notifyDataSetChanged();
                }
                break;
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.mine_fragment_score_get;
    }

    @Override
    protected boolean flux() {
        return true;
    }
}