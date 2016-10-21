package com.shixia.diudiuma.mvp.activity;

import android.content.Intent;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.shixia.diudiuma.MyApplication;
import com.shixia.diudiuma.R;
import com.shixia.diudiuma.mvp.activity.base.BaseActivity;
import com.shixia.diudiuma.adapter.PhotoAdapter;
import com.shixia.diudiuma.mvp.iview.SellCarIView;
import com.shixia.diudiuma.listener.RecyclerItemClickListener;
import com.shixia.diudiuma.mvp.presenter.PresenterSellCar;
import com.shixia.diudiuma.mvp.presenter.base.BasePresenter;
import com.shixia.diudiuma.view.CToast;
import com.shixia.diudiuma.view.LoadingDialog;

import java.util.ArrayList;
import java.util.List;

import me.iwf.photopicker.PhotoPicker;
import me.iwf.photopicker.PhotoPreview;

/**
 * Created by AmosShi on 2016/10/12.
 * Description:
 */

public class SellCarActivity extends BaseActivity implements SellCarIView {

    private PresenterSellCar presenterSellCar;

    private Button btnMyBills;
    private Button btnUploadPic;
    private Button btnDownloadApk;
    private TextView tvProgress;

    private RecyclerView recyclerView;
    private PhotoAdapter photoAdapter;

    public ArrayList<String> selectedPhotos = new ArrayList<>();

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_sell_car);
        btnMyBills = (Button) findViewById(R.id.btn_my_bills);
        btnUploadPic = (Button) findViewById(R.id.btn_upload_pic);
        btnDownloadApk = (Button) findViewById(R.id.btn_download_apk);
        tvProgress = (TextView) findViewById(R.id.tv_progress);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        photoAdapter = new PhotoAdapter(this, selectedPhotos);

        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(4, OrientationHelper.VERTICAL));
        recyclerView.setAdapter(photoAdapter);

    }

    @Override
    protected void initListener() {
        btnMyBills.setOnClickListener(v -> presenterSellCar.showPhonePicker());
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, (view, position) -> {
            //图片预览操作（可编辑）
            PhotoPreview.builder()
                    .setPhotos(selectedPhotos)
                    .setCurrentItem(position)
                    .start(SellCarActivity.this);
        }));

        //点击开始上传图片
        btnUploadPic.setOnClickListener(v -> presenterSellCar.uploadPic());
        //点击开始下载apk
        btnDownloadApk.setOnClickListener(v -> presenterSellCar.downloadApk());
    }

    @Override
    protected BasePresenter initPresenter() {
        presenterSellCar = new PresenterSellCar(this, this);
        return presenterSellCar;
    }

    /**
     * 将选中的图片进行展示
     *
     * @param requestCode //
     * @param resultCode //
     * @param data //
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK &&
                (requestCode == PhotoPicker.REQUEST_CODE || requestCode == PhotoPreview.REQUEST_CODE)) {

            List<String> photos = null;
            if (data != null) {
                photos = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
            }
            selectedPhotos.clear();

            if (photos != null) {
                selectedPhotos.addAll(photos);
            }
            photoAdapter.notifyDataSetChanged();
        }
    }

    /**
     * 界面展示相关提醒操作，如上传文件成功或者失败等
     * @param msg
     */
    @Override
    public void onShowRemind(String msg) {
        MyApplication.UIHandler.post(() -> {
            LoadingDialog.getInstance(this,"").dismiss();
//            Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
            CToast.makeCText(this,msg, Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public void onDownloadProgressUpdate(String progress) {
        tvProgress.setText(progress+"%");
    }
}
