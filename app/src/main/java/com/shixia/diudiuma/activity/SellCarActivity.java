package com.shixia.diudiuma.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.Button;

import com.shixia.diudiuma.R;
import com.shixia.diudiuma.activity.base.BaseActivity;
import com.shixia.diudiuma.adapter.PhotoAdapter;
import com.shixia.diudiuma.iview.SellCarIView;
import com.shixia.diudiuma.listener.RecyclerItemClickListener;
import com.shixia.diudiuma.presenter.PresenterSellCar;

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

    private RecyclerView recyclerView;
    private PhotoAdapter photoAdapter;

    private ArrayList<String> selectedPhotos = new ArrayList<>();

    public static final int WRITE_COARSE_LOCATION_REQUEST_CODE = 0x100;

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_sell_car);
        btnMyBills = (Button) findViewById(R.id.btn_my_bills);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        photoAdapter = new PhotoAdapter(this, selectedPhotos);

        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(4, OrientationHelper.VERTICAL));
        recyclerView.setAdapter(photoAdapter);

    }

    @Override
    protected void initListener() {
        btnMyBills.setOnClickListener(v -> showPhoto());
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, (view, position) -> {
            //图片预览操作（可编辑）
            PhotoPreview.builder()
                    .setPhotos(selectedPhotos)
                    .setCurrentItem(position)
                    .start(SellCarActivity.this);
        }));
    }

    @Override
    protected void initPresenter() {
        presenterSellCar = new PresenterSellCar(this, this);
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
     * 检测权限后展示照片
     */
    public void showPhoto(){
        //这里以ACCESS_COARSE_LOCATION为例
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            //申请WRITE_EXTERNAL_STORAGE权限
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    WRITE_COARSE_LOCATION_REQUEST_CODE);//自定义的code
        }else {
            presenterSellCar.showPhonePicker();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == WRITE_COARSE_LOCATION_REQUEST_CODE
                && grantResults.length>0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            presenterSellCar.showPhonePicker();
        }
    }
}
