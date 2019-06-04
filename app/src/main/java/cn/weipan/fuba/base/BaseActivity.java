package cn.weipan.fuba.base;

import android.Manifest;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;

import me.jessyan.autosize.internal.CustomAdapt;

public abstract class BaseActivity extends AppCompatActivity implements CustomAdapt{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(initLayout());
        initView();
        initData();
        stateNetWork();//调用动态权限
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
    }

    protected abstract void initView();//控件的处理
    protected abstract void initData();//数据操作
    protected abstract int initLayout();//初始化布局
    //动态注册权限
    private void stateNetWork() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            String[] mStatenetwork = new String[]{
                    //写的权限
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    //读的权限
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    //入网权限
                    Manifest.permission.ACCESS_NETWORK_STATE,
                    //WIFI权限
                    Manifest.permission.ACCESS_WIFI_STATE,
                    //读手机权限
                    Manifest.permission.READ_PHONE_STATE,
                    //网络权限
                    Manifest.permission.INTERNET,
                    //相机
                    Manifest.permission.CAMERA,
                    Manifest.permission.WRITE_APN_SETTINGS,
                    Manifest.permission.ACCESS_NETWORK_STATE,
            };
            ActivityCompat.requestPermissions(this, mStatenetwork, 100);
        }
    }
}
