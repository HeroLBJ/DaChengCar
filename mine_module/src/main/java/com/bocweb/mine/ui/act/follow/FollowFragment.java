package com.bocweb.mine.ui.act.follow;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bocweb.mine.R;
import com.bocweb.mine.R2;
import com.bocweb.mine.api.MineAction;
import com.bocweb.mine.api.MineStore;
import com.njh.common.constant.Constant;
import com.njh.common.core.RouterHub;
import com.njh.common.flux.base.BaseFluxFragment;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

/**
 * @author libingjun
 * @version 我的关注
 * @date 2019/4/29
 */
@Route(path = RouterHub.Mine.FOLLOW_FOLLOW)
public class FollowFragment extends BaseFluxFragment<MineStore, MineAction>
        implements OnRefreshLoadMoreListener {

    @BindView(R2.id.refresh_layout)
    SmartRefreshLayout mRefresh;
    @BindView(R2.id.recyclerView)
    RecyclerView recyclerView;

    @Override
    public void initData(Bundle savedInstanceState) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void request() {

    }

    @Override
    public void setListener() {
        mRefresh.setOnRefreshLoadMoreListener(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.mine_fragment_follow_and_fans;
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
    protected boolean flux() {
        return true;
    }
}
