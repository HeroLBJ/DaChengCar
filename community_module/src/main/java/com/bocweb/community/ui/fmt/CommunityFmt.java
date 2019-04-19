package com.bocweb.community.ui.fmt;


import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.fastjson.JSON;
import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bocweb.community.R;
import com.njh.common.core.RouterHub;
import com.njh.common.flux.base.BaseFluxFragment;
import com.njh.common.utils.LogUtil;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 *
 * @author niejiahuan
 * 社区
 */
@Route(path = RouterHub.COMMUNITY_FMT)
public class CommunityFmt extends BaseFluxFragment {

    //  省
    private List<String> options1Items = new ArrayList<>();
    //  市
    private List<List<String>> options2Items = new ArrayList<>();
    //  区
    private List<List<List<String>>> options3Items = new ArrayList<>();

    @Override
    public void initData(Bundle savedInstanceState) {
        getData();
        show();
    }

    private void getData() {// 弹出选择器

        InputStream inputStream = null;
        try {
            inputStream = getContext().getAssets().open("city_code.json");
            int size = inputStream.available();
            int len = -1;
            byte[] bytes = new byte[size];
            inputStream.read(bytes);
            inputStream.close();
            String string = new String(bytes);
            List<JsonRootBean> beanList = JSON.parseArray(string, JsonRootBean.class);
            if (beanList != null) {
                LogUtil.e("beanList:" + beanList.size());
            } else {
                LogUtil.e("beanList is null");
            }

            add(beanList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void add(List<JsonRootBean> shengList) {
        //     把解析后的数据组装成想要的list
//     遍历省
        for (int i = 0; i < shengList.size(); i++) {
            options1Items.add(shengList.get(i).getName());
//         存放城市
            List<String> cityList = new ArrayList<>();
//         存放区
            List<List<String>> province_AreaList = new ArrayList<>();
//         遍历市
            JsonRootBean jsonRootBean = shengList.get(i);
            List<City> list222 = jsonRootBean.getCity();
            if (list222 == null) {
                LogUtil.e("list222  is  null,position:" + i);
            } else {
                LogUtil.e("list222 size is " + list222.size());
            }
            for (int c = 0; c < shengList.get(i).getCity().size(); c++) {
//        拿到城市名称
                String cityName = shengList.get(i).getCity().get(c).getName();
                cityList.add(cityName);

                List<String> city_AreaList = new ArrayList<>();//该城市的所有地区列表
                List<Area> areaList = shengList.get(i).getCity().get(c).getArea();
                for (int x = 0; x < areaList.size(); x++) {
                    city_AreaList.add(areaList.get(x).getName());
                }
                province_AreaList.add(city_AreaList);
            }
            /**
             * 添加城市数据
             */
            options2Items.add(cityList);
            /**
             * 添加地区数据
             */
            options3Items.add(province_AreaList);
        }
    }

    private void show() {
        OptionsPickerView pvOptions = new OptionsPickerBuilder(getContext(), new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String tx = options1Items.get(options1) +
                        options2Items.get(options1).get(options2) +
                        options3Items.get(options1).get(options2).get(options3);

                Toast.makeText(getContext(), tx, Toast.LENGTH_SHORT).show();
                LogUtil.e("tx:" + tx);
            }
        })

                .setTitleText("城市选择")
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .build();

        /*pvOptions.setPicker(options1Items);//一级选择器
        pvOptions.setPicker(options1Items, options2Items);//二级选择器*/
        pvOptions.setPicker(options1Items, options2Items, options3Items);//三级选择器
        pvOptions.show();
    }


    @Override
    public int getLayoutId() {
        return R.layout.community_community_fmt;
    }
}
