package com.bocai.service.ui.keep.detail;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.bocai.service.R;
import com.bocai.service.R2;
import com.bocai.service.api.ServiceAction;
import com.bocai.service.api.ServiceStore;
import com.njh.common.picker.city.CityHelper;
import com.njh.common.core.RouterHub;
import com.njh.common.flux.base.BaseFluxActivity;
import com.njh.common.utils.LogUtil;
import com.njh.common.utils.time.TimeUtil;
import com.njh.common.widget.CustomPopWindow;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import androidx.annotation.Nullable;
import butterknife.BindView;

/**
 * @author libingjun
 * @date 2019/4/22
 */
@Route(path = RouterHub.Service.KEEP_DETAIL)
public class KeepDetailActivity extends BaseFluxActivity<ServiceStore, ServiceAction>
        implements CityHelper.OnSelectCityListener {

    @BindView(R2.id.rl_city)
    RelativeLayout rlCity;
    @BindView(R2.id.rl_shop)
    RelativeLayout rlShop;
    @BindView(R2.id.rl_date)
    RelativeLayout rlDate;
    @BindView(R2.id.rl_time)
    RelativeLayout rlTime;
    @BindView(R2.id.tv_city)
    TextView tvCity;
    @BindView(R2.id.tv_shop)
    TextView tvShop;
    @BindView(R2.id.tv_date)
    TextView tvDate;
    @BindView(R2.id.tv_time)
    TextView tvTime;
    @BindView(R2.id.et_username)
    EditText etUsername;
    @BindView(R2.id.et_phone)
    EditText etPhone;
    @BindView(R2.id.tv_submit)
    TextView tvSubmit;

    private CustomPopWindow popWindow;

    private String mPhone, mServiceId, mRealname, mCityName, mTimeline, mStartTime, mEndTime, mPackageId;

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public void setListener() {
        tvSubmit.setOnClickListener(v -> commitKeepOrder());
        rlCity.setOnClickListener(v -> selectCity());
        rlShop.setOnClickListener(v -> selectShop());
        rlDate.setOnClickListener(v -> selectDate());
        rlTime.setOnClickListener(v -> selectTime());
        CityHelper.getInstance().setOnSelectCityListener(this);
    }

    private void selectShop() {
        ARouter.getInstance().build(RouterHub.Service.SELECT_SHOP).navigation(this, 1);
    }

    private void selectCity() {
        CityHelper.getInstance().initCity(this);
    }

    private void selectDate() {
        // https://github.com/Bigkoo/Android-PickerView
        TimePickerView pvTime = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                String timeStr = TimeUtil.parseDateToStr(date, TimeUtil.PATTERN_DATA);
                LogUtil.e("timeStr = " + timeStr);
                tvDate.setText(timeStr);
                mTimeline = TimeUtil.dateToStamp(timeStr, TimeUtil.PATTERN_DATA);
                LogUtil.e("mTimeline = " + mTimeline);
            }
        })
                .setType(new boolean[]{true, true, true, false, false, false})
                .build();
        pvTime.show();
    }

    private void selectTime() {
        List<String> items = new ArrayList<>();
        items.add("上午");
        items.add("下午");
        //条件选择器
        OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String time = items.get(options1);
                tvTime.setText(time);
                mStartTime = time;
                mEndTime = time;
            }
        }).build();
        pvOptions.setPicker(items);
        pvOptions.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        }
        if (requestCode == 1) {
            mServiceId = data.getStringExtra("serviceId");
            String serviceName = data.getStringExtra("serviceName");
            tvShop.setText(serviceName);
        }
    }

    private void commitKeepOrder() {
        if (TextUtils.isEmpty(mPackageId)) {
            toast("保养订单有误");
            return;
        }
        if (TextUtils.isEmpty(mCityName)) {
            toast("请选择城市");
            return;
        }
        if (TextUtils.isEmpty(mServiceId)) {
            toast("请选择门店");
            return;
        }
        if (TextUtils.isEmpty(mTimeline)) {
            //时间选择器
            toast("请选择日期");
            return;
        }
        if (TextUtils.isEmpty(mStartTime) || TextUtils.isEmpty(mEndTime)) {
            toast("请选择时间段");
            return;
        }
        mRealname = etUsername.getText().toString().trim();
        if (TextUtils.isEmpty(mRealname)) {
            toast("请输入送修人姓名");
            return;
        }
        mPhone = etPhone.getText().toString().trim();
        if (TextUtils.isEmpty(mPhone)) {
            toast("请输入送修人手机号");
            return;
        }

        actionsCreator().postServiceKeepOrder(this, mPhone, mServiceId, mRealname, mCityName, mTimeline, mStartTime, mEndTime, mPackageId);
    }

    @Override
    public int getLayoutId() {
        return R.layout.service_activity_keep_detail;
    }

    @Override
    protected boolean flux() {
        return true;
    }

    @Override
    public void onSelectCityClick(String province, String city, String area) {
        mCityName = city;
        tvCity.setText(city);
    }
}
