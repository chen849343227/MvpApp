package com.chen.mvp.module.news;

import com.chen.mvp.module.base.IRxBusPresenter;

import rx.functions.Action1;

/**
 * author long
 * date 17-6-25
 * desc
 */

public class INewsMainPresenter implements IRxBusPresenter {
    @Override
    public <T> void registerRxBus(Class<T> eventType, Action1<T> action) {

    }

    @Override
    public void unregisterRxBus() {

    }
}
