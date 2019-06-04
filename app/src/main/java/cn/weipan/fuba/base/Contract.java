package cn.weipan.fuba.base;

import android.content.Context;

import java.util.Map;

public class Contract {
    //接口回调
    public interface CallBack{
        void  SuccessData(Object data);
        void FailData(String e);
    }
    public interface Model{
        void PostData(Context context, String url, Map<String, String> map, Class clazz, CallBack callBack);
        void GetData(Context context, String url, Map<String, String> map, Class clazz, CallBack callBack);
        void DeleteData(Context context, String url, Map<String, String> map, Class clazz, CallBack callBack);
        void PutData(Context context, String url, Map<String, String> map, Class clazz, CallBack callBack);
        void imagePost(Context context, String url, Map<String, String> map, Class clazz, CallBack callBack);
    }
    public interface View{
        void getSuccess(Object data);
        void getFaile(String e);
    }
}
