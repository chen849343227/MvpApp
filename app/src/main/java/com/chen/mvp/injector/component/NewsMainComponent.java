package com.chen.mvp.injector.component;

import com.chen.mvp.injector.scope.FragmentScope;
import com.chen.mvp.injector.modules.NewsMainModule;
import com.chen.mvp.module.news.main.NewsMainFragment;
import dagger.Component;

/**
 * author long
 * date 17-6-26
 * desc
 */
@FragmentScope
@Component(dependencies = ApplicationComponent.class, modules = NewsMainModule.class)
public interface NewsMainComponent {
    void inject(NewsMainFragment fragment);
}
