package com.huatec.hiot_cloud.ui.main;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.huatec.hiot_cloud.utils.Constans;

public class MainPagerAdapter extends FragmentPagerAdapter {

    public MainPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        fragment = EquipmentFragment.newInstance();

        switch (position) {
            case Constans.MAIN_VIEWPAGER_INDEX_MESSAGE:
                //创建消息Frament todo
                fragment = MessageFragment.newInstance();
                break;
            case Constans.MAIN_VIEWPAGER_INDEX_EQUIPMENT:
                fragment = EquipmentFragment.newInstance();
                break;
            case Constans.MAIN_VIEWPAGER_INDEX_SCENE:
                fragment = SceneFragment.newInstance();
                break;
            case Constans.MAIN_VIEWPAGER_INDEX_MINE:
                fragment = MineFragment.newInstance();
                break;
            default:
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return Constans.Main_FRAGMENT_COUNT;
    }
}
