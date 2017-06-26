package com.chen.mvp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * author long
 * date 17-6-25
 * desc ViewPager适配器
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> mFragments;
    private List<String> mTitles;

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
        mFragments = new ArrayList<>();
        mTitles = new ArrayList<>();
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position);
    }

    @Override
    public int getItemPosition(Object object) {
        return PagerAdapter.POSITION_NONE;
    }

    /**
     * 设置item
     * @param fragments
     * @param mTitles
     */
    public void setItems(List<Fragment> fragments, List<String> mTitles) {
        this.mFragments = fragments;
        this.mTitles = mTitles;
        notifyDataSetChanged();
    }

    public void setItems(List<Fragment> fragments, String[] mTitles) {
        this.mFragments = fragments;
        this.mTitles = Arrays.asList(mTitles);
        notifyDataSetChanged();
    }

    /**
     * 添加item
     * @param fragment
     * @param title
     */
    public void addItem(Fragment fragment, String title) {
        mFragments.add(fragment);
        mTitles.add(title);
        notifyDataSetChanged();
    }

    /**
     * 删除item
     * @param position
     */
    public void delItem(int position) {
        mTitles.remove(position);
        mFragments.remove(position);
        notifyDataSetChanged();
    }

    public int delItem(String title) {
        int index = mTitles.indexOf(title);
        if (index != -1) {
            delItem(index);
        }
        return index;
    }

    public void swapItems(int fromPos, int toPos) {
        Collections.swap(mTitles, fromPos, toPos);
        Collections.swap(mFragments, fromPos, toPos);
        notifyDataSetChanged();
    }

    public void modifyTitle(int position, String title) {
        mTitles.set(position, title);
        notifyDataSetChanged();
    }
}
