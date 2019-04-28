package com.bocweb.mine.ui.act.user.upload;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.fastjson.JSONObject;
import com.allen.library.CircleImageView;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.bocai.select.photo.util.ImageSelector;
import com.bocweb.mine.R;
import com.bocweb.mine.R2;
import com.bocweb.mine.api.MineAction;
import com.bocweb.mine.api.MineStore;
import com.njh.common.core.ReqTag;
import com.njh.common.core.RouterHub;
import com.njh.common.flux.base.BaseFluxActivity;
import com.njh.common.flux.stores.Store;
import com.njh.common.picker.city.CityHelper;
import com.njh.common.sp.user.UserHelper;
import com.njh.common.sp.user.UserInfo;
import com.njh.common.utils.LogUtil;
import com.njh.common.utils.img.GlideUtils;
import com.njh.common.utils.time.TimeUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;

import androidx.core.content.ContextCompat;
import butterknife.BindView;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

/**
 * @author libingjun
 * @version 1.0
 * @date 2019/4/28
 */
@Route(path = RouterHub.Mine.USER_EDIT)
public class UserInfoEditActivity extends BaseFluxActivity<MineStore, MineAction>
        implements CityHelper.OnSelectCityListener {

    @BindView(R2.id.toolbar_back)
    ImageButton toolbarBack;
    @BindView(R2.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R2.id.toolbar_divider)
    View toolbarDivider;
    @BindView(R2.id.civ_user_photo)
    CircleImageView civUserPhoto;
    @BindView(R2.id.et_user_nickname)
    EditText etUserNickname;
    @BindView(R2.id.et_user_name)
    EditText etUserName;
    @BindView(R2.id.ll_man)
    LinearLayout llMan;
    @BindView(R2.id.ll_woman)
    LinearLayout llWoman;
    @BindView(R2.id.tv_man)
    TextView tvMan;
    @BindView(R2.id.tv_woman)
    TextView tvWoman;
    @BindView(R2.id.rl_select_city)
    RelativeLayout rlSelectCity;
    @BindView(R2.id.tv_time)
    TextView tvTime;
    @BindView(R2.id.tv_city)
    TextView tvCity;
    @BindView(R2.id.tv_sign)
    TextView tvSign;

    private UserInfo userInfo;

    private ArrayList<String> mImages = new ArrayList<>();

    @Override
    public void initData(Bundle savedInstanceState) {
        initTitle();
        initUserInfo();
    }

    private void initTitle() {
        toolbarTitle.setText("个人信息");
        toolbarBack.setOnClickListener(v -> finish());
        toolbarDivider.setVisibility(View.VISIBLE);
    }

    private void initUserInfo() {
        userInfo = UserHelper.getUserInfo();

        GlideUtils.getInstance().loadImg(this, userInfo.getAvatar(), civUserPhoto);
        etUserNickname.setText(userInfo.getNickname());
        etUserName.setText(userInfo.getName());
        if ("2".equals(userInfo.getGender())) {
            tvWoman.setTextColor(ContextCompat.getColor(this, R.color.white));
            tvMan.setTextColor(ContextCompat.getColor(this, R.color.res_gray_888));
            llWoman.setBackgroundResource(R.drawable.mine_follow_color_radius_17dp);
            llMan.setBackgroundColor(ContextCompat.getColor(this, R.color.white));
        } else {
            tvMan.setTextColor(ContextCompat.getColor(this, R.color.white));
            tvWoman.setTextColor(ContextCompat.getColor(this, R.color.res_gray_888));
            llMan.setBackgroundResource(R.drawable.mine_follow_color_radius_17dp);
            tvWoman.setBackgroundColor(ContextCompat.getColor(this, R.color.white));
        }
        if (!TextUtils.isEmpty(userInfo.getBirthYear())
                && !TextUtils.isEmpty(userInfo.getBirthMonth())
                && !TextUtils.isEmpty(userInfo.getBirthDay())
                && !"0".equals(userInfo.getBirthYear())
                && !"0".equals(userInfo.getBirthMonth())
                && !"0".equals(userInfo.getBirthDay())) {
            tvTime.setText(userInfo.getBirthYear() + "-" + userInfo.getBirthMonth() + "-" + userInfo.getBirthDay());
        }
        if (!TextUtils.isEmpty(userInfo.getProvinceName())
                && !TextUtils.isEmpty(userInfo.getCityName())) {
            tvCity.setText(userInfo.getProvinceName() + "  " + userInfo.getCityName());
        }

        if (TextUtils.isEmpty(userInfo.getSightml())) {
            tvSign.setText("这家伙很懒，什么都没留下！");
        } else {
            tvSign.setText(userInfo.getSightml());
        }

        nickname = userInfo.getNickname();
        name = userInfo.getName();
        gender = userInfo.getGender();
        year = userInfo.getBirthYear();
        month = userInfo.getBirthMonth();
        day = userInfo.getBirthDay();
        provinceName = userInfo.getProvinceName();
        cityName = userInfo.getCityName();
        sightml = userInfo.getSightml();
    }

    @Override
    public void setListener() {
        civUserPhoto.setOnClickListener(v -> {
            ImageSelector.builder()
                    .useCamera(true)
                    .setSingle(true)
                    .setViewImage(true)
                    .setSelected(mImages)
                    .start(this, 0);
        });

        CityHelper.getInstance().setOnSelectCityListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && data != null) {
            mImages = data.getStringArrayListExtra(ImageSelector.SELECT_RESULT);
            compress();
        } else if (requestCode == 1) {
            sightml = data.getStringExtra("sightml");
            tvSign.setText(sightml);
        }
    }

    @Override
    protected void updateView(Store.StoreChangeEvent event) {
        super.updateView(event);
        hideLoading();
        switch (event.url) {
            case ReqTag.Mine.MINE_PHOTO:
                JSONObject obj = (JSONObject) event.data;
                GlideUtils.getInstance().loadImg(UserInfoEditActivity.this, (String) obj.get("photoUrl"), civUserPhoto);
                // TODO 更新用户头像接口
                break;
            case ReqTag.Mine.MINE_UPLOAD_USER:
                String sendIntegral = (String) event.data;
                // TODO 获取积分
                LogUtil.e("sendIntegral : " + sendIntegral);
                break;
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
                        actionsCreator().getPhoto(UserInfoEditActivity.this, file);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                }).launch();
    }

    public void toSign(View view) {
        ARouter.getInstance().build(RouterHub.Mine.SIGN).navigation(this, 1);
    }

    public void onMan(View view) {
        tvMan.setTextColor(ContextCompat.getColor(this, R.color.white));
        tvWoman.setTextColor(ContextCompat.getColor(this, R.color.res_gray_888));
        llWoman.setBackgroundResource(R.drawable.mine_write_radius_17dp_no_stoken);
        llMan.setBackgroundResource(R.drawable.mine_follow_color_radius_17dp);
    }

    public void onWoman(View view) {
        tvWoman.setTextColor(ContextCompat.getColor(this, R.color.white));
        tvMan.setTextColor(ContextCompat.getColor(this, R.color.res_gray_888));
        llMan.setBackgroundResource(R.drawable.mine_write_radius_17dp_no_stoken);
        llWoman.setBackgroundResource(R.drawable.mine_follow_color_radius_17dp);
    }

    public void onSelectTime(View view) {
        TimePickerView pvTime = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {

                year = TimeUtil.getYear(date) + "";
                month = TimeUtil.getMonth(date) + "";
                day = TimeUtil.getDay(date) + "";
                tvTime.setText(year + "-" + month + "-" + day);
            }
        })
                .setType(new boolean[]{true, true, true, false, false, false})
                .build();
        pvTime.show();
    }

    public void onSelectCity(View view) {
        CityHelper.getInstance().initCity(this);
    }

    private String nickname, name, year, month, day, gender, provinceName, cityName, sightml;

    public void onSubmit(View view) {
        nickname = etUserNickname.getText().toString().trim();
        name = etUserName.getText().toString().trim();

        showLoading();
        actionsCreator().uploadUser(this, nickname, name, gender, year, month, day, provinceName, cityName, sightml);
    }

    @Override
    public int getLayoutId() {
        return R.layout.mine_activity_user_info_edit;
    }

    @Override
    protected boolean flux() {
        return true;
    }

    @Override
    public void onSelectCityClick(String province, String city, String area) {
        tvCity.setText(province + "  " + city);
    }
}
