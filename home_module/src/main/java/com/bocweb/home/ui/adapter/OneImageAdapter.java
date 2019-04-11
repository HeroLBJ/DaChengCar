package com.bocweb.home.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bocweb.home.R;
import com.njh.common.utils.img.GlideUtils;
import com.njh.common.widget.RoundAngleImageView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author libingjun
 * @date 2019/4/10
 */
public class OneImageAdapter extends RecyclerView.Adapter<OneImageAdapter.ViewHolder> {

    private Context mContext;
    private List<String> mImageList;
    private LayoutInflater mInflater;

    public OneImageAdapter(Context context, List<String> imageList) {
        mContext = context;
        mImageList = imageList;
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = mInflater.inflate(R.layout.home_adapter_one_image, null, false);
        return new ViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GlideUtils.getInstance().loadImg(mContext, mImageList.get(position), holder.ivImage);
    }

    @Override
    public int getItemCount() {
        return mImageList == null ? 0 : mImageList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        RoundAngleImageView ivImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivImage = itemView.findViewById(R.id.iv_image);
        }
    }
}
