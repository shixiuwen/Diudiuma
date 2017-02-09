package com.shixia.diudiuma.mvp.activity;

import android.os.Bundle;
import android.text.TextUtils;

import com.bumptech.glide.Glide;
import com.kogitune.activity_transition.ActivityTransition;
import com.shixia.diudiuma.R;
import com.shixia.diudiuma.mvp.activity.base.BaseActivity;
import com.shixia.diudiuma.mvp.fragment.MainPagePersonalFragment;
import com.shixia.diudiuma.mvp.iview.PreviewPictureIView;
import com.shixia.diudiuma.mvp.presenter.PresenterPreviewPicture;
import com.shixia.diudiuma.mvp.presenter.base.BasePresenter;
import com.shixia.diudiuma.view.CircleImageView;

/**
 * Created by ShiXiuwen on 2017/2/9.
 * <p>
 * Description:用来预览图片的Activity
 */

public class PicturePreviewActivity extends BaseActivity implements PreviewPictureIView {

    private PresenterPreviewPicture presenter;
    private CircleImageView ivPreviewPic;
    private String bitmapUrl;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityTransition.with(getIntent()).to(ivPreviewPic).duration(500).start(savedInstanceState);
    }

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_picture_preview);
        ivPreviewPic = (CircleImageView) findViewById(R.id.iv_preview_pic);
        bitmapUrl = getIntent().getStringExtra("bitmapUrl");
        if (TextUtils.isEmpty(bitmapUrl)) {
            ivPreviewPic.setAvatar(MainPagePersonalFragment.bitmap);
        }else {
            Glide.with(this).load(bitmapUrl).dontAnimate().into(ivPreviewPic);
        }
        ivPreviewPic.setCircleNeeded(false);
    }

    @Override
    protected void initListener() {
        ivPreviewPic.setOnClickListener(v -> presenter.finishPreview());
    }

    @Override
    protected BasePresenter initPresenter() {
        presenter = new PresenterPreviewPicture(this, this);
        return presenter;
    }

    @Override
    public void onPreviewFinish() {
        this.finish();
    }
}
