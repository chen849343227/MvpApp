package com.chen.mvp.module.news.newslist;

import com.chen.mvp.adapter.item.NewsMultiItem;
import com.chen.mvp.api.RetrofitService;
import com.chen.mvp.api.bean.NewsInfo;
import com.chen.mvp.module.base.IBasePresenter;
import com.chen.mvp.utils.ToastUtils;
import com.chen.mvp.widget.EmptyLayout;
import com.orhanobut.logger.Logger;

import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Func1;

/**
 * author long
 * date 17-6-26
 * desc
 */

public class NewsListPresenter implements IBasePresenter {

    private INewsListView mView;
    private String type;

    private int page = 1;
    private static final int LIMIT = 20;

    public NewsListPresenter(INewsListView mView, String type) {
        this.mView = mView;
        this.type = type;
    }

    @Override
    public void getData(boolean isRefresh) {
        RetrofitService.getNewsList(type, page, LIMIT)
                .doOnSubscribe(() -> {
                    if (!isRefresh) {
                        mView.showLoading();
                    }
                })
                .compose(mTransformer)
                .subscribe(new Subscriber<List<NewsMultiItem>>() {
                    @Override
                    public void onCompleted() {
                        Logger.w("onCompleted " + isRefresh);
                        if (isRefresh) {
                            mView.finishRefresh();
                        } else {
                            mView.hideLoading();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.e(e.toString() + " " + isRefresh);
                        if (isRefresh) {
                            mView.finishRefresh();
                            // 可以提示对应的信息，但不更新界面
                            ToastUtils.showToast(e.toString());
                        } else {
                            mView.showNetError(() -> getData(false));
                        }
                    }

                    @Override
                    public void onNext(List<NewsMultiItem> newsMultiItems) {
                        mView.loadData(newsMultiItems);
                        page++;
                    }
                });
    }

    @Override
    public void getMoreData() {
        RetrofitService.getNewsList(type, page, LIMIT)
                .compose(mTransformer)
                .subscribe(new Subscriber<List<NewsMultiItem>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.e(e.toString());
                        mView.loadNoData();
                    }

                    @Override
                    public void onNext(List<NewsMultiItem> newsList) {
                        mView.loadMoreData(newsList);
                        page++;
                    }
                });
    }

    private Observable.Transformer<NewsInfo, List<NewsMultiItem>> mTransformer = new Observable.Transformer<NewsInfo, List<NewsMultiItem>>() {
        @Override
        public Observable<List<NewsMultiItem>> call(Observable<NewsInfo> newsInfoObservable) {
            return newsInfoObservable
                    .map(newsInfo -> {
                        /*if (NewsUtils.isNewsPhotoSet(newsBean.getSkipType())) {
                            return new NewsMultiItem(NewsMultiItem.ITEM_TYPE_PHOTO_SET, newsBean);
                        }*/
                        return new NewsMultiItem(NewsMultiItem.ITEM_TYPE_NORMAL, newsInfo);
                    })
                    .toList()
                    .compose(mView.bindToLife());
        }
    };
}
