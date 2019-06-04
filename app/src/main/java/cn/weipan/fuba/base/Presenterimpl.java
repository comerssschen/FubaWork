package cn.weipan.fuba.base;

import android.content.Context;
import java.util.Map;
public class Presenterimpl extends BasePresenter<Contract.View> {

    private final Modelimpl    modelimpl;

    public Presenterimpl() {
        modelimpl = new Modelimpl();
    }

    /**
     * post
     * @param context
     * @param url
     * @param map
     * @param clazz
     */
    public void PostData(Context context, String url, Map<String,String> map, Class clazz){
        modelimpl.PostData(context, url, map, clazz, new Contract.CallBack() {
            @Override
            public void SuccessData(Object data) {
                getView().getSuccess(data);
            }

            @Override
            public void FailData(String e) {
                getView().getFaile(e);
            }
        });
    }

    /**
     * get
     * @param context
     * @param url
     * @param map
     * @param clazz
     */
    public void GetData(Context context, String url, Map<String,String> map,Class clazz){
        modelimpl.GetData(context, url, map, clazz, new Contract.CallBack() {
            @Override
            public void SuccessData(Object data) {
                getView().getSuccess(data);
            }

            @Override
            public void FailData(String e) {
                getView().getFaile(e);
            }
        });
    }

    /**
     * delete
     * @param context
     * @param url
     * @param map
     * @param clazz
     */
    public void DeleteData(Context context, String url, Map<String,String> map,Class clazz){
        modelimpl.DeleteData(context, url, map, clazz, new Contract.CallBack() {
            @Override
            public void SuccessData(Object data) {
                getView().getSuccess(data);
            }

            @Override
            public void FailData(String e) {
                getView().getFaile(e);
            }
        });
    }

    /**
     * put
     * @param context
     * @param url
     * @param map
     * @param clazz
     */
    public void PutData(Context context, String url, Map<String,String> map,Class clazz){
        modelimpl.PutData(context, url, map, clazz, new Contract.CallBack() {
            @Override
            public void SuccessData(Object data) {
                getView().getSuccess(data);
            }

            @Override
            public void FailData(String e) {
                getView().getFaile(e);
            }
        });
    }

    /**
     * image
     * @param context
     * @param url
     * @param map
     * @param clazz
     */
    public void imagePost(Context context, String url, Map<String, String> map, Class clazz){
        modelimpl.imagePost(context, url, map, clazz, new Contract.CallBack() {
            @Override
            public void SuccessData(Object data) {
                getView().getSuccess(data);
            }

            @Override
            public void FailData(String e) {
                getView().getFaile(e);
            }
        });
    }
}
