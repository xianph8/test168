

## 高德定位服务
未编辑完。。。


```java
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import cn.pos.bean.LocationEvent;
import cn.pos.uitls.LogUtils;


/**
 * 高德定位服务
 * compile files('libs/AMap_Location_V2.4.1_20160414.jar')
 * <p>
 * 使用方法：
 *
 * 1.启动
 * <p>      Intent startLocationServiceIntent = new Intent(this,LocationService.class);
 * <p>      startService(startLocationServiceIntent);
 *
 * 2.停止
 * <p>      Intent stopLocationServiceIntent = new Intent(this, LocationService.class);
 * <p>      stopService(stopLocationServiceIntent);
 * <p>
 * <p>      LocationService.startLocation(mContext);
 * <p>      LocationService.stopLocation(mContext);
 */
public class LocationService extends Service implements AMapLocationListener {

    private Context mContext;

    public LocationService() {
    }

    public static void startLocation(Context mContext) {
        Intent startLocationServiceIntent = new Intent(mContext, LocationService.class);
        mContext.startService(startLocationServiceIntent);
    }

    public static void stopLocation(Context mContext) {
        Intent stopLocationServiceIntent = new Intent(mContext, LocationService.class);
        mContext.stopService(stopLocationServiceIntent);
    }

    @Override
    public IBinder onBind(Intent intent) {
//        // TODO: Return the communication channel to the service.
//        throw new UnsupportedOperationException("Not yet implemented");
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        startLocation();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopLocation();
    }

    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;

    /**
     * 初始化定位
     */
    private void startLocation() {
        if (mLocationClient == null) {
            //初始化定位
            mLocationClient = new AMapLocationClient(getApplicationContext());
            //设置定位回调监听
            mLocationClient.setLocationListener(this);
            ////////////////////////初始化定位参数//////////////////////////
            //声明mLocationOption对象
            AMapLocationClientOption mLocationOption = new AMapLocationClientOption();
            //设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
            mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
            //设置是否返回地址信息（默认返回地址信息）
            mLocationOption.setNeedAddress(true);
            //设置是否只定位一次,默认为false
            mLocationOption.setOnceLocation(true);
            //设置是否强制刷新WIFI，默认为强制刷新
            mLocationOption.setWifiActiveScan(true);
            //设置是否允许模拟位置,默认为false，不允许模拟位置
            mLocationOption.setMockEnable(false);
            //设置定位间隔,单位毫秒,默认为2000ms
            mLocationOption.setInterval(1000);
            //获取高精度模式下单次定位是否优先返回GPS定位信息,默认值：false
            mLocationOption.setGpsFirst(false);
            //给定位客户端对象设置定位参数
            mLocationClient.setLocationOption(mLocationOption);
            //启动定位
            mLocationClient.startLocation();
        }
    }

    private void stopLocation() {
        if (mLocationClient != null) {
            mLocationClient.stopLocation();//停止定位
            mLocationClient.onDestroy();//销毁定位客户端。
            mLocationClient = null;
        }
    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (aMapLocation != null && aMapLocation.getErrorCode() == AMapLocation.LOCATION_SUCCESS) {
            LogUtils.i("amapLocation : " + aMapLocation);
            //定位成功回调信息，设置相关消息
            aMapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
            aMapLocation.getLatitude();//获取纬度
            aMapLocation.getLongitude();//获取经度
            aMapLocation.getAccuracy();//获取精度信息
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
            Date date = new Date(aMapLocation.getTime());
            df.format(date);//定位时间
            aMapLocation.getAddress();//地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
            aMapLocation.getCountry();//国家信息
            aMapLocation.getProvince();//省信息
            aMapLocation.getCity();//城市信息
            aMapLocation.getDistrict();//城区信息
            aMapLocation.getStreet();//街道信息
            aMapLocation.getStreetNum();//街道门牌号信息
            aMapLocation.getCityCode();//城市编码
            aMapLocation.getAdCode();//地区编码
//                amapLocation.getAOIName();//获取当前定位点的AOI信息
            LogUtils.i("amapLocation : " + aMapLocation.getCity());
            locationSuccess(aMapLocation);
        } else {
            locationFailure(aMapLocation);
        }
        //定位完后，无论成功与否，需要的操作：
        stopLocation();
    }


    private void locationFailure(AMapLocation amapLocation) {
        //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
        LogUtils.e("AmapError", "Location Error, ErrCode:"
                + amapLocation.getErrorCode() + ", errInfo:"
                + amapLocation.getErrorInfo());

        LocationEvent locationEvent = new LocationEvent();
        locationEvent.setSuccess(false);
        locationEvent.setaMapLocation(amapLocation);
        EventBus.getDefault().post(locationEvent);

    }

    private void locationSuccess(AMapLocation amapLocation) {
        LogUtils.d("Success Location : " + amapLocation);
        LocationEvent locationEvent = new LocationEvent();
        locationEvent.setSuccess(true);
        locationEvent.setaMapLocation(amapLocation);
        EventBus.getDefault().post(locationEvent);
    }
}

```