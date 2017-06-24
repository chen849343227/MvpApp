package com.chen.mvp.module.home;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.widget.FrameLayout;

import com.chen.mvp.R;
import com.chen.mvp.module.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;


public class HomeActivity extends BaseActivity {


    @BindView(R.id.fl_container)
    FrameLayout flContainer;
    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    @Override
    public int attachLayoutRes() {
        return R.layout.activity_home;
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

}
