package com.bocweb.home.ui.activity.friend;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bocweb.home.R;
import com.bocweb.home.R2;
import com.bocweb.home.ui.api.MainAction;
import com.bocweb.home.ui.api.MainStore;
import com.bocweb.home.ui.bean.CustomData;
import com.bocweb.home.ui.bean.Friend;
import com.njh.common.city.CityHelper;
import com.njh.common.core.ReqTag;
import com.njh.common.core.RouterHub;
import com.njh.common.flux.base.BaseFluxActivity;
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
 * @date 2019/4/14
 */
@Route(path = RouterHub.Home.ADD_FRIEND)
public class AddFriendActivity extends BaseFluxActivity<MainStore, MainAction>
        implements OnRefreshLoadMoreListener {

    @BindView(R2.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R2.id.refresh_layout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R2.id.et_search)
    EditText etSearch;

    private AddFriendAdapter mAdapter;
    private List<Friend> mList = new ArrayList<>();
    private int currentPage = 1;
    private int pageNum = 10;
    private int maxNum;
    private String keyword;

    @Override
    public void initData(Bundle savedInstanceState) {
        initData();
    }

    private void initData() {
        mAdapter = new AddFriendAdapter(mList);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(llm);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void initRequest() {
        keyword = etSearch.getText().toString().trim();
        showLoading();
        actionsCreator().getMomentMemberSearch(this, currentPage, keyword);
    }

    @Override
    protected void updateView(Store.StoreChangeEvent event) {
        super.updateView(event);
        hideLoading();
        if (event.url.equals(ReqTag.REQ_TAG_GET_HOME_MOMENT_MEMBER_SEARCH)) {
            CustomData<Friend> result = (CustomData<Friend>) event.data;
            mList.clear();
            mList.addAll(result.getList());
            mAdapter.notifyDataSetChanged();
            mAdapter.notifyDataSetChanged();
            maxNum = result.getCount();
        }
    }

    @Override
    public void setListener() {
        mRefreshLayout.setOnRefreshLoadMoreListener(this);
        etSearch.setOnClickListener(v -> etSearch.setCursorVisible(true));
        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    // 当按了搜索之后关闭软键盘
                    InputMethodManager manager = (InputMethodManager) etSearch.getContext()
                            .getSystemService(Context.INPUT_METHOD_SERVICE);
                    manager.hideSoftInputFromWindow(etSearch.getWindowToken(), 0);
                    initRequest();
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    protected boolean flux() {
        return true;
    }

    @Override
    public int getLayoutId() {
        return R.layout.home_activity_add_friend;
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        if (maxNum > currentPage * pageNum) {
            currentPage++;
            initRequest();
        }
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        currentPage = 0;
        initRequest();
    }
}
