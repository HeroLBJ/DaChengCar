package com.bocai.service.ui.navigation;

import android.Manifest;
import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.allen.library.CircleImageView;
import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.DotOptions;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.PoiDetailInfo;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.baidu.mapapi.search.poi.PoiDetailSearchOption;
import com.baidu.mapapi.search.poi.PoiDetailSearchResult;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bocai.service.R;
import com.bocai.service.R2;
import com.bocai.service.api.ServiceAction;
import com.bocai.service.api.ServiceStore;
import com.bocai.service.bean.ServiceDealers;
import com.bocai.service.bean.SuperServiceBean;
import com.njh.common.city.CityHelper;
import com.njh.common.core.ReqTag;
import com.njh.common.core.RouterHub;
import com.njh.common.flux.base.BaseFluxActivity;
import com.njh.common.flux.stores.Store;
import com.njh.common.location.LocationService;
import com.njh.common.simple.SimplePoiSearchListener;
import com.njh.common.utils.LogUtil;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

/**
 * @author libingjun
 * @date 2019/4/19
 */
@Route(path = RouterHub.Service.NAVIGATION)
public class NavigationActivity extends BaseFluxActivity<ServiceStore, ServiceAction> implements NavigationAdapter.OnSelectItemListener {

    @BindView(R2.id.mapView)
    MapView mMapView;
    @BindView(R2.id.iv1)
    CircleImageView iv1;
    @BindView(R2.id.iv2)
    CircleImageView iv2;
    @BindView(R2.id.iv3)
    CircleImageView iv3;
    @BindView(R2.id.iv4)
    CircleImageView iv4;
    @BindView(R2.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R2.id.rl_select)
    RelativeLayout rlSelect;
    @BindView(R2.id.tv_province)
    TextView tvProvince;
    @BindView(R2.id.tv_city)
    TextView tvCity;
    @BindView(R2.id.rl_detail_info)
    RelativeLayout rlDetailInfo;
    @BindView(R2.id.tv_name)
    TextView tvName;
    @BindView(R2.id.tv_detail)
    TextView tvDetail;
    @BindView(R2.id.tv_call)
    TextView tvCall;
    @BindView(R2.id.tv_distance)
    TextView tvDistance;
    @BindView(R2.id.tv_repair)
    TextView tvRepair;
    @BindView(R2.id.tv_keep)
    TextView tvKeep;

    private PoiSearch mPoiSearch;
    private BaiduMap mBaiduMap;

    private NavigationAdapter mAdapter;
    private LocationService locationService;
    private boolean isFirstLoc = true;
    private LocationClient mClient;
    private MapLocationListener myLocationListener = new MapLocationListener();
    private List<ServiceDealers> mList = new ArrayList<>();

    // 一定要有值
    private String selectWord = "银行";
    // 一定要有值
    private String selectCity = "杭州市";
    private double mCurrentLat, mCurrentLon;

    @Override
    public void initData(Bundle savedInstanceState) {
        mAdapter = new NavigationAdapter(this, mList);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(mAdapter);

        mBaiduMap = mMapView.getMap();
        mMapView.showScaleControl(false);
        mMapView.showZoomControls(false);
        mBaiduMap.setMyLocationEnabled(true);

        LocationClientOption mOption = new LocationClientOption();
        mOption.setScanSpan(3 * 60 * 1000);
        mOption.setCoorType("bd09ll");
        mOption.setIsNeedAddress(true);
        mOption.setOpenGps(true);

        // 定位初始化
        mClient = new LocationClient(getApplicationContext());
        mClient.setLocOption(mOption);
        mClient.registerLocationListener(myLocationListener);
        mClient.start();

        requestPermission();

        initPOI();
        request();
    }

    private void initPOI() {
        mPoiSearch = PoiSearch.newInstance();
        mPoiSearch.setOnGetPoiSearchResultListener(new SimplePoiSearchListener() {

            @Override
            public void onGetPoiResult(PoiResult poiResult) {
                super.onGetPoiResult(poiResult);
                List<PoiInfo> allPoi = poiResult.getAllPoi();

                if (allPoi == null) {
                    LogUtil.e("第一步搜索：搜索结果为：" + poiResult.error);
                    return;
                }

                StringBuffer uids = new StringBuffer();
                for (PoiInfo info : allPoi) {
                    uids.append(info.uid + ",");
                }
                String s = uids.substring(0, uids.length() - 1);
                LogUtil.e("最终结果：" + s);
                mPoiSearch.searchPoiDetail(new PoiDetailSearchOption().poiUids(s));
            }

            @Override
            public void onGetPoiDetailResult(PoiDetailSearchResult poiDetailSearchResult) {
                super.onGetPoiDetailResult(poiDetailSearchResult);
                /**
                 * http://mapopen-pub-androidsdk.cdn.bcebos.com/map/doc/v5.3.0/index.html
                 * overallRating:总评分
                 */
                LogUtil.e("第二步搜索：搜索结果为：" + poiDetailSearchResult.error);
                List<PoiDetailInfo> poiDetailInfoList = poiDetailSearchResult.getPoiDetailInfoList();
                for (PoiDetailInfo info : poiDetailInfoList) {
                    LogUtil.e("详情结果：" + info.toString());
                }
            }
        });
    }

    private int pageNo = 1;
    private double longitude;
    private double latidude;
    private String cityName = "杭州市";
    private String keyword = "";

    private void request() {
        actionsCreator().getServiceDealers(this, pageNo, mCurrentLon, mCurrentLat, cityName, keyword);
    }

