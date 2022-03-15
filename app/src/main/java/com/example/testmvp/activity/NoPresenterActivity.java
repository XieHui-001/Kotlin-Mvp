package com.example.testmvp.activity;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.example.testmvp.R;
import com.example.testmvp.base.BaseActivity;
import com.example.testmvp.base.BasePresenter;
import com.example.testmvp.base.BaseResponse;
import com.example.testmvp.base.BaseView;
import com.example.testmvp.entity.Login;
import com.example.testmvp.model.LoginModel;
import com.example.testmvp.progress.ObserverResponseListener;
import com.example.testmvp.utils.ExceptionHandle;
import com.example.testmvp.utils.ToastUtil;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class NoPresenterActivity extends BaseActivity {

    @BindView(R.id.check_msg_tv)
    TextView check_msg_tv;
    @BindView(R.id.check_btn)
    Button check_btn;

    private LoginModel model;

    @Override
    public int getLayoutId() {
        return R.layout.activity_no_presenter;
    }

    @Override
    public BasePresenter createPresenter() {
        return null;
    }

    @Override
    public BaseView createView() {
        return null;
    }

    @Override
    public void init() {
        model = new LoginModel();
    }


    @OnClick({R.id.check_msg_tv, R.id.check_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.check_msg_tv:
                break;
            case R.id.check_btn:
                check_msg_tv.setText("");
//                HashMap<String, String> map = new HashMap<>();
//                map.put("type", "yuantong");
//                map.put("postid", "11111111111");
//                getData(map, true, false);

                getData(true, false);
                break;
        }
    }

    private void getData(boolean isDialog, boolean cancelable) {
        model.getDelayChapters(this, isDialog, cancelable, this.bindToLifecycle(), new ObserverResponseListener() {
            @Override
            public void onNext(Object o) {
                //这一步是必须的，判断view是否已经被销毁
                if (this != null) {
                    //设置数据。。。
                    BaseResponse<List<Login>> response = (BaseResponse<List<Login>>) o;
                    if(response.getCode() == 0){
                        //请求成功
                        setData(response);
                    }else {
                        //"请求失败,errorCode: "+response.getCode()
                    }
                }
            }
            @Override
            public void onError(ExceptionHandle.ResponeThrowable e) {
                if (this != null) {
                    ToastUtil.showShortToast(ExceptionHandle.handleException(e).message);
                }
            }
        });
    }

    private void setData(BaseResponse<List<Login>> data) {
        check_msg_tv.setText(data.getData().toString());
    }
}
