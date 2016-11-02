package com.shixia.diudiuma.mvp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.shixia.diudiuma.R;
import com.shixia.diudiuma.adapter.HallGoodsAdapter;
import com.shixia.diudiuma.bmob.bean.LoserGoodsInfo;
import com.shixia.diudiuma.mvp.fragment.base.BaseFragment;
import com.shixia.diudiuma.mvp.iview.HallIView;
import com.shixia.diudiuma.mvp.presenter.PresenterHall;
import com.shixia.diudiuma.mvp.presenter.base.BasePresenter;
import com.shixia.diudiuma.view.CToast;

import java.util.ArrayList;

/**
 * Created by AmosShi on 2016/10/24.
 * Description:
 */

public class MainPageHallFragment extends BaseFragment implements HallIView, SwipeRefreshLayout.OnRefreshListener {

    private PresenterHall presenter;

    private SwipeRefreshLayout swipeRefreshLayout;
    private RelativeLayout rlFindLoser;
    private RelativeLayout rlFindCard;
    private RelativeLayout rlFindGoods;
    private RelativeLayout rlFindCertification;
    private RelativeLayout rlFindPeople;
    private RelativeLayout rlHighReward;
    private RecyclerView rvHall;

    private HallGoodsAdapter hallGoodsAdapter;

    private ArrayList<LoserGoodsInfo> goodsList = new ArrayList<>();

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_page_hall_layout, container, false);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.srl_hall_refresh);
        rvHall = (RecyclerView) view.findViewById(R.id.rv_hall);
        rvHall.setLayoutManager(new LinearLayoutManager(getActivity()));
        initAdapter();
        return view;
    }

    @Override
    public BasePresenter initPresenter() {
        presenter = new PresenterHall(getActivity(), this);
        return presenter;
    }

    @Override
    public void initListener() {
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void afterFragmentCreated() {
        super.afterFragmentCreated();
        swipeRefreshLayout.setRefreshing(true);
        presenter.initData();
    }

    private void initAdapter() {
        hallGoodsAdapter = new HallGoodsAdapter(R.layout.recy_hall_goods_item, goodsList);
        rvHall.setAdapter(hallGoodsAdapter);
        addHeaderView();
    }

    /**
     * 为列表添加Header
     */
    private void addHeaderView() {
        View view = getActivity().getLayoutInflater().inflate(R.layout.view_hall_recy_header_view, (ViewGroup) rvHall.getParent(), false);
        rlFindLoser = (RelativeLayout) view.findViewById(R.id.rl_find_loser);
        rlFindCard = (RelativeLayout) view.findViewById(R.id.rl_find_card);
        rlFindGoods = (RelativeLayout) view.findViewById(R.id.rl_find_goods);
        rlFindCertification = (RelativeLayout) view.findViewById(R.id.rl_find_certification);
        rlFindPeople = (RelativeLayout) view.findViewById(R.id.rl_find_people);
        rlHighReward = (RelativeLayout) view.findViewById(R.id.rl_high_reward);
        rlFindCard.setOnClickListener(v -> CToast.makeCText(getActivity(), "card", Toast.LENGTH_SHORT).show());
        hallGoodsAdapter.addHeaderView(view);
        initHeaderListener();
    }

    /**
     * 初始化监听
     */
    private void initHeaderListener() {
        rlFindLoser.setOnClickListener(v -> presenter.searchByCondition(0));
        rlFindCard.setOnClickListener(v -> presenter.searchByCondition(1));
        rlFindGoods.setOnClickListener(v -> presenter.searchByCondition(2));
        rlFindCertification.setOnClickListener(v -> presenter.searchByCondition(3));
        rlFindPeople.setOnClickListener(v -> presenter.searchByCondition(4));
        rlHighReward.setOnClickListener(v -> presenter.searchByCondition(5));
        rlFindCard.setOnClickListener(v -> presenter.searchByCondition(6));
    }

    @Override
    public void onShowRemind(String s) {
        CToast.makeCText(getActivity(), s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNotifyDataSetChange(ArrayList<LoserGoodsInfo> loserGoodsInfosList) {
        goodsList.clear();
        this.goodsList.addAll(loserGoodsInfosList);
        hallGoodsAdapter.notifyDataSetChanged();
    }

    @Override
    public void onRefreshEnd() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        presenter.initData();
    }
}
