package com.chen.mvp.injector.modules;

import android.content.Context;

import com.chen.mvp.AndroidApplication;
import com.chen.mvp.greendao.DaoSession;
import com.chen.mvp.rxbus.RxBus;

import dagger.Module;
import dagger.Provides;

/**
 * author long
 * date 17-6-24
 * desc
 */
@Module
public class ApplicationModule {
    private AndroidApplication mApplication;
    private DaoSession mDaoSession;
    private RxBus mRxBus;

    public ApplicationModule(AndroidApplication mApplication, DaoSession mDaoSession, RxBus mRxBus) {
        this.mApplication = mApplication;
        this.mDaoSession = mDaoSession;
        this.mRxBus = mRxBus;
    }

    @Provides
    Context provideApplicationContext() {
        return mApplication.getApplication();
    }

    @Provides
    DaoSession provideDaoSession() {
        return mDaoSession;
    }

    @Provides
    RxBus provideRxBus() {
        return mRxBus;
    }
}
