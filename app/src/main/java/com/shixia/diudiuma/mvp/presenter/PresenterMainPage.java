package com.shixia.diudiuma.mvp.presenter;

import android.content.Context;

import com.shixia.diudiuma.mvp.activity.QuickAddGoodsActivity;
import com.shixia.diudiuma.mvp.activity.QuickFindGoodsActivity;
import com.shixia.diudiuma.mvp.activity.QuickFindLoserActivity;
import com.shixia.diudiuma.mvp.activity.QuickScanGoodsActivity;
import com.shixia.diudiuma.mvp.activity.base.BaseActivity;
import com.shixia.diudiuma.mvp.iview.DefaultFragmentIView;
import com.shixia.diudiuma.mvp.iview.base.BaseIView;
import com.shixia.diudiuma.mvp.presenter.base.BasePresenter;

/**
 * Created by AmosShi on 2016/10/25.
 * Description:
 */

public class PresenterMainPage extends BasePresenter {

    private Context context;
    private DefaultFragmentIView iView;

    public PresenterMainPage(Context context, BaseIView iView) {
        super(context, iView);
        this.context = context;
        this.iView = (DefaultFragmentIView) iView;
    }

    /**
     * 弹出快速添加页面
     */
    public void showQuickOptWindow() {
        iView.onShowQuickOptWindow();
    }

    public void initData(){

    }

    /**
     * 打开添加物品界面
     */
    public void toQuickPage(int witchPage) {
        Class c = null;
        if (context instanceof BaseActivity) {
            switch (witchPage) {
                case 0:
                    c = QuickFindGoodsActivity.class;
                    break;
                case 1:
                    c = QuickFindLoserActivity.class;
                    break;
                case 2:
                    c = QuickAddGoodsActivity.class;
                    break;
                case 3:
                    c = QuickScanGoodsActivity.class;
                    break;
                default:
                    break;
            }
            if (c != null) {
                ((BaseActivity) context).startActivity(context, c, null, false);
                iView.onQuickOptWindowDismiss();
            }
        }
    }
}
