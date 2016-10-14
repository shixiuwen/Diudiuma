package com.shixia.diudiuma.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shixia.diudiuma.R;
import com.shixia.diudiuma.fragment.base.BaseFragment;


/**
 * Created by AmosShi on 2016/10/12.
 * Description:
 */

public class WelcomePageTwoFragment extends BaseFragment {
    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_welcome_page_two,container,false);
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initListener() {

    }
}
