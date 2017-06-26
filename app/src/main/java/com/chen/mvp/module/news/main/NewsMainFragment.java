package com.chen.mvp.module.news.main;


import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.chen.mvp.R;
import com.chen.mvp.adapter.ViewPagerAdapter;
import com.chen.mvp.injector.component.DaggerNewsMainComponent;
import com.chen.mvp.injector.modules.NewsMainModule;
import com.chen.mvp.local.table.NewsTypeInfo;
import com.chen.mvp.module.base.BaseFragment;
import com.chen.mvp.module.base.IRxBusPresenter;
import com.chen.mvp.module.news.newslist.NewsListFragment;
import com.chen.mvp.rxbus.event.ChannelEvent;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import butterknife.BindView;


/**
 * author long
 * date 17-6-25
 * desc
 */

public class NewsMainFragment extends BaseFragment<IRxBusPresenter> implements INewsMainView {

    @BindView(R.id.tool_bar)
    Toolbar mToolBar;
    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;

    @Inject
    ViewPagerAdapter mPagerAdapter;

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_news_main;
    }

    @Override
    protected void initInjector() {
        DaggerNewsMainComponent.builder()
                .applicationComponent(getAppComponent())
                .newsMainModule(new NewsMainModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected void initViews() {
        initToolBar(mToolBar, true, "新闻");
        setHasOptionsMenu(true);
        mViewPager.setAdapter(mPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mPresenter.registerRxBus(ChannelEvent.class, this::handleChannelEvent);
    }



    @Override
    protected void updateViews(boolean isRefresh) {
        mPresenter.getData(isRefresh);
    }


    @Override
    public void loadData(List<NewsTypeInfo> checkList) {
        List<Fragment> fragments = new ArrayList<>();
        List<String> titles = new ArrayList<>();
        for (NewsTypeInfo bean : checkList) {
            titles.add(bean.getName());
            fragments.add(NewsListFragment.newInstance(bean.getType()));
        }
        mPagerAdapter.setItems(fragments, titles);
    }

    private void handleChannelEvent(ChannelEvent channelEvent) {

    }
}
