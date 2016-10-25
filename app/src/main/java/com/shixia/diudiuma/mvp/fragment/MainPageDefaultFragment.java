package com.shixia.diudiuma.mvp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.jlcf.lib_adapter.base.BaseQuickAdapter;
import com.shixia.diudiuma.R;
import com.shixia.diudiuma.adapter.MainPageGoodsAdapter;
import com.shixia.diudiuma.bean.MyGoods;
import com.shixia.diudiuma.listener.RecyclerItemClickListener;
import com.shixia.diudiuma.mvp.fragment.base.BaseFragment;
import com.shixia.diudiuma.mvp.iview.DefaultFragmentIView;
import com.shixia.diudiuma.mvp.presenter.PresenterMainPage;
import com.shixia.diudiuma.mvp.presenter.base.BasePresenter;
import com.shixia.diudiuma.view.CToast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AmosShi on 2016/10/24.
 * Description:
 */

public class MainPageDefaultFragment extends BaseFragment implements DefaultFragmentIView, BaseQuickAdapter.RequestLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {

    private PresenterMainPage presenter;

    private List<MyGoods> myGoodsList = new ArrayList<>();

    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private MainPageGoodsAdapter mainPageGoodsAdapter;
    private ImageView imgQuick; //快速添加按钮
    private View contentView;

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        contentView = inflater.inflate(R.layout.fragment_main_page_main_layout, container, false);
        recyclerView = (RecyclerView) contentView.findViewById(R.id.rv_goods_list_recy);
        swipeRefreshLayout = (SwipeRefreshLayout) contentView.findViewById(R.id.srl_refresh_layout);

        imgQuick = (ImageView) contentView.findViewById(R.id.img_btn_quick);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        initData();
        return contentView;
    }

    //初始化假的数据
    private void initData() {
        MyGoods myGoods01 = new MyGoods("钥匙", "2016/09/07", "钥匙 黑色 两根", " ", 20.0f, "15620633843", "shixiuwen", "1987370591", "ddm_123455");
        MyGoods myGoods02 = new MyGoods("钥匙", "2016/09/07", "钥匙 黑色 两根", " ", 20.0f, "15620633843", "shixiuwen", "1987370591", "ddm_123455");
        MyGoods myGoods03 = new MyGoods("钥匙", "2016/09/07", "钥匙 黑色 两根", " ", 20.0f, "15620633843", "shixiuwen", "1987370591", "ddm_123455");
        MyGoods myGoods04 = new MyGoods("钥匙", "2016/09/07", "钥匙 黑色 两根", " ", 20.0f, "15620633843", "shixiuwen", "1987370591", "ddm_123455");
        MyGoods myGoods05 = new MyGoods("钥匙", "2016/09/07", "钥匙 黑色 两根", " ", 20.0f, "15620633843", "shixiuwen", "1987370591", "ddm_123455");
        MyGoods myGoods06 = new MyGoods("钥匙", "2016/09/07", "钥匙 黑色 两根", " ", 20.0f, "15620633843", "shixiuwen", "1987370591", "ddm_123455");
        MyGoods myGoods07 = new MyGoods("钥匙", "2016/09/07", "钥匙 黑色 两根", " ", 20.0f, "15620633843", "shixiuwen", "1987370591", "ddm_123455");
        MyGoods myGoods08 = new MyGoods("钥匙", "2016/09/07", "钥匙 黑色 两根", " ", 20.0f, "15620633843", "shixiuwen", "1987370591", "ddm_123455");
        MyGoods myGoods09 = new MyGoods("钥匙", "2016/09/07", "钥匙 黑色 两根", " ", 20.0f, "15620633843", "shixiuwen", "1987370591", "ddm_123455");
        MyGoods myGoods10 = new MyGoods("钥匙", "2016/09/07", "钥匙 黑色 两根", " ", 20.0f, "15620633843", "shixiuwen", "1987370591", "ddm_123455");
        MyGoods myGoods11 = new MyGoods("钥匙", "2016/09/07", "钥匙 黑色 两根", " ", 20.0f, "15620633843", "shixiuwen", "1987370591", "ddm_123455");
        MyGoods myGoods12 = new MyGoods("钥匙", "2016/09/07", "钥匙 黑色 两根", " ", 20.0f, "15620633843", "shixiuwen", "1987370591", "ddm_123455");

        myGoodsList.add(myGoods01);
        myGoodsList.add(myGoods02);
        myGoodsList.add(myGoods03);
        myGoodsList.add(myGoods04);
        myGoodsList.add(myGoods05);
        myGoodsList.add(myGoods06);
        myGoodsList.add(myGoods07);
        myGoodsList.add(myGoods08);
        myGoodsList.add(myGoods09);
        myGoodsList.add(myGoods10);
        myGoodsList.add(myGoods11);
        myGoodsList.add(myGoods12);

    }

    @Override
    public BasePresenter initPresenter() {
        presenter = new PresenterMainPage(getActivity(), this);
        return presenter;
    }

    @Override
    public void afterFragmentCreated() {
        super.afterFragmentCreated();
        initAdapter();
        addHeaderView();
    }

    @Override
    public void initListener() {
        swipeRefreshLayout.setOnRefreshListener(this);
        imgQuick.setOnClickListener(v -> presenter.showQuickOptWindow());
    }

    /**
     * 初始化adapter
     */
    private void initAdapter() {
        mainPageGoodsAdapter = new MainPageGoodsAdapter(R.layout.recy_goods_item, myGoodsList);
        mainPageGoodsAdapter.openLoadAnimation();
        mainPageGoodsAdapter.openLoadMore(true);
        recyclerView.setAdapter(mainPageGoodsAdapter);
        mainPageGoodsAdapter.setOnLoadMoreListener(this);
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), (view, position) -> {
            CToast.makeCText(getActivity(), "" + position, Toast.LENGTH_SHORT).show();
        }));
    }

    /**
     * 为列表添加Header
     */
    private void addHeaderView() {
        View headView = getActivity().getLayoutInflater().inflate(R.layout.recy_goods_header_view, (ViewGroup) recyclerView.getParent(), false);
        mainPageGoodsAdapter.addHeaderView(headView);
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMoreRequested() {

    }

    @Override
    public void onShowQuickOptWindow() {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.view_pop_quick_opt_layout, null);
        PopupWindow popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.MATCH_PARENT);
        popupWindow.setFocusable(true);
        popupWindow.setAnimationStyle(R.style.login_pop_anim);
        popupWindow.showAtLocation(contentView, Gravity.CENTER,0,0);
    }
}
