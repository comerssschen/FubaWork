package cn.weipan.fuba.interfaceapi;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;
import rx.Observable;

public interface ApiService {
    //被观察者
    @GET
    Observable<ResponseBody> get(@Url String url, @QueryMap Map<String, String> map);

    @POST
    @FormUrlEncoded
    Observable<ResponseBody> post(@Url String url, @FieldMap Map<String, String> map);

    @PUT
    Observable<ResponseBody> put(@Url String url, @QueryMap Map<String, String> map);

    @DELETE
    Observable<ResponseBody> delete(@Url String url, @QueryMap Map<String, String> map);

    @POST
    Observable<ResponseBody> imagePost(@Url String url, @Body MultipartBody multipartBody);
}
