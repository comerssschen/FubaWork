package cn.weipan.fuba.base;

import java.lang.ref.Reference;
import java.lang.ref.SoftReference;

public abstract class BasePresenter<T>{

    //view接口类型的软引用
    protected Reference<T> softReference;

    //绑定view
    public void attchView(T view){
        softReference = new SoftReference<>(view);
    }
    protected T getView(){
        return softReference.get();
    }
    public boolean isViewAtcttch(){
        return softReference!=null&&softReference.get()!=null;
    }
    /**
     * view 解绑
     */
    public void destoryView(){
        if (softReference!=null){
            softReference.clear();
        }
    }
}


