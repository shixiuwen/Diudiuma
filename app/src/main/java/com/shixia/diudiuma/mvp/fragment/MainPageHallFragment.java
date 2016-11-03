package com.shixia.diudiuma.mvp.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.shixia.diudiuma.MyApplication;
import com.shixia.diudiuma.R;
import com.shixia.diudiuma.adapter.HallGoodsAdapter;
import com.shixia.diudiuma.bmob.bean.LoserGoodsInfo;
import com.shixia.diudiuma.common_utils.L;
import com.shixia.diudiuma.mvp.fragment.base.BaseFragment;
import com.shixia.diudiuma.mvp.iview.HallIView;
import com.shixia.diudiuma.mvp.presenter.PresenterHall;
import com.shixia.diudiuma.mvp.presenter.base.BasePresenter;
import com.shixia.diudiuma.view.CToast;
import com.shixia.diudiuma.view.LoadingDialog;

import java.util.ArrayList;
import java.util.List;

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
    private LinearLayout llSelectTitle;

    private TextView tvFindGoods;
    private TextView tvFindLoser;
    private TextView tvFindPeople;
    private TextView tvFindCard;
    private TextView tvFindCertification;
    private TextView tvFindHighReward;

    private HallGoodsAdapter hallGoodsAdapter;

    private ArrayList<LoserGoodsInfo> goodsList = new ArrayList<>();

    private float scrollY;

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_page_hall_layout, container, false);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.srl_hall_refresh);
        rvHall = (RecyclerView) view.findViewById(R.id.rv_hall);
        rvHall.setLayoutManager(new LinearLayoutManager(getActivity()));
        llSelectTitle = (LinearLayout) view.findViewById(R.id.ll_select_title);

        tvFindGoods = (TextView) view.findViewById(R.id.tv_find_goods);
        tvFindLoser = (TextView) view.findViewById(R.id.tv_find_loser);
        tvFindPeople = (TextView) view.findViewById(R.id.tv_find_people);
        tvFindCard = (TextView) view.findViewById(R.id.tv_find_card);
        tvFindCertification = (TextView) view.findViewById(R.id.tv_find_certification);
        tvFindHighReward = (TextView) view.findViewById(R.id.tv_find_high_reward);

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
        float recyHeaderHeight = getResources().getDimension(R.dimen.y600); //获取header高度
        swipeRefreshLayout.setOnRefreshListener(this);
        rvHall.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                scrollY += dy;
                if (scrollY >= recyHeaderHeight) {  //当颜色达到最深，不再变化
                    return;
                }
                L.i("scroll dy", scrollY + " ");
                llSelectTitle.setBackgroundColor(Color.argb((int) (scrollY / recyHeaderHeight * 255), 78, 191, 183));
                changeItemTitleColor(Color.argb((int) (scrollY / recyHeaderHeight * 255), 115, 115, 115));
            }
        });
    }

    private void changeItemTitleColor(int color) {
        tvFindGoods.setTextColor(color);
        tvFindLoser.setTextColor(color);
        tvFindPeople.setTextColor(color);
        tvFindCard.setTextColor(color);
        tvFindCertification.setTextColor(color);
        tvFindHighReward.setTextColor(color);
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

        tvFindLoser.setOnClickListener(v -> presenter.searchByCondition(0));
        tvFindCard.setOnClickListener(v -> presenter.searchByCondition(1));
        tvFindGoods.setOnClickListener(v -> presenter.searchByCondition(2));
        tvFindCertification.setOnClickListener(v -> presenter.searchByCondition(3));
        tvFindPeople.setOnClickListener(v -> presenter.searchByCondition(4));
        tvFindHighReward.setOnClickListener(v -> presenter.searchByCondition(5));
    }

    @Override
    public void onShowRemind(String s) {
        CToast.makeCText(getActivity(), s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSearchBegin() {
        MyApplication.UIHandler.post(() -> LoadingDialog.getInstance(getActivity(), "查询中，请稍后……").show());
    }

    @Override
    public void onSearchEnd() {
        MyApplication.UIHandler.post(() -> LoadingDialog.getInstance(getActivity(), "查询中，请稍后……").dismiss());
    }

    @Override
    public void onNotifyDataSetChange(List<LoserGoodsInfo> loserGoodsInfosList) {
        goodsList.clear();
        this.goodsList.addAll(loserGoodsInfosList);
//        hallGoodsAdapter.notifyDataSetChanged();
        rvHall.setAdapter(hallGoodsAdapter);
        rvHall.scrollBy(0,0);   //刷新后回到顶部
        scrollY = 0;
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
