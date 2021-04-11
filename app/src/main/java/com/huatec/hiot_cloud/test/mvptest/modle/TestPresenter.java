package com.huatec.hiot_cloud.test.mvptest.modle;

import com.huatec.hiot_cloud.Base.BasePresenter;

public class TestPresenter extends BasePresenter<TestView> {

    public TestPresenter() {

    }

    public void login(User user) {
        if ("pan81".equals(user.getUserName()) && "123".equals(user.getPassword())) {
            getView().showMessage("登入成功");

        } else {
            getView().showMessage("登入失败");
        }
    }

}
