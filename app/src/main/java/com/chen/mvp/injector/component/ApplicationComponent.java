package com.chen.mvp.injector.component;

import android.content.Context;

import com.chen.mvp.greendao.DaoSession;
import com.chen.mvp.rxbus.RxBus;
import com.chen.mvp.injector.modules.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * author long
 * date 17-6-24
 * desc
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    Context getContext();

    DaoSession getDaoSession();

    RxBus getRxBus();
}
