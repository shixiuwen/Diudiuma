package com.shixia.diudiuma.activity;

import android.graphics.Bitmap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.shixia.diudiuma.R;
import com.shixia.diudiuma.activity.base.BaseActivity;
import com.shixia.diudiuma.iview.ZXingActivityIView;
import com.shixia.diudiuma.presenter.PresenterZXing;
import com.shixia.diudiuma.presenter.base.BasePresenter;
import com.shixia.diudiuma.view.CToast;

/**
 * Created by AmosShi on 2016/10/21.
 *
 * Description:
 */

public class ZXingActivity extends BaseActivity implements ZXingActivityIView{

    private TextView tvZxingTextResult;
    private EditText etContext;
    private ImageView imgZxingPicResult;
    private Button btnScanImg;
    private Button btnDecodeToImg;

    private PresenterZXing zxingPresenter;

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_zxing_main_layout);

        tvZxingTextResult = (TextView) findViewById(R.id.tv_zxing_text_result);
        etContext = (EditText) findViewById(R.id.et_content);
        imgZxingPicResult = (ImageView) findViewById(R.id.img_zxing_pic_result);
        btnScanImg = (Button) findViewById(R.id.btn_scan_img);
        btnDecodeToImg = (Button) findViewById(R.id.btn_decode_to_img);

    }

    @Override
    protected void initListener() {
        btnScanImg.setOnClickListener(v -> zxingPresenter.scanZXingImg());
        btnDecodeToImg.setOnClickListener(v ->
                zxingPresenter.decodeToZXingImg(etContext.getText().toString()));
    }

    @Override
    protected BasePresenter initPresenter() {
        zxingPresenter = new PresenterZXing(this,this);
        return zxingPresenter;
    }

    @Override
    public void onShowScanResult(String result) {
        tvZxingTextResult.setText(result);
    }

    @Override
    public void onShowDecodeResult(Bitmap img) {
        imgZxingPicResult.setImageBitmap(img);
    }

    @Override
    public void showRemind(String s) {
        CToast.makeCText(this,s, Toast.LENGTH_SHORT).show();
    }
}
