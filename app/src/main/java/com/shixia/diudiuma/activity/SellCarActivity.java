package com.shixia.diudiuma.activity;

import android.content.Intent;
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
import com.shixia.diudiuma.presenter.base.BasePresenter;

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
        btnMyBills.setOnClickListener(v -> presenterSellCar.showPhonePicker());
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, (view, position) -> {
            //图片预览操作（可编辑）
            PhotoPreview.builder()
                    .setPhotos(selectedPhotos)
                    .setCurrentItem(position)
                    .start(SellCarActivity.this);
        }));
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

}
