package com.bocai.service.ui.feedback;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bocai.service.R;
import com.bocai.service.R2;
import com.bocai.service.api.ServiceAction;
import com.bocai.service.api.ServiceStore;
import com.bocai.service.bean.FeedbackCity;
import com.bocai.service.bean.FeedbackProvince;
import com.njh.common.core.ReqTag;
import com.njh.common.core.RouterHub;
import com.njh.common.flux.base.BaseFluxActivity;
import com.njh.common.flux.stores.Store;
import com.njh.common.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

/**
 * @author libingjun
 * @version 投诉与建议
 * @date 2019/4/25
 */
@Route(path = RouterHub.Service.FEEDBACK)
public class FeedbackActivity extends BaseFluxActivity<ServiceStore, ServiceAction> {

    @BindView(R2.id.et_input)
    EditText etInput;
    @BindView(R2.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R2.id.ll_select)
    LinearLayout llSelect;
    @BindView(R2.id.tv_select_city)
    TextView tvSelectCity;
    @BindView(R2.id.tv_select_shop)
    TextView tvSelectShop;

    @Override
    public void initData(Bundle savedInstanceState) {
        request();
    }

    private void request() {

    }

    @Override
    protected void updateView(Store.StoreChangeEvent event) {
        super.updateView(event);
        switch (event.url) {
            case ReqTag.Service.SERVICE_SELECT_CITY:
                List<FeedbackProvince> provinceList = (List<FeedbackProvince>) event.data;
                city(provinceList);
                break;
        }
    }


    public void onSubmit(View view) {

    }

    public void onSelectCity(View view) {
        actionsCreator().selectCity(this, "2");
    }

    public void onSelectShop(View view) {

    }

    private OptionsPickerView pickerView;

    private void city(List<FeedbackProvince> provinceList) {

        List<String> list1 = new ArrayList<>();
        List<List<String>> list2 = new ArrayList<>();

        for (FeedbackProvince province : provinceList) {
            list1.add(province.getFullName());
            List<FeedbackCity> citys = province.getCity();
            List<String> list3 = new ArrayList<>();
            for (FeedbackCity city : citys) {
                list3.add(city.getFullName());
            }
            list2.add(list3);
        }


        pickerView = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                // 返回的分别是三个级别的选中位置
                String province = list1.get(options1);
                String city = list2.get(options1).get(options2);
                LogUtil.e(province + city);
            }
        }).setTitleText("城市选择")
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .build();


        pickerView.setPicker(list1, list2);

        pickerView.show();
    }

    @Override
    public void setListener() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.service_activity_feedback;
    }

    @Override
    protected boolean flux() {
        return true;
    }
}
