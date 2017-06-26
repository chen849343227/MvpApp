package com.chen.mvp.injector.modules;

import android.annotation.TargetApi;
import android.os.Build;

import com.chen.mvp.adapter.ViewPagerAdapter;
import com.chen.mvp.greendao.DaoSession;
import com.chen.mvp.injector.scope.FragmentScope;
import com.chen.mvp.module.base.IRxBusPresenter;
import com.chen.mvp.module.news.main.NewsMainFragment;
import com.chen.mvp.module.news.main.NewsMainPresenter;
import com.chen.mvp.rxbus.RxBus;

import dagger.Module;
import dagger.Provides;

/**
 * author long
 * date 17-6-26
 * desc
 */
@Module
public class NewsMainModule {

    private NewsMainFragment mView;

    public NewsMainModule(NewsMainFragment view) {
        this.mView = view;
    }

    @FragmentScope
    @Provides
    public IRxBusPresenter provideMainPresenter(DaoSession mDaoSession, RxBus mRxBus) {
        return new NewsMainPresenter(mView, mDaoSession.getNewsTypeInfoDao(), mRxBus);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @FragmentScope
    @Provides
    public ViewPagerAdapter provideViewPagerAdapter() {
        return new ViewPagerAdapter(mView.getChildFragmentManager());
    }
}
