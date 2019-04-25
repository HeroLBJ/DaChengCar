package com.bocweb.community.ui.fmt;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bocweb.community.R;
import com.njh.common.core.RouterHub;
import com.njh.common.flux.base.BaseFluxFragment;

import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 *
 * @author niejiahuan
 * 社区
 */
@Route(path = RouterHub.COMMUNITY_FMT)
public class CommunityFmt extends BaseFluxFragment {

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.community_community_fmt;
    }
}
