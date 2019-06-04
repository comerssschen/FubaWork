package cn.weipan.fuba.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import cn.weipan.fuba.interfaceapi.ApiService;
import cn.weipan.fuba.interfaceapi.Constant;
import cn.weipan.fuba.myapp.MyApplication;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RetrofitUtils<T> {
    public static RetrofitUtils retrofitManager;

    public static synchronized RetrofitUtils getInstance(){
        if(retrofitManager==null){
            retrofitManager=new RetrofitUtils();
        }
        return retrofitManager;
    }
    private ApiService mBaseApis;
    private RetrofitUtils(){
        OkHttpClient builder = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request();
                        SharedPreferences preferences = MyApplication.getApplication().getSharedPreferences("User", Context.MODE_PRIVATE);
                        String userId = preferences.getString("userId", "");
                        String sessionId = preferences.getString("sessionId", "");
                        Request.Builder builder1 = request.newBuilder();
                        builder1.method(request.method(),request.body());
                        if(!TextUtils.isEmpty(userId)&&!TextUtils.isEmpty(sessionId)){
                            builder1.addHeader("userId",userId);
                            builder1.addHeader("sessionId",sessionId);
                        }
                        Request build = builder1.build();
                        return chain.proceed(build);
                    }
                })
                .readTimeout(10,TimeUnit.SECONDS)
                .writeTimeout(10,TimeUnit.SECONDS)
                .connectTimeout(10,TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(Constant.URL)
                .client(builder)
                .build();

        mBaseApis = retrofit.create(ApiService.class);
    }

    public void get(String url,Map<String, String> map,HttpListener listener){
        mBaseApis.get(url,map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .throttleFirst(10,  TimeUnit.MILLISECONDS)//在一秒内只取第一次点击
                .subscribe(getObserver(listener));
    }

    public void post(String dataUrl,Map<String,String> map,HttpListener listener){
        mBaseApis.post(dataUrl,map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .throttleFirst(10, TimeUnit.MILLISECONDS)//在一秒内只取第一次点击
                .subscribe(getObserver(listener));
    }

    public void put(String dataUrl,Map<String,String> map,HttpListener listener){
        if(map==null){
            map=new HashMap<>();
        }
        mBaseApis.put(dataUrl,map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .throttleFirst(10, TimeUnit.MILLISECONDS)//在一秒内只取第一次点击
                .subscribe(getObserver(listener));
    }



    public void delete(String quxiao, Map<String, String> map, HttpListener listener) {
        if(map==null){
            map=new HashMap<>();
        }
        mBaseApis.delete(quxiao,map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .throttleFirst(10, TimeUnit.MILLISECONDS)//在一秒内只取第一次点击
                .subscribe(getObserver(listener));
    }
    public void imagePost(String url, Map<String,String> map,HttpListener listener){
        if(map == null){
            map = new HashMap<>();
        }
        MultipartBody multipartBody = filesMutipar(map);
        mBaseApis.imagePost(url,multipartBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .throttleFirst(10, TimeUnit.MILLISECONDS)
                .subscribe(getObserver(listener));
    }


    public static MultipartBody filesMutipar(Map<String,String> map){
        MultipartBody.Builder builder = new MultipartBody.Builder();
        for (Map.Entry<String,String> entry:map.entrySet()){
            if (entry.getKey().equals("image")){
                File file = new File(entry.getValue());
                builder.addFormDataPart(entry.getKey(),"tp.png",RequestBody.create(MediaType.parse("multipart/form-data"),file));
            }
        }
        builder.setType(MultipartBody.FORM);
        MultipartBody multipartBody = builder.build();
        return multipartBody;
    }
    private Observer getObserver(final HttpListener listener) {
        Observer observer = new Observer<ResponseBody>() {

            //观察者
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                if (listener != null) {
                    listener.onFail(e.getMessage());
                }
            }

            @Override
            public void onNext(ResponseBody responseBody) {
                try {
                    String data = responseBody.string();
                    if (listener != null) {
                        listener.onSuccess(data);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    if (listener != null) {
                        listener.onFail(e.getMessage());
                    }
                }
            }
        };
        return observer;
    }
    public interface HttpListener {
        void onSuccess(String data);
        void onFail(String error);
    }
}
