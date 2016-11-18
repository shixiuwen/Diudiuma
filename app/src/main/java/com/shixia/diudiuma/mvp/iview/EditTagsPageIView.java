package com.shixia.diudiuma.mvp.iview;

import com.shixia.diudiuma.mvp.iview.base.BaseIView;

/**
 * Created by Administrator on 2016/11/17.
 */

public interface EditTagsPageIView extends BaseIView {

    /**
     * 添加一个标签
     *
     * @param tag                  添加的标签内容
     * @param isAddToTagList       是否添加到标签列表
     * @param isAddToAlwaysUsedTag 是否添加到常用标签列表
     */
    void onAddTag(String tag, boolean isAddToTagList, boolean isAddToAlwaysUsedTag);

    /**
     * 移除一个标签
     *
     * @param tag 移除的标签内容
     */
    void onRemoveTag(String tag);

    /**
     * 确认提交标签
     *
     * @param tags 提交的标签内容
     */
    void onSubmitTags(String tags);

    /**
     * 显示提示信息
     *
     * @param content 提示内容
     */
    void onShowRemind(String content);
}
