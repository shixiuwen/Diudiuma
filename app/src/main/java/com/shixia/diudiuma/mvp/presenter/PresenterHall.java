package com.shixia.diudiuma.mvp.presenter;

import android.content.Context;

import com.shixia.diudiuma.bmob.bean.LoserGoodsInfo;
import com.shixia.diudiuma.common_utils.L;
import com.shixia.diudiuma.mvp.iview.HallIView;
import com.shixia.diudiuma.mvp.iview.base.BaseIView;
import com.shixia.diudiuma.mvp.presenter.base.BasePresenter;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by AmosShi on 2016/11/1.
 * Description:
 */

public class PresenterHall extends BasePresenter {

    private Context context;
    private HallIView iView;
    private ArrayList<LoserGoodsInfo> dataList = new ArrayList<>();
    ;

    public PresenterHall(Context context, BaseIView iView) {
        super(context, iView);
        this.context = context;
        this.iView = (HallIView) iView;
    }

    /**
     * 初始化数据
     */
    public void initData() {
        dataList.clear();
        BmobQuery<LoserGoodsInfo> query = new BmobQuery<>();
        query.addWhereNotEqualTo("type", 3);     //主动注册物品是防止丢失的，不显示在失物大厅，只在扫描丢丢码查询的时候能够查询到
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

    public ArrayList<LoserGoodsInfo> searchByCondition(int type) {
        BmobQuery<LoserGoodsInfo> query = new BmobQuery<LoserGoodsInfo>();
        switch (type) {
            case 0:
                query.addWhereEqualTo("type",2);
                queryGoodsByCondition(query);
                break;
            case 1:
                query.addWhereEqualTo("isCard",true);
                queryGoodsByCondition(query);
                break;
            case 2:
                query.addWhereEqualTo("type",1);
                queryGoodsByCondition(query);
                break;
            case 3:
                query.addWhereEqualTo("isCredit",true);
                queryGoodsByCondition(query);
                break;
            case 4:
                iView.onShowRemind("该功能暂未开放，敬请期待^_^");
                break;
            case 5:
                query.addWhereGreaterThan("reward",100f);
                queryGoodsByCondition(query);
                break;
        }
        return null;
    }

    /**
     * 根据条件查找物品
     */
    private void queryGoodsByCondition(BmobQuery<LoserGoodsInfo> query) {
        iView.onSearchBegin();
        Observable.create(new Observable.OnSubscribe<List<LoserGoodsInfo>>() {
            @Override
            public void call(Subscriber<? super List<LoserGoodsInfo>> subscriber) {
                query.findObjects(new FindListener<LoserGoodsInfo>() {
                    @Override
                    public void done(List<LoserGoodsInfo> list, BmobException e) {
                        if (e == null) {
                            subscriber.onNext(list);
                        } else {
                            L.i("error", e.getMessage());
                            subscriber.onNext(null);
                        }
                        iView.onSearchEnd();
                    }
                });
            }
        })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(loserGoodsInfos -> {
                    iView.onNotifyDataSetChange(loserGoodsInfos);
                });
    }

}
