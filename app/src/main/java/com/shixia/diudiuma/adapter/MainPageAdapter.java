package com.shixia.diudiuma.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by AmosShi on 2016/10/24.
 * Description:
 */

public class MainPageAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragmentList;

    public MainPageAdapter(FragmentManager fm,List<Fragment> fragmentList) {
        super(fm);
        this.fragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //解决ViewPager预加载问题
//        super.destroyItem(container, position, object);
    }
}
