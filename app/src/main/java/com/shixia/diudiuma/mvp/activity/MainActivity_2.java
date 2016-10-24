package com.shixia.diudiuma.mvp.activity;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shixia.diudiuma.R;
import com.shixia.diudiuma.adapter.MainPageAdapter;
import com.shixia.diudiuma.mvp.activity.base.BaseActivity;
import com.shixia.diudiuma.mvp.fragment.MainPageDefaultFragment;
import com.shixia.diudiuma.mvp.fragment.MainPageHallFragment;
import com.shixia.diudiuma.mvp.fragment.MainPageMsgFragment;
import com.shixia.diudiuma.mvp.fragment.MainPagePersonalFragment;
import com.shixia.diudiuma.mvp.iview.MainActivity_2IView;
import com.shixia.diudiuma.mvp.presenter.PresenterMain_2;
import com.shixia.diudiuma.mvp.presenter.base.BasePresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AmosShi on 2016/10/24.
 * Description:
 */

public class MainActivity_2 extends BaseActivity implements MainActivity_2IView {

    private LinearLayout llTab;
    private RelativeLayout rlPageMain;
    private ImageView imgTabMain;
    private TextView tvTabMain;
    private RelativeLayout rlPageHall;
    private ImageView imgTabHall;
    private TextView tvTabHall;
    private RelativeLayout rlPageMsg;
    private ImageView imgTabMsg;
    private TextView tvTabMsg;
    private RelativeLayout rlPagePersonalCenter;
    private ImageView imgTabCenter;
    private TextView tvTabCenter;
    private ViewPager vpMain;

    private PresenterMain_2 presenter;
    private List<Fragment> fragmentList = new ArrayList<>();

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_main_2);

//        StatusBarCompat.compatBarTranslucent(this);

        llTab = (LinearLayout) findViewById(R.id.ll_tab);
        //四个分页点击事件
        rlPageMain = (RelativeLayout) findViewById(R.id.rl_page_main);
        rlPageHall = (RelativeLayout) findViewById(R.id.rl_page_hall);
        rlPageMsg = (RelativeLayout) findViewById(R.id.rl_page_msg);
        rlPagePersonalCenter = (RelativeLayout) findViewById(R.id.rl_page_personal_center);

        imgTabMain = (ImageView) findViewById(R.id.img_tab_main);
        imgTabHall = (ImageView) findViewById(R.id.img_tab_hall);
        imgTabMsg = (ImageView) findViewById(R.id.img_tab_msg);
        imgTabCenter = (ImageView) findViewById(R.id.img_tab_center);

        tvTabMain = (TextView) findViewById(R.id.tv_tab_main);
        tvTabHall = (TextView) findViewById(R.id.tv_tab_hall);
        tvTabMsg = (TextView) findViewById(R.id.tv_tab_msg);
        tvTabCenter = (TextView) findViewById(R.id.tv_tab_center);
        //ViewPager
        vpMain = (ViewPager) findViewById(R.id.vp_main);

        fragmentList.add(new MainPageDefaultFragment());
        fragmentList.add(new MainPageHallFragment());
        fragmentList.add(new MainPageMsgFragment());
        fragmentList.add(new MainPagePersonalFragment());
        MainPageAdapter pageAdapter = new MainPageAdapter(getSupportFragmentManager(),fragmentList);
        vpMain.setAdapter(pageAdapter);
    }

    @Override
    protected void initListener() {
        //切换页面
        rlPageMain.setOnClickListener(v -> presenter.changeFragment(0));
        rlPageHall.setOnClickListener(v -> presenter.changeFragment(1));
        rlPageMsg.setOnClickListener(v -> presenter.changeFragment(2));
        rlPagePersonalCenter.setOnClickListener(v -> presenter.changeFragment(3));
    }

    @Override
    protected BasePresenter initPresenter() {
        presenter = new PresenterMain_2(this, this,vpMain);
        return presenter;
    }

    @Override
    protected void afterActivityOnCreate() {
        super.afterActivityOnCreate();

    }

    @Override
    public void onPageChanged(int position) {

    }
}
