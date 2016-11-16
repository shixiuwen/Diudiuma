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
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.jlcf.lib_adapter.base.BaseQuickAdapter;
import com.shixia.diudiuma.R;
import com.shixia.diudiuma.adapter.ExpandableItemAdapter;
import com.shixia.diudiuma.bean.MultiItemEntity;
import com.shixia.diudiuma.common_utils.L;
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

    private List<MultiItemEntity> lever01List = new ArrayList<MultiItemEntity>();

    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    //    private MainPageGoodsAdapter mainPageGoodsAdapter;
    private ImageView imgQuick; //快速添加按钮
    private View contentView;

    //快速添加popWindow
    private LinearLayout popLl01;
    private LinearLayout popLl03;
    private LinearLayout popLl02;
    private LinearLayout popLl04;
    private PopupWindow popupWindow;
    private ExpandableItemAdapter expandableItemAdapter;

    private View emptyView; //当列表为空的时候显示的控件

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        contentView = inflater.inflate(R.layout.fragment_main_page_main_layout, container, false);
        recyclerView = (RecyclerView) contentView.findViewById(R.id.rv_goods_list_recy);
        swipeRefreshLayout = (SwipeRefreshLayout) contentView.findViewById(R.id.srl_refresh_layout);

        imgQuick = (ImageView) contentView.findViewById(R.id.img_btn_quick);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        swipeRefreshLayout.setRefreshing(true);
        return contentView;
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
        presenter.isShowEmptyView();
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
        expandableItemAdapter = new ExpandableItemAdapter(lever01List,getActivity());
        expandableItemAdapter.openLoadMore(5);
        expandableItemAdapter.openLoadAnimation();
        recyclerView.setAdapter(expandableItemAdapter);

        expandableItemAdapter.setOnLoadMoreListener(this);
    }

    /**
     * 为列表添加Header
     */
    private void addHeaderView() {
        View headView = getActivity().getLayoutInflater().inflate(R.layout.recy_goods_header_view, (ViewGroup) recyclerView.getParent(), false);
        expandableItemAdapter.addHeaderView(headView);
    }

    @Override
    public void onRefresh() {
        L.i("refresh", "refreshing");
        presenter.isShowEmptyView();
    }

    @Override
    public void onLoadMoreRequested() {
        // TODO: 2016/11/1  加载更多
    }

    @Override
    public void onShowEmptyView() {
        if (emptyView == null) {
            emptyView = LayoutInflater.from(getActivity()).inflate(R.layout.view_no_data_view, null);
        }
        lever01List.clear();
        expandableItemAdapter.setEmptyView(emptyView);
        expandableItemAdapter.notifyDataSetChanged();
    }

    @Override
    public void onShowRefresh() {

    }

    @Override
    public void onRefreshEnd() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onShowRemind(String s) {
        CToast.makeCText(getActivity(), s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNotifyDataSetChange(ArrayList<MultiItemEntity> goodsList) {
        lever01List.clear();
        lever01List.addAll(goodsList);
        expandableItemAdapter.notifyDataSetChanged();
    }


    /** ############################### 快速弹窗相关 #################################### */

    /**
     * 初始化快速添加popupWindow控件
     *
     * @param view popLayout
     */
    private void initPopWindowView(View view) {
        popLl01 = (LinearLayout) view.findViewById(R.id.pop_ll_01);
        popLl03 = (LinearLayout) view.findViewById(R.id.pop_ll_03);
        popLl02 = (LinearLayout) view.findViewById(R.id.pop_ll_02);
        popLl04 = (LinearLayout) view.findViewById(R.id.pop_ll_04);
    }

    /**
     * 初始化快速添加popupWindow控件监听事件
     */
    private void initPopupWindowViewListener() {
        popLl01.setOnClickListener(v -> presenter.toQuickPage(0));    //点击进入发布寻物启事界面
        popLl02.setOnClickListener(v -> presenter.toQuickPage(1));    //点击进入添加失物招领界面
        popLl03.setOnClickListener(v -> presenter.toQuickPage(2));    //点击进入添加物品界面
        popLl04.setOnClickListener(v -> presenter.toQuickPage(3));    //点击进入扫描二维码界面
    }

    @Override
    public void onShowQuickOptWindow() {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.view_pop_quick_opt_layout, null);
        popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.MATCH_PARENT);
        popupWindow.setFocusable(true);
        popupWindow.setAnimationStyle(R.style.login_pop_anim);
        popupWindow.showAtLocation(contentView, Gravity.CENTER, 0, 0);
        //初始化快速添加popupWindow控件
        initPopWindowView(view);
        //初始化快速添加popupWindow控件监听事件
        initPopupWindowViewListener();
    }

    @Override
    public void onQuickOptWindowDismiss() {
        if (popupWindow != null) {
            popupWindow.dismiss();
        }
    }


}
