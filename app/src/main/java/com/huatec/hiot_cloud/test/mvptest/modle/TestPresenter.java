package com.huatec.hiot_cloud.test.mvptest.modle;

import com.huatec.hiot_cloud.Base.BasePresenter;
import com.huatec.hiot_cloud.test.dagger2test.ThirdObj;

import javax.inject.Inject;

public class TestPresenter extends BasePresenter<TestView> {

    @Inject
    ThirdObj thirdObj;

    @Inject
    public TestPresenter() {

    }

    public void login(User user) {
        thirdObj.thirdAction();
        if ("pan81".equals(user.getUserName()) && "123".equals(user.getPassword())) {
            getView().showMessage("登入成功");

        } else {
            getView().showMessage("登入失败");
        }
    }

}
