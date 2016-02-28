package com.pop.sean.androidtown.view.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.pop.sean.androidtown.view.MomentDetailView;
import com.pop.sean.androidtown.view.fragment.MomentDetailFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by stanzhao on 2/27/16.
 */
public class MomentViewPagerAdapter extends PagerAdapter {

    private List<MomentDetailView> viewList = new ArrayList<>();

    public MomentViewPagerAdapter(List<MomentDetailView> viewList) {
        this.viewList = viewList;
    }

    @Override
    public int getCount() {
        return viewList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(viewList.get(position));
        return viewList.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(viewList.get(position));
    }
}
