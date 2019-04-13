package com.bocweb.community.ui.fmt;


import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bocweb.home.R;
import com.njh.common.core.RouterHub;
import com.njh.common.flux.base.BaseFluxFragment;

import java.io.IOException;
import java.io.InputStream;

import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 *
 * @author niejiahuan
 * 社区
 */
@Route(path = RouterHub.COMMUNITY_FMT)
public class CommunityFmt extends BaseFluxFragment {

    private String[][] a = new String[][]{};

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    private void showPickerView() {// 弹出选择器

        InputStream inputStream = null;
        try {
            inputStream = getContext().getAssets().open("citys.json");
            int size = inputStream.available();
            int len = -1;
            byte[] bytes = new byte[size];
            inputStream.read(bytes);
            inputStream.close();
            String string = new String(bytes);

        } catch (IOException e) {
            e.printStackTrace();
        }
        OptionsPickerView options = new OptionsPickerBuilder(getContext(), new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {

            }
        }).build();
        options.show();


        //条件选择器
//        OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
//
//            @Override
//            public void onOptionsSelect(int options1, int option2, int options3, View v) {
//                //返回的分别是三个级别的选中位置
//                String tx = options1Items.get(options1).getPickerViewText()
//                        + options2Items.get(options1).get(option2)
//                        + options3Items.get(options1).get(option2).get(options3).getPickerViewText();
//                tvOptions.setText(tx);
//            }
//        }).build();
////        pvOptions.setPicker(options1Items, options2Items, options3Items);
//        pvOptions.show();
    }

    @Override
    public int getLayoutId() {
        return R.layout.community_community_fmt;
    }
}
