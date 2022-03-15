package com.example.testmvp.fragment;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.testmvp.R;
import com.example.testmvp.base.BaseFragment;
import com.example.testmvp.base.BaseResponse;
import com.example.testmvp.contract.LoginContract;
import com.example.testmvp.entity.Banner;
import com.example.testmvp.entity.Login;
import com.example.testmvp.presenter.LoginPresenter;
import com.example.testmvp.utils.ToastUtil;
import com.trello.rxlifecycle2.android.FragmentEvent;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.ObservableTransformer;

/**
 * 作者：senon on 2017/12/27 16:36
 * 邮箱：a1083911695@163.com
 */

public class LoginFragment extends BaseFragment<LoginContract.View, LoginContract.Presenter> implements LoginContract.View {

    @BindView(R.id.fragment_msg_tv)
    TextView fragment_msg_tv;
    @BindView(R.id.fragment_check_btn)
    Button fragment_check_btn;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_layout;
    }

    @Override
    public LoginContract.Presenter createPresenter() {
        return new LoginPresenter(mContext);
    }

    @Override
    public LoginContract.View createView() {
        return this;
    }

    @Override
    public void init() {

    }

    @Override
    public void result(BaseResponse<List<Login>> data) {
        fragment_msg_tv.setText(data.getData().toString());
    }

    @Override
    public void getChaptersResult(BaseResponse<List<Login>> data) {
        fragment_msg_tv.setText(data.getData().toString());
    }

    @Override
    public void getBannerResult(BaseResponse<List<Banner>> data) {

    }

    @Override
    public void logoutResult(BaseResponse data) {

    }

    @Override
    public void setMsg(String msg) {
        ToastUtil.showShortToast(msg);
    }

    @Override
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindUntilEvent(FragmentEvent.PAUSE);
    }

    @OnClick({R.id.fragment_msg_tv, R.id.fragment_check_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fragment_msg_tv:
                break;
            case R.id.fragment_check_btn:
                fragment_msg_tv.setText("");
//                HashMap<String,String> map = new HashMap<>();
//                map.put("type","yuantong");
//                map.put("postid","11111111111");
//                getPresenter().login(map,false,false);

                getPresenter().getChapters(false,false);
                break;
        }
    }
}