    @Override
    protected void updateView(Store.StoreChangeEvent event) {
        super.updateView(event);
        switch (event.url) {
            case ReqTag.Service.SERVICE_DEALERS:
                LogUtil.e("event.data:" + event.data);
                SuperServiceBean<ServiceDealers> bean1 = (SuperServiceBean<ServiceDealers>) event.data;
                List<ServiceDealers> serviceDealersList = bean1.getList();

                mList.clear();
                mList.addAll(serviceDealersList);
                mAdapter.notifyDataSetChanged();
                break;
        }
    }

    @Override
    protected boolean flux() {
        return true;
    }

    private void search() {
        /**
         *  PoiCiySearchOption 设置检索属性
         *  city 检索城市
         *  keyword 检索内容关键字
         *  pageNum 分页页码
         */
        LogUtil.e("搜索的城市：" + selectCity + ", 搜索的关键字：" + selectWord);
        mPoiSearch.searchInCity(new PoiCitySearchOption()
                .city(selectCity) //必填
                .keyword(selectWord) //必填
                .pageNum(10));
    }

    @SuppressLint("CheckResult")
    private void requestPermission() {
        LogUtil.e("权限开始申请");
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.requestEach(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)
                .subscribe(permission -> {
                    if (permission.granted) {
                        LogUtil.e("用户给权限了");
                        return;
                    }
                    if (permission.shouldShowRequestPermissionRationale) {
                        LogUtil.e("用户不给权限");
                        return;
                    }
                });
    }

    @Override
    public void setListener() {
        mAdapter.setOnSelectItemListener(this);

        iv1.setOnClickListener(v -> {
            // 移到定位位置
            LatLng lat = new LatLng(mCurrentLat, mCurrentLon);
            MapStatus mMapStatus = new MapStatus.Builder()
                    .target(lat)
                    .zoom(19)
                    .build();
            //定义MapStatusUpdate对象，以便描述地图状态将要发生的变化
            MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);
            //改变地图状态
            mBaiduMap.animateMapStatus(mMapStatusUpdate);
        });
        iv2.setOnClickListener(v -> {
            // 搜索维修站
            request();
        });
        iv3.setOnClickListener(v -> {
            // 搜索停车场
            selectWord = "停车场";
            search();
        });
        iv4.setOnClickListener(v -> {
            // 搜索加油站
            selectWord = "加油站";
            search();
        });
        rlSelect.setOnClickListener(v -> {
            if (pickerView == null) {
                initCity();
            }
            pickerView.show();
        });

        rlDetailInfo.setOnClickListener(v -> {
            recyclerView.setVisibility(View.VISIBLE);
            rlDetailInfo.setVisibility(View.GONE);
        });

        tvRepair.setOnClickListener(v -> {
            // 去维修
        });

        tvKeep.setOnClickListener(v -> {
            // 去保养
        });
    }

    private OptionsPickerView pickerView;
    private String selectProvince;

    private void initCity() {
        pickerView = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
//                String tx = CityHelper.getProvinceList().get(options1) +
//                        CityHelper.getCityList().get(options1).get(options2);
                String cityName = CityHelper.getCityList().get(options1).get(options2);
                selectProvince = CityHelper.getProvinceList().get(options1);
                selectCity = cityName;
                search();
            }
        }).setTitleText("城市选择")
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .build();
        pickerView.setPicker(CityHelper.getProvinceList(), CityHelper.getCityList());
    }

    @Override
    public int getLayoutId() {
        return R.layout.service_activity_navigation;
    }

    @Override
    public void onSelectItemClick(ServiceDealers item) {
        tvName.setText(item.getTitle());
        tvDetail.setText(item.getFullTitle());
        tvCall.setText(item.getTelphone());
        tvDistance.setText(item.getDistance());

        recyclerView.setVisibility(View.INVISIBLE);
        rlDetailInfo.setVisibility(View.VISIBLE);
    }


    class MapLocationListener extends BDAbstractLocationListener {

        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            if (bdLocation == null || mBaiduMap == null) {
                LogUtil.e("bdLocation:null");
                return;
            }
            LogUtil.e("bdLocation:" + bdLocation.getCity());

            MyLocationData locData = new MyLocationData.Builder().accuracy(bdLocation.getRadius())
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(bdLocation.getDirection()).latitude(bdLocation.getLatitude())
                    .longitude(bdLocation.getLongitude()).build();
            mCurrentLat = locData.latitude;
            mCurrentLon = locData.longitude;
            // 设置定位数据
            mBaiduMap.setMyLocationData(locData);
            //地图SDK处理
            if (isFirstLoc) {
                isFirstLoc = false;
                LatLng ll = new LatLng(bdLocation.getLatitude(),
                        bdLocation.getLongitude());
                MapStatus.Builder builder = new MapStatus.Builder();
                builder.target(ll).zoom(18.0f);
                mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
            }
            LatLng point = new LatLng(bdLocation.getLatitude(), bdLocation.getLongitude());
            OverlayOptions dotOption = new DotOptions().center(point).color(0xAAFF0000);
            mBaiduMap.addOverlay(dotOption);
        }

        @Override
        public void onLocDiagnosticMessage(int i, int i1, String s) {
            super.onLocDiagnosticMessage(i, i1, s);
            LogUtil.e(i + " - " + i1 + " - " + s);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPoiSearch.destroy();
        mClient.disableLocInForeground(true);
        mClient.unRegisterLocationListener(myLocationListener);
        mClient.stop();
    }
}
