package com.bocweb.mine.ui.act.user;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.allen.library.CircleImageView;
import com.bocweb.mine.R;
import com.bocweb.mine.R2;
import com.njh.common.core.RouterHub;
import com.njh.common.flux.base.BaseFluxActivity;
import com.njh.common.sp.user.UserHelper;
import com.njh.common.sp.user.UserInfo;
import com.njh.common.utils.img.GlideUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

/**
 * @author libingjun
 * @date 2019/4/11
 */
@Route(path = RouterHub.Mine.USER_CENTER)
public class UserCenterActivity extends BaseFluxActivity {

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

    UserInfo userInfo;
    @Autowired
    boolean self;

    @Override
    public void initData(Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.WHITE);
        }
        initUserInfo();
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

    public void onCamera(View view) {

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
    public void setListener() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.mine_activity_user_center;
    }
}
