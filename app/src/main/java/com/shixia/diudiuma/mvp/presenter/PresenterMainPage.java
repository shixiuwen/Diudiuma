package com.shixia.diudiuma.mvp.presenter;

import android.content.Context;

import com.shixia.diudiuma.bean.MultiItemEntity;
import com.shixia.diudiuma.bmob.bean.DDMGoods;
import com.shixia.diudiuma.bmob.bean.DDMGoodsLever0Item;
import com.shixia.diudiuma.bmob.bean.DDMGoodsLever1Item;
import com.shixia.diudiuma.common_utils.L;
import com.shixia.diudiuma.mvp.activity.QuickAddGoodsActivity;
import com.shixia.diudiuma.mvp.activity.QuickFindGoodsActivity;
import com.shixia.diudiuma.mvp.activity.QuickFindLoserActivity;
import com.shixia.diudiuma.mvp.activity.QuickScanGoodsActivity;
import com.shixia.diudiuma.mvp.activity.base.BaseActivity;
import com.shixia.diudiuma.mvp.iview.DefaultFragmentIView;
import com.shixia.diudiuma.mvp.iview.base.BaseIView;
import com.shixia.diudiuma.mvp.presenter.base.BasePresenter;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by AmosShi on 2016/10/25.
 * Description:
 */

public class PresenterMainPage extends BasePresenter {

    private Context context;
    private DefaultFragmentIView iView;

    private ArrayList<MultiItemEntity> lever01List = new ArrayList<MultiItemEntity>();

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

    public void initData() {
        lever01List.clear();    //每次初始化数据都需要将缓存的数据清空
        BmobQuery<DDMGoods> query = new BmobQuery<DDMGoods>();
        query.findObjects(new FindListener<DDMGoods>() {
            @Override
            public void done(List<DDMGoods> list, BmobException e) {
                if (e == null) {
                    iView.onShowRemind("查询成功");
                    //查询成功
                    for (int i = 0; i < list.size(); i++) {
                        DDMGoods ddmGoods = list.get(i);
                        DDMGoodsLever0Item ddmGoodsLever0Item = new DDMGoodsLever0Item(ddmGoods.getDdmGoodsName(), ddmGoods.getUpdatedAt().toString(), ddmGoods.getDdmGoodsReward(), ddmGoods.getDdmGoodsTag(), ddmGoods.getPic());
                        DDMGoodsLever1Item ddmGoodsLever1Item = new DDMGoodsLever1Item(ddmGoods.getDdmGoodsAddress(), ddmGoods.getDdmCode(),
                                ddmGoods.getCard(), ddmGoods.getCertificate(),
                                ddmGoods.getTel(), ddmGoods.getWechat(),
                                ddmGoods.getQq(), ddmGoods.getDescribe());
                        ddmGoodsLever0Item.addSubItem(ddmGoodsLever1Item);
                        lever01List.add(ddmGoodsLever0Item);
                    }
                    iView.onNotifyDataSetChange(lever01List);
                    iView.onRefreshEnd();
                } else {
                    //查询失败
                    L.i(e.getMessage() + " " + e.getErrorCode());
                    iView.onRefreshEnd();
                    iView.onShowRemind("查询失败");
                }
            }
        });
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
                    if(BmobUser.getCurrentUser() == null){
                        iView.onShowRemind("请先登录才能注册添加物品");
                        break;
                    }
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

    /**
     * 通过是否登录决定要显示的界面
     */
    public void isShowEmptyView() {
        if (BmobUser.getCurrentUser() == null) {    //未登录
            iView.onShowEmptyView();
        } else {                                    //已登录
            initData();
        }
    }
}
