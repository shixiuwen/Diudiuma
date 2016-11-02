package com.shixia.diudiuma.mvp.presenter;

import android.content.Context;

import com.shixia.diudiuma.bmob.bean.LoserGoodsInfo;
import com.shixia.diudiuma.mvp.iview.HallIView;
import com.shixia.diudiuma.mvp.iview.base.BaseIView;
import com.shixia.diudiuma.mvp.presenter.base.BasePresenter;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by AmosShi on 2016/11/1.
 * Description:
 */

public class PresenterHall extends BasePresenter {

    private Context context;
    private HallIView iView;
    private ArrayList<LoserGoodsInfo> dataList = new ArrayList<>();;

    public PresenterHall(Context context, BaseIView iView) {
        super(context, iView);
        this.context = context;
        this.iView = (HallIView) iView;
    }

    /**
     * 初始化数据
     */
    public void initData(){
        dataList.clear();
        BmobQuery<LoserGoodsInfo> query = new BmobQuery<>();
        query.addWhereNotEqualTo("type",3);     //主动注册物品是防止丢失的，不显示在失物大厅，只在扫描丢丢码查询的时候能够查询到
        query.order("-createdAt");   //按照创建时间查询（负号表示反向），保证最新发布的在最前面
        query.findObjects(new FindListener<LoserGoodsInfo>() {
            @Override
            public void done(List<LoserGoodsInfo> list, BmobException e) {
                if (e == null) {
                    dataList = (ArrayList<LoserGoodsInfo>) list;
                    iView.onShowRemind("查询成功^_^");
                    iView.onNotifyDataSetChange(dataList);
                    iView.onRefreshEnd();
                } else {
                    iView.onShowRemind("查询失败^+^" + e.getMessage());
                    iView.onRefreshEnd();
                }
            }
        });

    }

    public ArrayList<LoserGoodsInfo> searchByCondition(int type){
        switch (type){
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
        }
        return null;
    }

}
