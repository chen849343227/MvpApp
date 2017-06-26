package com.chen.mvp.utils;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * author long
 * date 17-6-24
 * desc splash界面按钮倒计时
 */

public class RxHelper {

    private RxHelper() {
        throw new AssertionError();
    }

    public static Observable<Integer> countdown(int time) {
        if (time < 0) {
            time = 0;
        }
        final int countTime = time;

        return Observable.interval(0, 1, TimeUnit.SECONDS)
                .map(aLong -> countTime - aLong.intValue())
                //倒计时次数
                .take(countTime + 1)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());

    }
}
