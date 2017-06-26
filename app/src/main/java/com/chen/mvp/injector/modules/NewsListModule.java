package com.chen.mvp.injector.modules;

import com.chen.mvp.adapter.NewsMultiListAdapter;
import com.chen.mvp.injector.scope.FragmentScope;
import com.chen.mvp.module.base.IBasePresenter;
import com.chen.mvp.module.news.newslist.NewsListFragment;
import com.chen.mvp.module.news.newslist.NewsListPresenter;
import com.dl7.recycler.adapter.BaseQuickAdapter;

import dagger.Module;
import dagger.Provides;

/**
 * author long
 * date 17-6-26
 * desc
 */

@Module
public class NewsListModule {

    private String type;
    private NewsListFragment mNewsListView;

    public NewsListModule(String type, NewsListFragment mNewsListFragment) {
        this.type = type;
        this.mNewsListView = mNewsListFragment;
    }

    @FragmentScope
    @Provides
    public IBasePresenter providePresenter() {
        return new NewsListPresenter(mNewsListView, type);
    }

    @FragmentScope
    @Provides
    public BaseQuickAdapter provideAdapter() {
        return new NewsMultiListAdapter(mNewsListView.getContext());
    }
}
