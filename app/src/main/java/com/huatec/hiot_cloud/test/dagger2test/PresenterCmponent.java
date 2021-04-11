package com.huatec.hiot_cloud.test.dagger2test;

import com.huatec.hiot_cloud.test.mvptest.modle.TestMVPActivity;

import dagger.Component;

@Component(modules = TestMoudle.class)
public interface PresenterCmponent {
    void inject(TestMVPActivity testMVPActivity);
}
