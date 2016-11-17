package com.shixia.diudiuma.mvp.presenter;

import android.content.Context;
import android.content.Intent;

import com.shixia.diudiuma.mvp.activity.EditTagsPageActivity;
import com.shixia.diudiuma.mvp.iview.EditTagsPageIView;
import com.shixia.diudiuma.mvp.iview.base.BaseIView;
import com.shixia.diudiuma.mvp.presenter.base.BasePresenter;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Administrator on 2016/11/17.
 */

public class PresenterEditTagsPage extends BasePresenter {

    private EditTagsPageActivity activity;
    private EditTagsPageIView iView;

    private List<String> tagsList = new ArrayList<String>();

    public PresenterEditTagsPage(Context context, BaseIView iView) {
        super(context, iView);
        this.activity = (EditTagsPageActivity) context;
        this.iView = (EditTagsPageIView) iView;
    }

    /**
     * 添加标签
     * @param tag 当前标签内容
     */
    public void addTag(String tag){
        if (tag != null) {
            String[] tagsArr = tag.split("#");
            if (tagsArr.length > 0) {
                for (String aTagsArr : tagsArr) {
                    tagsList.add(aTagsArr);
                    iView.onAddTag(aTagsArr);
                }
            }
        }
    }

    /**
     * 移除标签
     * @param tag 当前标签内容
     */
    public void removeTag(String tag){
        tagsList.remove(tag);
        iView.onRemoveTag(tag);
    }

    /**
     * 确认提交标签
     */
    public void submit(){
        Intent intent = new Intent();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < tagsList.size(); i++) {
            if(i == tagsList.size()-1){
                stringBuilder.append(tagsList.get(i));
            }else {
                stringBuilder.append(tagsList.get(i)).append("#");
            }
        }
        intent.putExtra("value", stringBuilder.toString());
        activity.setResult(RESULT_OK, intent);
        iView.onSubmitTags(stringBuilder.toString());
    }
}
