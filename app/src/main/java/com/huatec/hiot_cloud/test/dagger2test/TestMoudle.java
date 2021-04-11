package com.huatec.hiot_cloud.test.dagger2test;

import dagger.Module;
import dagger.Provides;

@Module
public class TestMoudle {
    @Provides
    public ThirdObj getThirdObj() {
        return new ThirdObj();

    }
}
