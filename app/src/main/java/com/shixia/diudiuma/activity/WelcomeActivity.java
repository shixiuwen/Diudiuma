package com.shixia.diudiuma.activity;

import android.support.v4.view.ViewPager;

import com.shixia.diudiuma.R;
import com.shixia.diudiuma.activity.base.BaseActivity;
import com.shixia.diudiuma.adapter.WelcomePageAdapter;
import com.shixia.diudiuma.fragment.WelcomePageOneFragment;
import com.shixia.diudiuma.fragment.WelcomePageThreeFragment;
import com.shixia.diudiuma.fragment.WelcomePageTwoFragment;
import com.shixia.diudiuma.fragment.base.BaseFragment;
import com.shixia.diudiuma.presenter.base.BasePresenter;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by AmosShi on 2016/10/11.
 * Description:
 */

public class WelcomeActivity extends BaseActivity {

    private ViewPager vpPage;

    private List<BaseFragment> viewList = new ArrayList<>();

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_welcome);
        vpPage = (ViewPager) findViewById(R.id.vp_welcome);
        viewList.add(new WelcomePageOneFragment());
        viewList.add(new WelcomePageTwoFragment());
        viewList.add(new WelcomePageThreeFragment());
        vpPage.setAdapter(new WelcomePageAdapter(getSupportFragmentManager(),viewList));
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void afterActivityOnCreate() {

    }
}
