package com.shixia.diudiuma.mvp.presenter;

import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;

import com.shixia.diudiuma.R;
import com.shixia.diudiuma.bean.Constants;
import com.shixia.diudiuma.common_utils.SpUtil;
import com.shixia.diudiuma.mvp.activity.EditTagsPageActivity;
import com.shixia.diudiuma.mvp.iview.EditTagsPageIView;
import com.shixia.diudiuma.mvp.iview.base.BaseIView;
import com.shixia.diudiuma.mvp.presenter.base.BasePresenter;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

import static android.app.Activity.RESULT_OK;

/**
 * Created by AmosShi on 2016/11/17.
 * Description:
 */

public class PresenterEditTagsPage extends BasePresenter {

    private EditTagsPageActivity activity;
    private EditTagsPageIView iView;

    private List<String> tagsList = new ArrayList<String>();

    private boolean isAlreadyExit;

    public PresenterEditTagsPage(Context context, BaseIView iView) {
        super(context, iView);
        this.activity = (EditTagsPageActivity) context;
        this.iView = (EditTagsPageIView) iView;
    }

    /**
     * 初始化标签
     *
     * @param tag 当前标签内容和常用标签内容
     */
    public void initTag(String tag) {
        //初始化当前物品标签数据
        if (!TextUtils.isEmpty(tag)) {
            String[] tagsArr = tag.split("#");
            if (tagsArr.length > 0) {
                for (String aTagsArr : tagsArr) {
                    tagsList.add(aTagsArr);
                    iView.onAddTag(aTagsArr, true, false);
                }
            }
        }

        //初始化常用物品标签数据
        String goodsTags = SpUtil.getString(activity, "goods_always_used_tags", null);
        if (TextUtils.isEmpty(goodsTags)) {
            SpUtil.put(activity, "goods_always_used_tags", Constants.defaultTag);
            goodsTags = Constants.defaultTag;   //当前标签为空，初始化默认标签并存储到本地，只有第一次使用该功能的时候会执行该操作
        }
        String[] usedTagsArr = goodsTags.split("#");
        if (usedTagsArr.length > 0) {
            for (String aTagsArr : usedTagsArr) {
                iView.onAddTag(aTagsArr, false, true);
            }
        }
    }

    /**
     * 为当前物品添加一个标签
     *
     * @param newTag 新的标签
     */
    public void addTag(String newTag) {
        if (!TextUtils.isEmpty(newTag)) {
            if(tagsList.contains(newTag)){
                iView.onShowRemind("您已添加过该标签！");
                return;
            }
            tagsList.add(newTag);
            iView.onAddTag(newTag, true, false);
        }
    }

    /**
     * 移除标签
     *
     * @param tag 当前标签内容
     */
    public void removeTag(String tag) {
        tagsList.remove(tag);
        iView.onRemoveTag(tag);
    }

    /**
     * 确认提交标签
     */
    public void submit() {
        Intent intent = new Intent();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < tagsList.size(); i++) {
            if (i == tagsList.size() - 1) {
                stringBuilder.append(tagsList.get(i));
            } else {
                stringBuilder.append(tagsList.get(i)).append("#");
            }
        }
        intent.putExtra("value", stringBuilder.toString());
        activity.setResult(RESULT_OK, intent);
        iView.onSubmitTags(stringBuilder.toString());
    }

    /**
     * 添加自定义标签
     *
     * @param tagText 自定义标签内容
     */
    public void addNewTag(Editable tagText) {
        isAlreadyExit = false;
        if (tagText == null||TextUtils.isEmpty(tagText.toString())) {
            iView.onShowRemind(activity.getString(R.string.tag_canot_null));
            return;
        }
        String newTag = tagText.toString(); //得到新的标签内容
        String alwaysUsedTags = SpUtil.getString(activity, "goods_always_used_tags", "");
        String[] split = alwaysUsedTags.split("#");
        Observable.from(split)
                .filter(s -> TextUtils.equals(s, newTag))
                .subscribe(s -> {
                    isAlreadyExit = true;
                    iView.onShowRemind("该标签已存在");
                });
        if (!isAlreadyExit) {
            iView.onAddTag(newTag, true, true);
            //修改本地常用标签数据
            changeUsedTags(newTag);
            tagsList.add(newTag);
        }
        iView.onAddTagFinish(); //添加标签完成之后收起全键盘，清空输入框

    }

    /**
     * 添加新标签之后修改本地存储的常用标签体
     *
     * @param newTag 新增标签内容
     */
    private void changeUsedTags(String newTag) {
        String alwaysUsedTags = SpUtil.getString(activity, "goods_always_used_tags", "");
        String newAlwaysUsedTags = alwaysUsedTags + "#" + newTag;
        SpUtil.put(activity, "goods_always_used_tags", newAlwaysUsedTags); //更新本地存储的常用标签信息
    }
}
