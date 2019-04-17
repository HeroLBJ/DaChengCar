package com.bocweb.home.ui.activity.dynamic;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bocai.select.photo.util.ImageSelector;
import com.bocweb.home.R;
import com.bocweb.home.R2;
import com.bocweb.home.ui.api.MainAction;
import com.bocweb.home.ui.api.MainStore;
import com.njh.common.core.RouterHub;
import com.njh.common.flux.base.BaseFluxActivity;

import java.util.ArrayList;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

/**
 * @author libingjun
 * @date 2019/4/14
 */
@Route(path = RouterHub.Home.SEND_DYNAMIC)
public class SendDynamicActivity extends BaseFluxActivity<MainStore, MainAction>
        implements ItemTouchHelperCallback.DragListener {

    @BindView(R2.id.et_content)
    EditText etContent;
    @BindView(R2.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R2.id.rl_root)
    RelativeLayout rlRoot;
    @BindView(R2.id.tv_delete)
    TextView tvDelete;

    private ItemTouchHelper itemTouchHelper;
    private LookImageAdapter mAdapter;
    private ArrayList<String> mImageList = new ArrayList<>();

    @Override
    public void initData(Bundle savedInstanceState) {
        GridLayoutManager glm = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(glm);
        mAdapter = new LookImageAdapter(this);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void setListener() {
        recyclerView.addOnItemTouchListener(new OnRecyclerItemClickListener(recyclerView) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder vh) {

            }

            @Override
            public void onItemLongClick(RecyclerView.ViewHolder vh) {
                if (mImageList.size() == 0) {
                    return;
                }
                if (mImageList.size() == 9) {
                    itemTouchHelper.startDrag(vh);
                } else {
                    if (vh.getLayoutPosition() != mImageList.size()) {
                        itemTouchHelper.startDrag(vh);
                    }
                }
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.home_activity_send_dynamic;
    }

    @Override
    protected boolean flux() {
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && data != null) {
            mImageList = data.getStringArrayListExtra(ImageSelector.SELECT_RESULT);
            boolean isCameraImage = data.getBooleanExtra(ImageSelector.IS_CAMERA_IMAGE, false);
//            Log.d("ImageSelector", "是否是拍照图片：" + isCameraImage);
            for (String path : mImageList) {
                Log.d("Path", "图片地址：" + path);
            }
            mAdapter.refresh(mImageList);
            ItemTouchHelperCallback callback = new ItemTouchHelperCallback(this, mAdapter, mImageList);
            callback.setDragListener(this);
            itemTouchHelper = new ItemTouchHelper(callback);
            itemTouchHelper.attachToRecyclerView(recyclerView);
        }
    }

    @Override
    public void deleteState(boolean delete) {
        if (delete) {
            tvDelete.setText("松手即可删除");
        } else {
            tvDelete.setText("拖动到此处删除");
        }
    }

    @Override
    public void dragState(boolean start) {
        if(start){
            tvDelete.setVisibility(View.VISIBLE);
        }else{
            tvDelete.setVisibility(View.GONE);
        }
    }
}
