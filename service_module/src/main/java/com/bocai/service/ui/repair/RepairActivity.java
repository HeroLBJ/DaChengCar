package com.bocai.service.ui.repair;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bocai.select.photo.util.ImageSelector;
import com.bocai.service.R;
import com.bocai.service.R2;
import com.bocai.service.api.ServiceAction;
import com.bocai.service.api.ServiceStore;
import com.njh.common.core.RouterHub;
import com.njh.common.flux.base.BaseFluxActivity;
import com.njh.common.utils.LogUtil;
import com.njh.common.utils.arouter.ArouterUtils;

import java.util.ArrayList;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

/**
 * @author libingjun
 * @date 2019/4/23
 */
@Route(path = RouterHub.Service.REPAIR)
public class RepairActivity extends BaseFluxActivity<ServiceStore, ServiceAction>
        implements ItemTouchHelperCallback.DragListener {

    @BindView(R2.id.cd_info)
    CardView cvInfo;
    @BindView(R2.id.et_detail)
    EditText etDetail;
    @BindView(R2.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R2.id.tv_desc)
    TextView tvDesc;
    @BindView(R2.id.tv_submit)
    TextView tvSubmit;
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
        cvInfo.setOnClickListener(v -> {
            // 选择维修类型
            actionsCreator().getServicePackage(this);
        });
        tvSubmit.setOnClickListener(v -> {
            // 提交
            String detail = etDetail.getText().toString().trim();

            LogUtil.e("路由跳转。。。");
            ArouterUtils.getInstance().navigation(this,RouterHub.Service.SUBMIT_SUCCESS);
        });

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
        return R.layout.service_activity_repair;
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
        if (start) {
            tvDelete.setVisibility(View.VISIBLE);
        } else {
            tvDelete.setVisibility(View.GONE);
        }
    }
}
