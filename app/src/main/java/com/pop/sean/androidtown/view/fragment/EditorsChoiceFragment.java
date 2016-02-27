package com.pop.sean.androidtown.view.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.astuetz.PagerSlidingTabStrip;
import com.pop.sean.androidtown.R;
import com.pop.sean.androidtown.view.activity.MainActivity;
import com.pop.sean.androidtown.view.adapter.EditorsChoicePagerAdapter;

import butterknife.Bind;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

/**
 * A simple {@link Fragment} subclass.
 */
public class EditorsChoiceFragment extends BaseFragment {

    @Bind(R.id.view_pager)
    ViewPager viewPager;
    @Bind(R.id.tabs)
    PagerSlidingTabStrip tabs;


    private LinearLayout mTabsLinearLayout;
    private EditorsChoicePagerAdapter mAdapter;

    public EditorsChoiceFragment() {
        // Required empty public constructor
    }

    public static EditorsChoiceFragment newInstance(String title) {
        EditorsChoiceFragment f = new EditorsChoiceFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        f.setArguments(bundle);
        return f;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainActivity activity = (MainActivity) getActivity();
//        activity.setToolBarTitle(getArguments().getString("title"));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_editors_choice_layout;
    }

    @Override
    protected void initView() {
        // Initialize the ViewPager and set an adapter
        mAdapter = new EditorsChoicePagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(mAdapter);

        // Bind the tabs to the ViewPager
        tabs.setViewPager(viewPager);
        mTabsLinearLayout = (LinearLayout) tabs.getChildAt(0);
        for (int i = 0; i < mTabsLinearLayout.getChildCount(); i++) {
            TextView tv = (TextView) mTabsLinearLayout.getChildAt(i);

            if (i == 0) {
                tv.setTextColor(Color.BLACK);
            } else {
                tv.setTextColor(Color.GRAY);
            }
        }
        tabs.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < mTabsLinearLayout.getChildCount(); i++) {
                    TextView tv = (TextView) mTabsLinearLayout.getChildAt(i);
                    if (i == position) {
                        tv.setTextColor(Color.BLACK);
                    } else {
                        tv.setTextColor(Color.GRAY);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        switchTo(0);

    }

    private void switchTo(int position) {
        mAdapter.switchTO(position);
        viewPager.setCurrentItem(position);
    }

    @Override
    protected void initData() {

    }

}
