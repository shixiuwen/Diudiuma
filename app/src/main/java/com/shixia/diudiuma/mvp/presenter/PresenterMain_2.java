package com.shixia.diudiuma.mvp.presenter;

import android.content.Context;
import android.support.v4.view.ViewPager;

import com.shixia.diudiuma.mvp.activity.MainActivity_2;
import com.shixia.diudiuma.mvp.iview.MainActivity_2IView;
import com.shixia.diudiuma.mvp.iview.base.BaseIView;
import com.shixia.diudiuma.mvp.presenter.base.BasePresenter;

/**
 * Created by AmosShi on 2016/10/24.
 * Description:
 */

public class PresenterMain_2 extends BasePresenter {

    private MainActivity_2 activity;
    private MainActivity_2IView iView;
    private ViewPager vpMain;

    public PresenterMain_2(Context context, BaseIView iView) {
        super(context, iView);
        this.activity = (MainActivity_2) context;
        this.iView = (MainActivity_2IView) iView;
    }

    public PresenterMain_2(Context context, BaseIView iView, ViewPager vpMain){
        this(context,iView);
        this.vpMain = vpMain;
    }

    /**
     * 切换到某一个Fragment页面
     * @param position 要切换到的页面
     */
    public void changeFragment(int position){
        switch (position){
            case 0:
                vpMain.setCurrentItem(0,true);
                break;
            case 1:
                vpMain.setCurrentItem(1,true);
                break;
            case 2:
                vpMain.setCurrentItem(2,true);
                break;
            case 3:
                vpMain.setCurrentItem(3,true);
                break;
            default:
                break;
        }
    }
}
