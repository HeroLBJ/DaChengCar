package com.bocweb.mine.ui.act.user;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.fastjson.JSONObject;
import com.allen.library.CircleImageView;
import com.bocai.select.photo.util.ImageSelector;
import com.bocweb.mine.R;
import com.bocweb.mine.R2;
import com.bocweb.mine.adapter.SuperAdapter;
import com.bocweb.mine.api.MineAction;
import com.bocweb.mine.api.MineStore;
import com.bocweb.mine.bean.SuperMineBean;
import com.bocweb.mine.bean.TargetInfo;
import com.njh.common.constant.Constant;
import com.njh.common.core.ReqTag;
import com.njh.common.core.RouterHub;
import com.njh.common.flux.base.BaseFluxActivity;
import com.njh.common.flux.stores.Store;
import com.njh.common.sp.user.UserHelper;
import com.njh.common.sp.user.UserInfo;
import com.njh.common.utils.img.GlideUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

/**
 * @author libingjun
 * @date 2019/4/11
 */
@Route(path = RouterHub.Mine.USER_CENTER)
public class UserCenterActivity extends BaseFluxActivity<MineStore, MineAction>
        implements OnRefreshLoadMoreListener, DynamicZeroAdapter.OnStatusListener,
        DynamicOneAdapter.OnStatusListener, DynamicTwoAdapter.OnStatusListener,
        DynamicMoreAdapter.OnStatusListener, DynamicFourAdapter.OnStatusListener  {

    @BindView(R2.id.civ_user_photo)
    CircleImageView civUserPhone;
    @BindView(R2.id.tv_follow)
    TextView tvFollow;
    @BindView(R2.id.tv_user_name)
    TextView tvUserName;
    @BindView(R2.id.iv_user_sex)
    ImageView ivUserSex;
    @BindView(R2.id.tv_user_desc)
    TextView tvUserDesc;
    @BindView(R2.id.tv_send_num)
    TextView tvSendNum;
    @BindView(R2.id.tv_follow_num)
    TextView tvFollowNum;
    @BindView(R2.id.tv_fans_num)
    TextView tvFansNum;
    @BindView(R2.id.refresh_layout)
    SmartRefreshLayout mRefresh;
    @BindView(R2.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R2.id.iv_user_bg)
    ImageView ivUserBg;

    private SuperAdapter mSuperAdapter;

    private List<TargetInfo> mMainSelectedItemList = new ArrayList<>();

    UserInfo userInfo;
    @Autowired
    boolean self;

    @Override
    public void initData(Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.WHITE);
        }

        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(llm);

        initAdapter();

        initUserInfo();
        requestMomentList();
    }

    private void initAdapter() {
        mSuperAdapter = new SuperAdapter(getContext(), mMainSelectedItemList);

        DynamicZeroAdapter zero = new DynamicZeroAdapter(getContext());
        DynamicOneAdapter one = new DynamicOneAdapter(getContext());
        DynamicTwoAdapter two = new DynamicTwoAdapter(getContext());
        DynamicFourAdapter four = new DynamicFourAdapter(getContext());
        DynamicMoreAdapter more = new DynamicMoreAdapter(getContext());

        mSuperAdapter.addDelegate(zero);
        mSuperAdapter.addDelegate(one);
        mSuperAdapter.addDelegate(two);
        mSuperAdapter.addDelegate(four);
        mSuperAdapter.addDelegate(more);

        recyclerView.setAdapter(mSuperAdapter);

        zero.setOnStatusListener(this);
        one.setOnStatusListener(this);
        two.setOnStatusListener(this);
        four.setOnStatusListener(this);
        more.setOnStatusListener(this);
    }

    private void requestMomentList() {
        showLoading();
        actionsCreator().getUserMomentList(this, currentPage);
    }

    private void initUserInfo() {
        userInfo = UserHelper.getUserInfo();

        tvUserName.setText(userInfo.getNickname());
        GlideUtils.getInstance().loadImg(this, userInfo.getAvatar(), civUserPhone);
        tvUserDesc.setText(userInfo.getSightml());
        tvFollowNum.setText(userInfo.getFocus());
        tvFansNum.setText(userInfo.getFans());

        if (self) {
            tvFollow.setText("编辑");
        } else {
            // TODO 关注或已关注
        }
    }

    public void onBack(View view) {
        finish();
    }

    private ArrayList<String> mImages = new ArrayList<>();

    public void onCamera(View view) {
        ImageSelector.builder()
                .useCamera(true)
                .setSingle(true)
                .setViewImage(true)
                .setSelected(mImages)
                .start(this, 0);
    }

    public void onFollow(View view) {
        if (self) {
            // 进入个人中心编辑页面
            ARouter.getInstance().build(RouterHub.Mine.USER_EDIT)
                    .withSerializable("UserInfo", userInfo)
                    .navigation();
        } else {
            // TODO 关注或已关注
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        }
        if (requestCode == 0) {
            mImages = data.getStringArrayListExtra(ImageSelector.SELECT_RESULT);
            compress();
        }
    }

    private void compress() {
        Luban.with(this)
                .load(mImages)
                .ignoreBy(100)
                .setCompressListener(new OnCompressListener() {
                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onSuccess(File file) {
                        actionsCreator().getPhoto(UserCenterActivity.this, file);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                }).launch();
    }

    @Override
    protected void updateView(Store.StoreChangeEvent event) {
        super.updateView(event);
        hideLoading();
        mRefresh.finishRefresh();
        mRefresh.finishLoadMore();
        switch (event.url) {
            case ReqTag.Mine.MINE_PHOTO:
                JSONObject obj = (JSONObject) event.data;
                String img = (String) obj.get("photoUrl");
                GlideUtils.getInstance().loadImg(this, img, ivUserBg);
                showLoading();
                actionsCreator().uploadUserCenterBg(this, img);
                break;
            case ReqTag.Mine.MINE_USER_CENTER_BG:
                // TODO 背景图片被更新了
                break;
            case ReqTag.Mine.MINE_USER_MOMENT_LIST:
                SuperMineBean<TargetInfo> bean3 = (SuperMineBean<TargetInfo>) event.data;
                maxNum = bean3.getCount();

                mMainSelectedItemList.clear();
                mMainSelectedItemList.addAll(bean3.getList());
                mSuperAdapter.notifyDataSetChanged();
                break;
        }
    }

    @Override
    public void setListener() {
        mRefresh.setOnRefreshLoadMoreListener(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.mine_activity_user_center;
    }

    @Override
    protected boolean flux() {
        return true;
    }

    private int currentPage = Constant.Num.NUM_1;
    private int maxNum;

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        if (maxNum > currentPage * Constant.Num.NUM_10) {
            currentPage++;
            requestMomentList();
        } else {
            mRefresh.finishLoadMore();
        }
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        mRefresh.finishRefresh();
    }

    @Override
    public void onStatusClick(String id) {

    }

    @Override
    public void onZanClick(String id) {

    }
}
