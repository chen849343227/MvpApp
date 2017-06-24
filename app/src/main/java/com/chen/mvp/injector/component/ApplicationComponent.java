package com.chen.mvp.injector.component;

import android.content.Context;

import com.chen.mvp.RxBus.RxBus;
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
    RxBus getRxBus();
}
