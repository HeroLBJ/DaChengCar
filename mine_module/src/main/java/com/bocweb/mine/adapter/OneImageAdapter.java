package com.bocweb.mine.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bocweb.mine.R;
import com.njh.common.image.ShowImagesDialog;
import com.njh.common.utils.img.GlideUtils;
import com.njh.common.widget.RoundAngleAndSquareImageView;

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
        View layout = mInflater.inflate(R.layout.mine_adapter_one_image, parent, false);
        return new ViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GlideUtils.getInstance().loadImg(mContext, mImageList.get(position), holder.ivImage);
        holder.ivImage.setOnClickListener(v -> {
            new ShowImagesDialog(mContext, mImageList,position).show();
        });
    }

    @Override
    public int getItemCount() {
        return mImageList == null ? 0 : mImageList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        RoundAngleAndSquareImageView ivImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivImage = itemView.findViewById(R.id.iv_image);
        }
    }
}
