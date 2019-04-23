package com.njh.common.picker.city;

import android.content.Context;
import android.graphics.Color;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.njh.base.app.BaseApp;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author libingjun
 * @date 2019/4/19
 */
public class CityHelper {

    public static List<String> getProvinceList() {
        return options1Items;
    }

    public static List<List<String>> getCityList() {
        return options2Items;
    }

    public static List<List<List<String>>> getArea() {
        return options3Items;
    }

    //  省
    private static List<String> options1Items = new ArrayList<>();
    //  市
    private static List<List<String>> options2Items = new ArrayList<>();
    //  区
    private static List<List<List<String>>> options3Items = new ArrayList<>();

    static {
        getData();
    }

    private static void getData() {// 弹出选择器

        InputStream inputStream = null;
        try {
            inputStream = BaseApp.getAppInstance().getAssets().open("city_code.json");
            int size = inputStream.available();
            int len = -1;
            byte[] bytes = new byte[size];
            inputStream.read(bytes);
            inputStream.close();
            String string = new String(bytes);
            List<JsonRootBean> beanList = JSON.parseArray(string, JsonRootBean.class);
            add(beanList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void add(List<JsonRootBean> shengList) {
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

    private OptionsPickerView pickerView;

    private CityHelper() {
    }

    private static CityHelper INSTANCE;

    public static CityHelper getInstance() {
        if (INSTANCE == null) {
            synchronized (CityHelper.class) {
                if (INSTANCE == null) {
                    INSTANCE = new CityHelper();
                }
            }
        }
        return INSTANCE;
    }

    public void initCity(Context context) {
        initCity(context, 2);
    }

    public void initCity(Context context, int count) {
        if (pickerView == null) {
            pickerView = new OptionsPickerBuilder(context, new OnOptionsSelectListener() {
                @Override
                public void onOptionsSelect(int options1, int options2, int options3, View v) {
                    //返回的分别是三个级别的选中位置
//                String tx = CityHelper.getProvinceList().get(options1) +
//                        CityHelper.getCityList().get(options1).get(options2);
                    String province = CityHelper.getProvinceList().get(options1);
                    String city = CityHelper.getCityList().get(options1).get(options2);
                    String area = CityHelper.getArea().get(options1).get(options2).get(options3);

                    if (onSelectCityListener != null) {
                        onSelectCityListener.onSelectCityClick(province, city, area);
                    }
                }
            }).setTitleText("城市选择")
                    .setDividerColor(Color.BLACK)
                    .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                    .setContentTextSize(20)
                    .build();
        }
        if (count == 1) {
            pickerView.setPicker(CityHelper.getProvinceList());
        } else if (count == 3) {
            pickerView.setPicker(CityHelper.getProvinceList(), CityHelper.getCityList(), CityHelper.getArea());
        } else {
            pickerView.setPicker(CityHelper.getProvinceList(), CityHelper.getCityList());
        }
        pickerView.show();
    }

    public OnSelectCityListener onSelectCityListener;

    public void setOnSelectCityListener(OnSelectCityListener onSelectCityListener) {
        this.onSelectCityListener = onSelectCityListener;
    }

    public interface OnSelectCityListener {
        void onSelectCityClick(String province, String city, String area);
    }
}
