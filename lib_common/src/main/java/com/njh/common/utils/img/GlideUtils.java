package com.njh.common.utils.img;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

/**
 * @author niejiahuan
 */
public class GlideUtils {
    private static GlideUtils glideUtils;

    public static GlideUtils getInstance() {
        if (null==glideUtils){
            glideUtils=new GlideUtils();
        }
        return glideUtils;
    }
    public void loadImg(Context context, String imgPath, ImageView imageView,int defImg){
        RequestOptions requestOptions=new RequestOptions();
        requestOptions.error(defImg);
        requestOptions.placeholder(defImg);
        Glide.with(context)
                .load(imgPath)
                .apply(requestOptions)
                .into(imageView);
    }
    public void loadImg(Context context, String imgPath, ImageView imageView){
        RequestOptions requestOptions=new RequestOptions();
//        requestOptions.error(R.mipmap.act_img_loading);
//        requestOptions.placeholder(R.mipmap.act_img_loading);
        Glide.with(context)
                .load(imgPath)
                .apply(requestOptions)
                .into(imageView);
    }
}
