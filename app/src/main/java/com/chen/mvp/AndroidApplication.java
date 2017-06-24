package com.chen.mvp;

import android.app.Application;

import com.chen.mvp.RxBus.RxBus;
import com.chen.mvp.api.RetrofitService;
import com.chen.mvp.injector.component.ApplicationComponent;
import com.chen.mvp.injector.component.DaggerApplicationComponent;
import com.chen.mvp.injector.modules.ApplicationModule;
import com.chen.mvp.utils.ToastUtils;
import com.orhanobut.logger.Logger;
import com.squareup.leakcanary.LeakCanary;

/**
 * author long
 * date 17-6-24
 * desc
 */

public class AndroidApplication extends Application {

    private static ApplicationComponent sComponent;
    private static AndroidApplication sApplication;

    private RxBus mRxBus = new RxBus();

    @Override
    public void onCreate() {
        super.onCreate();
        sApplication = this;
        initInjector();
        initConfig();
    }

    public static AndroidApplication getApplication() {
        return sApplication;
    }

    public static ApplicationComponent getAppComponent() {
        return sComponent;
    }

    /**
     * 初始化注射器
     */
    private void initInjector() {
        sComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(this,mRxBus))
                .build();
    }

    /**
     * 初始化配置
     */
    private void initConfig() {
        if (BuildConfig.DEBUG) {
            LeakCanary.install(getApplication());
            Logger.init("LogTAG");
        }
        //toast初始化
        ToastUtils.init(getApplication());
        //网络服务初始化
        RetrofitService.init();
    }


}
