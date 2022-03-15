package com.example.testmvp.presenter;

import android.content.Context;

import com.example.testmvp.base.BaseResponse;
import com.example.testmvp.contract.LoginContract;
import com.example.testmvp.entity.Banner;
import com.example.testmvp.entity.Login;
import com.example.testmvp.model.LoginModel;
import com.example.testmvp.progress.ObserverResponseListener;
import com.example.testmvp.utils.ExceptionHandle;
import com.example.testmvp.utils.ToastUtil;

import java.util.HashMap;
import java.util.List;

import retrofit2.http.GET;

/**
 * 作者：senon on 2017/12/27 10:34
 * 邮箱：a1083911695@163.com
 */
public class LoginPresenter extends LoginContract.Presenter {

    private LoginModel model;
    private Context context;

    public LoginPresenter(Context context) {
        this.model = new LoginModel();
        this.context = context;
    }

    @Override
    public void login(HashMap<String, String> map, boolean isDialog, boolean cancelable) {
        model.login(context, map, isDialog, cancelable, getView().bindLifecycle(),new ObserverResponseListener() {
            @Override
            public void onNext(Object o) {
                //这一步是必须的，判断view是否已经被销毁
                if(getView() != null){
                    getView().result((BaseResponse<List<Login>>) o);
                    getView().setMsg("请求成功");
                }
            }
            @Override
            public void onError(ExceptionHandle.ResponeThrowable e) {
                if(getView() != null){
                //// TODO: 2017/12/28 自定义处理异常
                ToastUtil.showShortToast(ExceptionHandle.handleException(e).message);
                }
            }
        });
    }

    @Override
    public void logout(HashMap<String, String> map, boolean isDialog, boolean cancelable) {
        model.logout(context, map, isDialog, cancelable, getView().bindLifecycle(),new ObserverResponseListener() {
            @Override
            public void onNext(Object o) {
                //这一步是必须的，判断view是否已经被销毁
                if(getView() != null){
                    getView().logoutResult((BaseResponse<List<Login>>) o);
                    getView().setMsg("请求成功");
                }
            }
            @Override
            public void onError(ExceptionHandle.ResponeThrowable e) {
                if(getView() != null){
                    ToastUtil.showShortToast(ExceptionHandle.handleException(e).message);
                }
            }
        });
    }

    @Override
    public void getChapters(boolean isDialog, boolean cancelable) {
        model.getChapters(context,isDialog, cancelable, getView().bindLifecycle(),new ObserverResponseListener() {
            @Override
            public void onNext(Object o) {
                //这一步是必须的，判断view是否已经被销毁
                if(getView() != null){
                    BaseResponse<List<Login>> response = (BaseResponse<List<Login>>) o;
                    if(response.getCode() == 0){
                        getView().getChaptersResult(response);
                        getView().setMsg("请求成功");
                    }else {
                        getView().setMsg("请求失败,errorCode: "+response.getCode());
                    }
                }
            }
            @Override
            public void onError(ExceptionHandle.ResponeThrowable e) {
                if(getView() != null){
                    ToastUtil.showShortToast(ExceptionHandle.handleException(e).message);
                }
            }
        });
    }

    @Override
    public void getBanner(boolean isDialog, boolean cancelable) {
        model.zipRequest(context,isDialog, cancelable, getView().bindLifecycle(),new ObserverResponseListener() {
            @Override
            public void onNext(Object o) {
                //这一步是必须的，判断view是否已经被销毁
                if(getView() != null){
                    getView().getBannerResult((BaseResponse<List<Banner>>) o);
                    getView().setMsg("合并请求成功");
                }
            }
            @Override
            public void onError(ExceptionHandle.ResponeThrowable e) {
                if(getView() != null){
                    ToastUtil.showShortToast(ExceptionHandle.handleException(e).message);
                }
            }
        });
    }

}
