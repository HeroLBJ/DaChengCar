package com.bocweb.mine.ui.act.score;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bocweb.mine.R;
import com.bocweb.mine.R2;
import com.bocweb.mine.api.MineAction;
import com.bocweb.mine.api.MineStore;
import com.bocweb.mine.bean.ScoreDetail;
import com.njh.common.constant.Constant;
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
 * @version 积分支出明细
 * @date 2019/4/26
 */
@Route(path = RouterHub.Mine.SCORE_PAY)
public class ScorePayFragment extends BaseFluxFragment<MineStore, MineAction>
        implements OnRefreshLoadMoreListener {

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
        mRefresh.autoRefresh();
        actionsCreator().getScorePayList(this, currentPage);
    }

    @Override
    protected void updateView(Store.StoreChangeEvent event) {
        super.updateView(event);
        hideLoading();
        mRefresh.finishRefresh();
        mRefresh.finishLoadMore();
        switch (event.url) {
            case ReqTag.Mine.MINE_SCORE_PAY:
                ScoreDetail bean1 = (ScoreDetail) event.data;
                ScoreDetail.ScoreDetailCount list = bean1.getList();
                if (list != null) {
                    List<ScoreDetail.ScoreDetailList> list1 = list.getList();
                    mList.addAll(list1);
                    mAdapter.notifyDataSetChanged();
                    maxNum = list.getCount();
                }
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
        return R.layout.mine_fragment_score_pay;
    }

    @Override
    protected boolean flux() {
        return true;
    }
}
