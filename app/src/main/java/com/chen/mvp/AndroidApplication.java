package com.chen.mvp;

import android.app.Application;
import android.content.Context;

import com.chen.mvp.greendao.DaoMaster;
import com.chen.mvp.greendao.DaoSession;
import com.chen.mvp.injector.component.DaggerApplicationComponent;
import com.chen.mvp.local.dao.NewsTypeDao;
import com.chen.mvp.rxbus.RxBus;
import com.chen.mvp.api.RetrofitService;
import com.chen.mvp.injector.component.ApplicationComponent;

import com.chen.mvp.injector.modules.ApplicationModule;
import com.chen.mvp.utils.ToastUtils;
import com.orhanobut.logger.Logger;
import com.squareup.leakcanary.LeakCanary;

import org.greenrobot.greendao.database.Database;

/**
 * author long
 * date 17-6-24
 * desc
 */

public class AndroidApplication extends Application {

    private static final java.lang.String DB_NAME = "Mvp_db";
    private static ApplicationComponent sComponent;
    private static AndroidApplication sApplication;
    private DaoSession mDaoSession;

    private RxBus mRxBus = new RxBus();

    @Override
    public void onCreate() {
        super.onCreate();
        sApplication = this;
        initDatabase();
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
                .applicationModule(new ApplicationModule(this,mDaoSession,mRxBus))
                .build();
    }

    /**
     * 初始化数据库
     */
    private void initDatabase() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(getApplication(), DB_NAME);
        Database database = helper.getWritableDb();
        mDaoSession = new DaoMaster(database).newSession();
        NewsTypeDao.updateLocalData(getApplication(), mDaoSession);
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
