package com.chen.mvp.module.news;

import com.chen.mvp.local.table.NewsTypeInfo;

import java.util.List;

/**
 * author long
 * date 17-6-25
 * desc 主页接口
 */

public interface INewsMainView {

    /**
     * 加载数据
     * @param checkList 选中的新闻类型
     */
    void loadData(List<NewsTypeInfo> checkList);
}
