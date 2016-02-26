package com.pop.sean.androidtown.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.pop.sean.androidtown.view.fragment.MomentsFragment;

/**
 * Created by sean on 2/25/16.
 */
public class EditorsChoicePagerAdapter extends FragmentPagerAdapter {

    private final int PAGE_NUM = 3;

    public EditorsChoicePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return MomentsFragment.newInstance("MOMENTS");
            case 1:
                return MomentsFragment.newInstance("VENUE");
            case 2:
                return MomentsFragment.newInstance("PEOPLE");
        }

        return null;
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
}
