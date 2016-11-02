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

import cn.bmob.v3.BmobUser;

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
        vpMain.setOffscreenPageLimit(2);    //防止预加载
        imgTabMain.setSelected(true);
    }

    @Override
    protected void initListener() {
        //切换页面
        rlPageMain.setOnClickListener(v -> presenter.changeFragment(0));
        rlPageHall.setOnClickListener(v -> presenter.changeFragment(1));
        rlPageMsg.setOnClickListener(v -> presenter.changeFragment(2));
        rlPagePersonalCenter.setOnClickListener(v -> presenter.changeFragment(3));

        vpMain.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        onPageChanged(0);
                        break;
                    case 1:
                        onPageChanged(1);
                        break;
                    case 2:
                        onPageChanged(2);
                        break;
                    case 3:
                        onPageChanged(3);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected BasePresenter initPresenter() {
        presenter = new PresenterMain_2(this, this,vpMain);
        return presenter;
    }

    @Override
    protected void afterActivityOnCreate() {
        super.afterActivityOnCreate();
        initPage();
    }

    /**
     * 初始化初始界面
     */
    private void initPage() {
        if(BmobUser.getCurrentUser() == null){      //如果用户未登录,默认显示第二页大厅
            presenter.changeFragment(1);
        }
    }

    @Override
    public void onPageChanged(int position) {
        switch (position){
            case 0:
                imgTabMain.setSelected(true);
                imgTabHall.setSelected(false);
                imgTabMsg.setSelected(false);
                imgTabCenter.setSelected(false);
                break;
            case 1:
                imgTabMain.setSelected(false);
                imgTabHall.setSelected(true);
                imgTabMsg.setSelected(false);
                imgTabCenter.setSelected(false);
                break;
            case 2:
                imgTabMain.setSelected(false);
                imgTabHall.setSelected(false);
                imgTabMsg.setSelected(true);
                imgTabCenter.setSelected(false);
                break;
            case 3:
                imgTabMain.setSelected(false);
                imgTabHall.setSelected(false);
                imgTabMsg.setSelected(false);
                imgTabCenter.setSelected(true);
                break;
        }
    }
}
