package com.chen.mvp.module.news.newslist;

import com.chen.mvp.adapter.item.NewsMultiItem;
import com.chen.mvp.api.bean.NewsInfo;
import com.chen.mvp.module.base.ILoadDataView;
import com.chen.mvp.widget.EmptyLayout;
import com.trello.rxlifecycle.LifecycleTransformer;

import java.util.List;

/**
 * author long
 * date 17-6-26
 * desc
 */

public interface INewsListView extends ILoadDataView<List<NewsMultiItem>> {
    /**
     * 加载广告数据
     * @param newsBean 新闻
     */
    void loadAdData(NewsInfo newsBean);
}
