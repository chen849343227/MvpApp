package com.chen.mvp.injector.component;

import com.chen.mvp.injector.scope.FragmentScope;
import com.chen.mvp.module.news.newslist.NewsListFragment;
import com.chen.mvp.injector.modules.NewsListModule;

import dagger.Component;

/**
 * author long
 * date 17-6-26
 * desc
 */
@FragmentScope
@Component(dependencies = ApplicationComponent.class ,modules = NewsListModule.class)
public interface NewsListComponent {
    void inject(NewsListFragment fragment);
}
