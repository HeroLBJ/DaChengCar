package com.bocweb.home.ui.activity.dynamic;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bocai.select.photo.util.ImageSelector;
import com.bocweb.home.R;
import com.bocweb.home.ui.image.ShowImagesDialog;
import com.njh.common.utils.img.GlideUtils;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;

/**
 * @author libingjun
 * @date 2019/4/4
 */
public class LookImageAdapter extends RecyclerView.Adapter<LookImageAdapter.ViewHolder> {

    private Activity mContext;
    private ArrayList<String> mImages;
    private LayoutInflater mInflater;

    public LookImageAdapter(Activity context) {
        mContext = context;
        this.mInflater = LayoutInflater.from(mContext);
    }

    public ArrayList<String> getImages() {
        return mImages;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.home_adapter_recycler_view_look_image, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        if (mImages == null || mImages.size() == 0) {
            holder.ivImage.setImageResource(R.drawable.res_add_photo);
        } else if (mImages.size() < 9) {
            if (position == mImages.size()) {
                holder.ivImage.setImageResource(R.drawable.res_add_photo);
            } else {
                GlideUtils.getInstance().loadImg(mContext, mImages.get(position), holder.ivImage);
            }
        } else if (mImages.size() == 9) {
            GlideUtils.getInstance().loadImg(mContext, mImages.get(position), holder.ivImage);
        }

        holder.ivImage.setOnClickListener(v -> {
            int size = mImages == null ? 0 : mImages.size();
            if (mImages == null || (size < 9 && position == size)) {
                onOpenPhoto();
            } else {
                new ShowImagesDialog(mContext, mImages, position).show();
            }
        });
    }

    private void onOpenPhoto() {
        ImageSelector.builder()
                .useCamera(true)
                .setSingle(false)
                .setViewImage(true)
                .setMaxSelectCount(9)
                .setSelected(mImages)
                .start(mContext, 0);
    }

    @Override
    public int getItemCount() {
        if (mImages == null) {
            return 1;
        }
        if (mImages.size() < 9) {
            return mImages.size() + 1;
        } else {
            return 9;
        }
    }

    public void refresh(ArrayList<String> images) {
        mImages = images;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivImage;

        public ViewHolder(View itemView) {
            super(itemView);
            ivImage = itemView.findViewById(R.id.iv_image);
        }
    }
}
