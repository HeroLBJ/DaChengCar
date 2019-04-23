package com.bocai.service.ui.call;

import com.bocai.service.R;
import com.bocai.service.bean.CallPolice;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import androidx.annotation.Nullable;

/**
 * @author libingjun
 * @date 2019/4/23
 */
public class CallPoliceAdapter extends BaseQuickAdapter<CallPolice, BaseViewHolder> {

    public CallPoliceAdapter(@Nullable List<CallPolice> data) {
        super(R.layout.service_adapter_call_police, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CallPolice item) {

    }
}
