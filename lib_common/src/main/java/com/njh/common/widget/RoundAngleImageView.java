package com.njh.common.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Path;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import com.njh.common.R;

import androidx.appcompat.widget.AppCompatImageView;

/**
 * @author libingjun
 * @date 2019/4/11
 */
public class RoundAngleImageView extends AppCompatImageView {

    private int defaultRadius = 0;
    private int radius;
    private int leftTopRadius;
    private int rightTopRadius;
    private int rightBottomRadius;
    private int leftBottomRadius;

    public RoundAngleImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        if (Build.VERSION.SDK_INT < 18) {
            setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }
        // 读取配置
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.common_round_angle_image_view);
        radius = array.getDimensionPixelOffset(R.styleable.common_round_angle_image_view_common_radius, defaultRadius);
        leftTopRadius = array.getDimensionPixelOffset(R.styleable.common_round_angle_image_view_common_radius_left_top, defaultRadius);
        rightTopRadius = array.getDimensionPixelOffset(R.styleable.common_round_angle_image_view_common_radius_right_top, defaultRadius);
        leftBottomRadius = array.getDimensionPixelOffset(R.styleable.common_round_angle_image_view_common_radius_left_bottom, defaultRadius);
        rightBottomRadius = array.getDimensionPixelOffset(R.styleable.common_round_angle_image_view_common_radius_right_bottom, defaultRadius);

        if (defaultRadius == leftTopRadius) {
            leftTopRadius = radius;
        }
        if (defaultRadius == rightTopRadius) {
            rightTopRadius = radius;
        }
        if (defaultRadius == rightBottomRadius) {
            rightBottomRadius = radius;
        }
        if (defaultRadius == leftBottomRadius) {
            leftBottomRadius = radius;
        }
        array.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int maxLeft = Math.max(leftTopRadius, leftBottomRadius);
        int maxRight = Math.max(rightTopRadius, rightBottomRadius);
        int minWidth = maxLeft + maxRight;
        int maxTop = Math.max(leftTopRadius, rightTopRadius);
        int maxBottom = Math.max(leftBottomRadius, rightBottomRadius);
        int minHeight = maxTop + maxBottom;
        if (getWidth() >= minWidth && getHeight() > minHeight) {
            Path path = new Path();

            path.moveTo(leftTopRadius, 0);
            path.lineTo(getWidth() - rightTopRadius, 0);
            path.quadTo(getWidth(), 0, getWidth(), rightTopRadius);

            path.lineTo(getWidth(), getHeight() - rightBottomRadius);
            path.quadTo(getWidth(), getHeight(), getWidth() - rightBottomRadius, getHeight());

            path.lineTo(leftBottomRadius, getHeight());
            path.quadTo(0, getHeight(), 0, getHeight() - leftBottomRadius);

            path.lineTo(0, leftTopRadius);
            path.quadTo(0, 0, leftTopRadius, 0);

            canvas.clipPath(path);
        }
        super.onDraw(canvas);
    }
}
