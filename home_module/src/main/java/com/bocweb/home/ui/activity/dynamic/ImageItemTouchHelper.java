package com.bocweb.home.ui.activity.dynamic;

import android.app.Service;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Vibrator;
import android.view.View;
import android.widget.Toast;

import com.njh.common.utils.LogUtil;

import java.util.ArrayList;
import java.util.Collections;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author libingjun
 * @date 2019/4/8
 */
public class ImageItemTouchHelper extends ItemTouchHelper.Callback {

    private Context mContext;
    private LookImageAdapter mAdapter;
    private ArrayList<String> images;
    private boolean haveAdd;

    public ImageItemTouchHelper(Context context, LookImageAdapter lookImageAdapter, ArrayList<String> images) {
        mAdapter = lookImageAdapter;
        mContext = context;
        this.images = images;
        haveAdd = images.size() != 9;
    }

    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        int dragFrlg = 0;
        if (recyclerView.getLayoutManager() instanceof GridLayoutManager) {
            dragFrlg = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        } else if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {
            dragFrlg = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        }
        return makeMovementFlags(dragFrlg, 0);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        int fromPosition = viewHolder.getAdapterPosition();
        //拿到当前拖拽到的item的viewHolder
        int toPosition = target.getAdapterPosition();

        LogUtil.e(fromPosition + "-" + toPosition);
        if (haveAdd) {
            toPosition--;
        }

        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(images, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(images, i, i - 1);
            }
        }
        mAdapter.notifyItemMoved(fromPosition, toPosition);
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

    }

    @Override
    public void onSelectedChanged(@Nullable RecyclerView.ViewHolder viewHolder, int actionState) {
        if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
            viewHolder.itemView.setBackgroundColor(Color.RED);
            //获取系统震动服务//震动70毫秒
            Vibrator vib = (Vibrator) mContext.getSystemService(Service.VIBRATOR_SERVICE);
            vib.vibrate(70);

            // 从屏幕底部弹出popupWindow
            Toast.makeText(mContext, "到这里来删除", Toast.LENGTH_SHORT).show();
        }

        if (ItemTouchHelper.ACTION_STATE_DRAG == actionState && dragListener != null) {
            dragListener.dragState(true);
        }
        super.onSelectedChanged(viewHolder, actionState);

    }

    @Override
    public long getAnimationDuration(RecyclerView recyclerView, int animationType, float animateDx, float animateDy) {
        //手指放开
        up = true;
        return super.getAnimationDuration(recyclerView, animationType, animateDx, animateDy);
    }

    @Override
    public boolean isLongPressDragEnabled() {
        return true;
    }

    @Override
    public void clearView(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
        mAdapter.notifyDataSetChanged();
        initData();
    }

    @Override
    public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        if (null == dragListener) {
            return;
        }
        dragListener.dragState(true);//显示删除区域

        // item偏移量 >= RecyclerView的高 - item底部到RecyclerView顶边的距离 - TextView的高
        if (dY >= (recyclerView.getHeight()
                - viewHolder.itemView.getBottom()//item底部距离recyclerView顶部高度
//                - CommonUtils.getPixelById(R.dimen.article_post_delete)
        )) {//拖到删除处
            dragListener.deleteState(true);
            if (up) {//在删除处放手，则删除item
                viewHolder.itemView.setVisibility(View.INVISIBLE);//先设置不可见，如果不设置的话，会看到viewHolder返回到原位置时才消失，因为remove会在viewHolder动画执行完成后才将viewHolder删除
                images.remove(viewHolder.getAdapterPosition());
                mAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
                initData();
                return;
            }
        } else {//没有到删除处
            if (View.INVISIBLE == viewHolder.itemView.getVisibility()) {//如果viewHolder不可见，则表示用户放手，重置删除区域状态
                dragListener.dragState(false);
            }
            dragListener.deleteState(false);
        }
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
    }

    private boolean up;

    /**
     * 重置
     */
    private void initData() {
        if (dragListener != null) {
            dragListener.deleteState(false);
            dragListener.dragState(false);
        }
        up = false;
    }

    private DragListener dragListener;

    public void setDragListener(DragListener dragListener) {
        this.dragListener = dragListener;
    }

    interface DragListener {
        /**
         * 用户是否将 item拖动到删除处，根据状态改变颜色
         */
        void deleteState(boolean delete);

        /**
         * 是否于拖拽状态
         */
        void dragState(boolean start);
    }
}

