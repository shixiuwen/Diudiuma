package com.shixia.diudiuma.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.shixia.diudiuma.fragment.base.BaseFragment;

import java.util.List;

/**
 * Created by AmosShi on 2016/10/12.
 * Description:
 */

public class WelcomePageAdapter extends FragmentPagerAdapter {

    private List<BaseFragment> fragmentList;

    public WelcomePageAdapter(FragmentManager fm, List<BaseFragment> fragmentList) {
        super(fm);
        this.fragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return (fragmentList == null || fragmentList.size() == 0) ? null : fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}
