package com.chen.mvp.module.news.main;

import com.chen.mvp.greendao.NewsTypeInfoDao;
import com.chen.mvp.module.base.IRxBusPresenter;
import com.chen.mvp.rxbus.RxBus;
import com.orhanobut.logger.Logger;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * author long
 * date 17-6-25
 * desc
 */

public class NewsMainPresenter implements IRxBusPresenter {

    private INewsMainView mView;
    private NewsTypeInfoDao mDbDao;
    private RxBus mRxBus;

    public NewsMainPresenter(INewsMainView mView, NewsTypeInfoDao mDbDao, RxBus mRxBus) {
        this.mView = mView;
        this.mDbDao = mDbDao;
        this.mRxBus = mRxBus;
    }

    @Override
    public void getData(boolean isRefresh) {
        mDbDao.queryBuilder().rx().list()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(newsTypeInfo ->
                        mView.loadData(newsTypeInfo)
                );
    }

    @Override
    public void getMoreData() {

    }

    @Override
    public <T> void registerRxBus(Class<T> eventType, Action1<T> action) {
        Subscription subscription = mRxBus.doSubscribe(eventType, action,
                throwable -> Logger.e(throwable.toString()));
        mRxBus.addSubscription(this,subscription);
    }

    @Override
    public void unRegisterRxBus() {
        mRxBus.unSubscribe(this);
    }


}
