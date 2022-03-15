package com.example.testmvp.contract;


import com.example.testmvp.base.BasePresenter;
import com.example.testmvp.base.BaseResponse;
import com.example.testmvp.base.BaseView;
import com.example.testmvp.entity.Banner;
import com.example.testmvp.entity.Login;

import java.util.HashMap;
import java.util.List;

import io.reactivex.ObservableTransformer;

/**
 * 作者：senon on 2017/12/27 10:30
 * 邮箱：a1083911695@163.com
 * LoginContract  V 、P契约类
 */
public interface LoginContract {

    interface View extends BaseView {

        void result(BaseResponse<List<Login>> data);

        void logoutResult(BaseResponse<List<Login>> data);

        void getChaptersResult(BaseResponse<List<Login>> data);

        void getBannerResult(BaseResponse<List<Banner>> data);

        void setMsg(String msg);

        <T> ObservableTransformer<T, T> bindLifecycle();

    }

    abstract class Presenter extends BasePresenter<View> {

        //请求1
        public abstract void login(HashMap<String, String> map, boolean isDialog, boolean cancelable);

        //请求2
        public abstract void logout(HashMap<String, String> map, boolean isDialog, boolean cancelable);

        //请求3
        public abstract void getChapters(boolean isDialog, boolean cancelable);

        //请求4
        public abstract void getBanner(boolean isDialog, boolean cancelable);
    }
}
