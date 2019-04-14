package com.bocweb.home.ui.activity.friend;

import android.widget.TextView;

import com.bocweb.home.R;
import com.bocweb.home.ui.bean.Friend;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.njh.common.utils.img.GlideUtils;

import java.util.List;

import androidx.annotation.Nullable;

/**
 * @author libingjun
 * @date 2019/4/14
 */
public class AddFriendAdapter extends BaseQuickAdapter<Friend, BaseViewHolder> {
    public AddFriendAdapter(@Nullable List<Friend> data) {
        super(R.layout.home_adapter_add_friend, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Friend item) {
        GlideUtils.getInstance().loadImg(mContext, item.getAvatar(), helper.getView(R.id.civ_photo));
        helper.setText(R.id.tv_name, item.getNickname());
        helper.setText(R.id.tv_desc, item.getSightml());
        TextView tvFollow = helper.getView(R.id.tv_follow);
        String isFollow = item.getIsFollow();
        switch (isFollow) {
            case "1":
                tvFollow.setText("已关注");
                break;
            case "2":
                tvFollow.setText("聊天");
                break;
            default:
                tvFollow.setText("+ 关注");
                break;
        }
        tvFollow.setOnClickListener(v -> onFollow(item));
    }

    private void onFollow(Friend item) {

    }
}
