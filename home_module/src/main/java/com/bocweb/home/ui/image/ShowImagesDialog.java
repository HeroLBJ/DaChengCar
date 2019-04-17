package com.bocweb.home.ui.image;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bocweb.home.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.github.chrisbanes.photoview.OnOutsidePhotoTapListener;
import com.github.chrisbanes.photoview.OnPhotoTapListener;
import com.github.chrisbanes.photoview.OnScaleChangedListener;
import com.github.chrisbanes.photoview.PhotoView;
import com.github.chrisbanes.photoview.PhotoViewAttacher;
import com.njh.base.app.BaseApp;
import com.njh.common.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

public class ShowImagesDialog extends Dialog {

    private View mView;
    private Context mContext;
    private ShowImagesViewPager mViewPager;
    private TextView mIndexText;
    private List<String> mImgUrls;
    private List<String> mTitles;
    private List<View> mViews;
    private ShowImagesAdapter mAdapter;
    private int mCurrentItem = 0;

    public ShowImagesDialog(@NonNull Context context, List<String> imgUrls) {
        this(context, imgUrls, 0);
    }

    public ShowImagesDialog(@NonNull Context context, List<String> imgUrls, int item) {
        super(context, R.style.HomeTransparentBgDialog);
        this.mContext = context;
        this.mImgUrls = imgUrls;
        mCurrentItem = item;
        initView();
        initData();
    }

    private void initView() {
        mView = View.inflate(mContext, R.layout.home_dialog_images_brower, null);
        mViewPager = mView.findViewById(R.id.vp_images);
        mIndexText = mView.findViewById(R.id.tv_image_index);
        mTitles = new ArrayList<>();
        mViews = new ArrayList<>();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(mView);
        Window window = getWindow();
        WindowManager.LayoutParams wl = window.getAttributes();
        wl.x = 0;
        wl.y = 0;
        wl.height = BaseApp.EXACT_SCREEN_HEIGHT;
        wl.width = BaseApp.EXACT_SCREEN_WIDTH;
        wl.gravity = Gravity.CENTER;
        window.setAttributes(wl);
    }

    private void initData() {
        for (int i = 0; i < mImgUrls.size(); i++) {
            final PhotoView photoView = new PhotoView(mContext);
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            photoView.setLayoutParams(layoutParams);
            //点击图片外围（无图片处）监听

            photoView.setOnPhotoTapListener(new OnPhotoTapListener() {
                @Override
                public void onPhotoTap(ImageView view, float x, float y) {
                    dismiss();
                }
            });

            photoView.setOnOutsidePhotoTapListener(new OnOutsidePhotoTapListener() {
                @Override
                public void onOutsidePhotoTap(ImageView imageView) {
                    dismiss();
                }
            });

            Glide.with(mContext)
                    .load(mImgUrls.get(i))
                    .into(new SimpleTarget<Drawable>() {
                        @Override
                        public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                            photoView.setImageDrawable(resource);
                        }
                    });
            mViews.add(photoView);
            mTitles.add(i + "");

            // 让小图保持原形，不充满全屏
//            photoView.setScaleType(ImageView.ScaleType.CENTER);
//            photoView.setMinimumScale(0.8f);
//            PhotoViewAttacher photoViewAttacher= photoView.getAttacher();
            //通过photoViewAttacher设置缩放大小
            //第一个参数是获取photoViewAttacher自带的缩放大小最小值，第二个和第三个参数设置缩放中心
//            photoView.setOnScaleChangeListener(new OnScaleChangedListener() {
//                @Override
//                public void onScaleChange(float scaleFactor, float focusX, float focusY) {
//                    LogUtil.e("scale = " + photoView.getScale());
////                    photoViewAttacher.setScale(photoViewAttacher.getMinimumScale(), 0f, 0f, true);
//                }
//            });
        }

        mAdapter = new ShowImagesAdapter(mViews, mTitles);
        mViewPager.setAdapter(mAdapter);
        mViewPager.setCurrentItem(mCurrentItem);
        mIndexText.setText((mCurrentItem + 1) + "/" + mImgUrls.size());
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mIndexText.setText(position + 1 + "/" + mImgUrls.size());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

}