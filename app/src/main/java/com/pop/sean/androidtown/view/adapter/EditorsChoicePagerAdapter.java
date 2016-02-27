package com.pop.sean.androidtown.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.widget.ListView;

import com.pop.sean.androidtown.view.fragment.BaseFragment;
import com.pop.sean.androidtown.view.fragment.BaseListFragment;
import com.pop.sean.androidtown.view.fragment.MomentsFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sean on 2/25/16.
 */
public class EditorsChoicePagerAdapter extends FragmentPagerAdapter {

    private final int PAGE_NUM = 3;
    private List<BaseListFragment> fragments;

    public EditorsChoicePagerAdapter(FragmentManager fm) {
        super(fm);
        fragments = new ArrayList<BaseListFragment>();
        fragments.add(new MomentsFragment().newInstance("MOMENTS"));
        fragments.add(new MomentsFragment().newInstance("VENUE"));
        fragments.add(new MomentsFragment().newInstance("PEOPLE"));
    }
    private BaseListFragment mCurrentFragment;

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return PAGE_NUM;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "MOMENTS";
            case 1:
                return "VENUE";
            case 2:
                return "PEOPLE";
        }
        return null;
    }

    public void switchTO(final int position) {
        for (int i = 0; i < PAGE_NUM; i++) {
            BaseListFragment fragment =  fragments.get(i);
            if (i == position) {
                mCurrentFragment = fragment;
            }
        }
    }
}
