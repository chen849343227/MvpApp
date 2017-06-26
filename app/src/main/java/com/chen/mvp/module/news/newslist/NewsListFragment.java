package com.chen.mvp.module.news.newslist;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chen.mvp.R;
import com.chen.mvp.adapter.item.NewsMultiItem;
import com.chen.mvp.api.bean.NewsInfo;
import com.chen.mvp.injector.component.DaggerNewsListComponent;
import com.chen.mvp.injector.modules.NewsListModule;
import com.chen.mvp.module.base.BaseFragment;
import com.chen.mvp.widget.EmptyLayout;
import com.dl7.recycler.adapter.BaseQuickAdapter;
import com.dl7.recycler.helper.RecyclerViewHelper;
import com.dl7.recycler.listener.OnRequestDataListener;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.SlideInRightAnimationAdapter;

/**
 * author long
 * date 17-6-26
 * desc
 */

public class NewsListFragment extends BaseFragment implements INewsListView{

    private static final String TYPE_KEY = "NewsTypeKey";
    @BindView(R.id.rv_news_list)
    RecyclerView rvNewsList;

    @Inject
    BaseQuickAdapter mBaseAdapter;

    //private SliderLayout mAdSlider;

    private String type;


    /**
     * 实例化一个Fragment
     *
     * @param type 新闻类型
     * @return
     */
    public static Fragment newInstance(String type) {
        NewsListFragment mNewsListFragment = new NewsListFragment();
        Bundle bundle = new Bundle();
        bundle.putString(TYPE_KEY, type);
        mNewsListFragment.setArguments(bundle);
        return mNewsListFragment;
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_news_list;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            type = getArguments().getString(TYPE_KEY);
        }
    }

    @Override
    protected void initInjector() {
        DaggerNewsListComponent.builder()
                .applicationComponent(getAppComponent())
                .newsListModule(new NewsListModule(type,this))
                .build()
                .inject(this);
    }

    @Override
    protected void initViews() {
        SlideInRightAnimationAdapter animAdapter = new SlideInRightAnimationAdapter(mBaseAdapter);
        RecyclerViewHelper.initRecyclerViewV(mContext, rvNewsList, true, new AlphaInAnimationAdapter(animAdapter));
        mBaseAdapter.setRequestDataListener(new OnRequestDataListener() {
            @Override
            public void onLoadMore() {
                mPresenter.getMoreData();
            }
        });
    }

    @Override
    protected void updateViews(boolean isRefresh) {
        mPresenter.getData(isRefresh);
    }


    @Override
    public void loadData(List<NewsMultiItem> data) {
        mBaseAdapter.updateItems(data);
    }

    @Override
    public void loadMoreData(List<NewsMultiItem> data) {
        mBaseAdapter.loadComplete();
        mBaseAdapter.addItems(data);
    }

    @Override
    public void loadNoData() {
        mBaseAdapter.loadAbnormal();
    }

    @Override
    public void loadAdData(NewsInfo newsBean) {
       /* View view = LayoutInflater.from(mContext).inflate(R.layout.head_news_list, null);
        mAdSlider = (SliderLayout) view.findViewById(R.id.slider_ads);
        SliderHelper.initAdSlider(mContext, mAdSlider, newsBean);
        mAdapter.addHeaderView(view);*/
    }
}
