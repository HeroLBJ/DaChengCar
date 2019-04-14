package com.bocweb.home.ui.activity.dynamic;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

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
        implements ImageItemTouchHelper.DragListener{

    @BindView(R2.id.et_content)
    EditText etContent;
    @BindView(R2.id.recycler_view)
    RecyclerView recyclerView;

    private LookImageAdapter mAdapter;
    private ArrayList<String> mImageList;

    @Override
    public void initData(Bundle savedInstanceState) {
        GridLayoutManager glm = new GridLayoutManager(this,3);
        recyclerView.setLayoutManager(glm);
        mAdapter = new LookImageAdapter(this);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void setListener() {

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
            for(String path : mImageList){
                Log.d("Path", "图片地址：" + path);
            }
            mAdapter.refresh(mImageList);
            ImageItemTouchHelper helper = new ImageItemTouchHelper(this,mAdapter,mImageList);
            helper.setDragListener(this);
            ItemTouchHelper itemTouchHelper = new ItemTouchHelper(helper);
            itemTouchHelper.attachToRecyclerView(recyclerView);
        }
    }

    @Override
    public void deleteState(boolean delete) {

    }

    @Override
    public void dragState(boolean start) {

    }
}
