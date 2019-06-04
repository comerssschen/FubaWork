package cn.weipan.fuba.myapp;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.StrictMode;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.lzy.okgo.OkGo;
import com.tencent.bugly.crashreport.CrashReport;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;

import cn.jpush.android.api.JPushInterface;
import cn.weipan.fuba.utils.GlobalConfig;

public class MyApplication extends Application {
    public static Context context;
    public static RequestQueue volleyQueue;
    public static final String URLZHM = "http://manager.payweipan.com";

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        volleyQueue = Volley.newRequestQueue(getApplicationContext());
        GlobalConfig.setContext(getApplicationContext());
        OkGo.getInstance().init(this);
        CrashReport.initCrashReport(getApplicationContext(), "9641d6c525", false);//上线时改成false
        ZXingLibrary.initDisplayOpinion(this);
        JPushInterface.setDebugMode(true);// 设置开启日志,发布时请关闭日志
        JPushInterface.init(this);// 初始化 JPush
        Fresco.initialize(this);
        context=getApplicationContext();
        //android 7.0调用相机闪退问题
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder.build());
        }
    }
    public static Context getApplication() {
        return context;
    }
}
