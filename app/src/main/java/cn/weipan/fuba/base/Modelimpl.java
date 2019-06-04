package cn.weipan.fuba.base;

import android.content.Context;
import com.google.gson.Gson;
import java.util.Map;
import cn.weipan.fuba.utils.RetrofitUtils;
public class Modelimpl implements Contract.Model {
    @Override
    public void PostData(Context context, String url, Map<String, String> map, final Class clazz, final Contract.CallBack callBack) {
        RetrofitUtils.getInstance().post(url, map, new RetrofitUtils.HttpListener() {
            @Override
            public void onSuccess(String data) {
                Gson gson = new Gson();
                Object o = gson.fromJson(data, clazz);
                callBack.SuccessData(o);
            }

            @Override
            public void onFail(String error) {
                callBack.FailData(error);
            }
        });
    }

    @Override
    public void GetData(Context context, String url, Map<String, String> map, final Class clazz, final Contract.CallBack callBack) {
        RetrofitUtils.getInstance().get(url,map,new RetrofitUtils.HttpListener() {
            @Override
            public void onSuccess(String data) {
                Object o = new Gson().fromJson(data, clazz);
                callBack.SuccessData(o);
            }
            @Override
            public void onFail(String error) {
                callBack.FailData(error);
            }
        });
    }

    @Override
    public void DeleteData(Context context, String url, Map<String, String> map, final Class clazz, final Contract.CallBack callBack) {
        RetrofitUtils.getInstance().delete(url,map,new RetrofitUtils.HttpListener() {
            @Override
            public void onSuccess(String data) {
                Object o = new Gson().fromJson(data, clazz);
                callBack.SuccessData(o);
            }
            @Override
            public void onFail(String error) {
                callBack.FailData(error);
            }
        });
    }

    @Override
    public void PutData(Context context, String url, Map<String, String> map, final Class clazz, final Contract.CallBack callBack) {
        RetrofitUtils.getInstance().put(url, map, new RetrofitUtils.HttpListener() {
            @Override
            public void onSuccess(String data) {
                Object o = new Gson().fromJson(data, clazz);
                callBack.SuccessData(o);
            }

            @Override
            public void onFail(String error) {
                callBack.FailData(error);
            }
        });
    }

    @Override
    public void imagePost(Context context, String url, Map<String, String> map, final Class clazz, final Contract.CallBack callBack) {
        RetrofitUtils.getInstance().imagePost(url, map, new RetrofitUtils.HttpListener() {
            @Override
            public void onSuccess(String data) {
                Object o = new Gson().fromJson(data, clazz);
                callBack.SuccessData(o);
            }

            @Override
            public void onFail(String error) {
                callBack.FailData(error);
            }
        });
    }
}
