package com.shixia.diudiuma.mvp.activity;

import android.support.v4.view.ViewPager;

import com.shixia.diudiuma.R;
import com.shixia.diudiuma.mvp.activity.base.BaseActivity;
import com.shixia.diudiuma.adapter.WelcomePageAdapter;
import com.shixia.diudiuma.fragment.WelcomePageOneFragment;
import com.shixia.diudiuma.fragment.WelcomePageThreeFragment;
import com.shixia.diudiuma.fragment.WelcomePageTwoFragment;
import com.shixia.diudiuma.fragment.base.BaseFragment;
import com.shixia.diudiuma.mvp.presenter.base.BasePresenter;
import com.shixia.diudiuma.view.StatusBarCompat;

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
//        StatusBarCompat.compat(this);   //设置沉浸式状态栏颜色,在ViewPager中不要用这种方式，要用直接将状态栏变透明的方式
        StatusBarCompat.compatBarTranslucent(this);

        vpPage = (ViewPager) findViewById(R.id.vp_welcome);
        viewList.add(new WelcomePageOneFragment());
        viewList.add(new WelcomePageTwoFragment());
        viewList.add(new WelcomePageThreeFragment());
        vpPage.setAdapter(new WelcomePageAdapter(getSupportFragmentManager(),viewList));
    }

    @Override
    protected void initListener() {
        vpPage.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //测试沉浸式状态栏
//                if(position == 0){
//                    StatusBarCompat.changeStatusBarColor(WelcomeActivity.this,getResources().getColor(R.color.colorPrimary));
//                }else if(position == 1){
//                    StatusBarCompat.changeStatusBarColor(WelcomeActivity.this,getResources().getColor(R.color.colorPrimaryDark));
//                }else if(position == 2){
//                    StatusBarCompat.changeStatusBarColor(WelcomeActivity.this,getResources().getColor(R.color.__picker_pager_bg));
//                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void afterActivityOnCreate() {

    }
}
