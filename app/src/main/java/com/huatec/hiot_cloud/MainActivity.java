package com.huatec.hiot_cloud;

import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.RadioGroup;

import com.huatec.hiot_cloud.ui.Base.BaseActivity;
import com.huatec.hiot_cloud.ui.Base.BasePresenter;
import com.huatec.hiot_cloud.ui.main.MainPagerAdapter;
import com.huatec.hiot_cloud.utils.Constans;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewPager vpMain = findViewById(R.id.vp_main);
        vpMain.setAdapter(new MainPagerAdapter(getSupportFragmentManager(),
                FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT));
        vpMain.setOffscreenPageLimit(Constans.Main_FRAGMENT_COUNT);


        RadioGroup rgMain = findViewById(R.id.rg_main);
        rgMain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_message:
                        vpMain.setCurrentItem(Constans.MAIN_VIEWPAGER_INDEX_MESSAGE);
                        break;
                    case R.id.rb_equipment:
                        vpMain.setCurrentItem(Constans.MAIN_VIEWPAGER_INDEX_EQUIPMENT);
                        break;
                    case R.id.rb_scene:
                        vpMain.setCurrentItem(Constans.MAIN_VIEWPAGER_INDEX_SCENE);
                        break;
                    case R.id.rb_mine:
                        vpMain.setCurrentItem(Constans.MAIN_VIEWPAGER_INDEX_MINE);
                        break;
                    default:
                }
            }
        });


    }

    @Override
    public BasePresenter createPresenter() {
        return null;
    }

    @Override
    public void injectIndependies() {
        getActivityComponent().inject(this);

    }
}