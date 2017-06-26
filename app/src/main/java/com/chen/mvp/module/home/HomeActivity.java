package com.chen.mvp.module.home;

import android.Manifest;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.SparseArray;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.chen.mvp.R;
import com.chen.mvp.module.base.BaseActivity;
import com.chen.mvp.module.news.main.NewsMainFragment;

import java.io.File;

import butterknife.BindView;
import rx.functions.Action1;


public class HomeActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final String NEW = "news";

    private static final String PHOTO = "photos";

    private static final String VIDEO = "videos";


    @BindView(R.id.fl_container)
    FrameLayout flContainer;
    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;


    private SparseArray<String> mSparseTags = new SparseArray<>();
    private long mExitTime = 0;
    private int mItemId = -1;
    private Handler mHandler = new Handler(msg -> {
        switch (msg.what) {
            case R.id.nav_news:
                replaceFragment(R.id.fl_container, new NewsMainFragment(), mSparseTags.get(R.id.nav_news));
                break;
            case R.id.nav_photos:
                // replaceFragment(R.id.fl_container, new PhotoMainFragment(), mSparseTags.get(R.id.nav_photos));
                break;
            case R.id.nav_videos:
                //   replaceFragment(R.id.fl_container, new VideoMainFragment(), mSparseTags.get(R.id.nav_videos));
                break;
            case R.id.nav_night:

                break;
            case R.id.nav_setting:
                //  SettingsActivity.launch(HomeActivity.this);
                break;
        }
        mItemId = -1;
        return true;
    });

    @Override
    public int attachLayoutRes() {
        return R.layout.activity_home;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {
        initDrawerLayout(mDrawerLayout, navView);
        mSparseTags.put(R.id.nav_news, NEW);
        mSparseTags.put(R.id.nav_photos, PHOTO);
        mSparseTags.put(R.id.nav_videos, VIDEO);
        _getPermission();
    }


    @Override
    protected void updateViews(boolean isRefresh) {
        navView.setCheckedItem(R.id.nav_news);
        addFragment(R.id.fl_container, new NewsMainFragment(), NEW);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        mDrawerLayout.closeDrawer(GravityCompat.START);
        if (item.isChecked()) {
            return true;
        }
        mItemId = item.getItemId();
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            mDrawerLayout.openDrawer(GravityCompat.START);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 初始化DrawerLayout
     *
     * @param mDrawerLayout
     * @param navView
     */
    private void initDrawerLayout(DrawerLayout mDrawerLayout, NavigationView navView) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
            //将侧边栏顶部延伸至status bar
            mDrawerLayout.setFitsSystemWindows(true);
            //将主页面顶部延伸至status bar
            mDrawerLayout.setClipToPadding(false);
        }
        mDrawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerClosed(View drawerView) {
                mHandler.sendEmptyMessage(mItemId);
            }
        });
        navView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        // 获取堆栈里有几个
        final int stackEntryCount = getSupportFragmentManager().getBackStackEntryCount();
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else if (stackEntryCount == 1) {
            // 如果剩一个说明在主页，提示按两次退出app
            exit();
        } else {
            // 获取上一个堆栈中保存的是哪个页面，根据name来设置导航项的选中状态
            final String tagName = getSupportFragmentManager().getBackStackEntryAt(stackEntryCount - 3).getName();
            navView.setCheckedItem(mSparseTags.keyAt(mSparseTags.indexOfValue(tagName)));
            super.onBackPressed();
        }
    }

    private void _getPermission() {
       /* final File dir = new File(FileDownloader.getDownloadDir());
        if (!dir.exists() || !dir.isDirectory()) {
            dir.delete();
            new RxPermissions(this).request(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
                    .subscribe(new Action1<Boolean>() {
                        @Override
                        public void call(Boolean granted) {
                            if (granted) {
                                dir.mkdirs();
                            } else {
                                SnackbarUtils.showSnackbar(HomeActivity.this, "下载目录创建失败", true);
                            }
                        }
                    });
        }*/
    }

    /**
     * 退出
     */
    private void exit() {
        if (System.currentTimeMillis() - mExitTime > 2000) {
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            mExitTime = System.currentTimeMillis();
        } else {
            finish();
        }
    }
}
