package com.chen.mvp.module.home;

import android.content.Intent;
import android.widget.Button;

import com.chen.mvp.R;
import com.chen.mvp.module.base.BaseActivity;
import com.chen.mvp.utils.RxHelper;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Observer;

/**
 * author long
 * date 17-6-24
 * desc
 */

public class SplashActivity extends BaseActivity {

    @BindView(R.id.btn_skip)
    Button btnSkip;

    private boolean mIsSkip = false;

    @Override
    public int attachLayoutRes() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void updateViews(boolean isRefresh) {
        RxHelper.countdown(5)
                .compose(this.<Integer>bindToLife())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onNext(Integer integer) {
                        btnSkip.setText("跳过 " + integer);
                    }


                    @Override
                    public void onError(Throwable e) {
                        doSkip();
                    }

                    @Override
                    public void onCompleted() {
                        doSkip();
                    }
                });
    }

    /**
     * 跳过
     */
    private void doSkip() {
        if (!mIsSkip) {
            mIsSkip = true;
            finish();
            startActivity(new Intent(SplashActivity.this, HomeActivity.class));
            overridePendingTransition(R.anim.hold, R.anim.zoom_in_exit);
        }
    }

    @Override
    public void onBackPressed() {
        return;
    }

    @OnClick(R.id.btn_skip)
    public void onViewClicked() {
        doSkip();
    }
}
