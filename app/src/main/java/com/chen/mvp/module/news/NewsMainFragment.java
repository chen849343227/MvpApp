package com.chen.mvp.module.news;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chen.mvp.R;
import com.chen.mvp.adapter.ViewPagerAdapter;
import com.chen.mvp.local.table.NewsTypeInfo;
import com.chen.mvp.module.base.BaseFragment;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;


/**
 * author long
 * date 17-6-25
 * desc
 */

public class NewsMainFragment extends BaseFragment<INewsMainPresenter> implements INewsMainView {

    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;

    @Inject
    ViewPagerAdapter pagerAdapter;

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_news_main;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }


    @Override
    public void loadData(List<NewsTypeInfo> checkList) {

    }
}
