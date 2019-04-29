package com.bocweb.mine.ui.act.follow;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bocweb.mine.R;
import com.njh.common.core.RouterHub;
import com.njh.common.flux.base.BaseFluxFragment;

/**
 * @author libingjun
 * @version 1.0
 * @date 2019/4/29
 */
@Route(path = RouterHub.Mine.FOLLOW_FANS)
public class FansFragment extends BaseFluxFragment {
    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.mine_fragment_follow_and_fans;
    }
}
