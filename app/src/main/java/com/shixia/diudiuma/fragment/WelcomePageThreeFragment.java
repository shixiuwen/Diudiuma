package com.shixia.diudiuma.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.shixia.diudiuma.R;
import com.shixia.diudiuma.mvp.activity.MainActivity;
import com.shixia.diudiuma.mvp.activity.base.BaseActivity;
import com.shixia.diudiuma.fragment.base.BaseFragment;

/**
 * Created by AmosShi on 2016/10/12.
 * Description:
 */

public class WelcomePageThreeFragment extends BaseFragment implements View.OnClickListener {

    private Context context;

    private Button btnToMainActivity;

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.context = getActivity();
        View view = inflater.inflate(R.layout.fragment_welcome_page_three, container, false);
        btnToMainActivity = (Button) view.findViewById(R.id.btn_to_main_activity);
        return view;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initListener() {
        btnToMainActivity.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_to_main_activity:
                if (context != null) {
                    ((BaseActivity) context).startActivity(context, MainActivity.class, null,true);
                }
                break;
        }
    }
}
