package com.pop.sean.androidtown.view.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.astuetz.PagerSlidingTabStrip;
import com.pop.sean.androidtown.R;
import com.pop.sean.androidtown.view.adapter.EditorsChoicePagerAdapter;

import butterknife.Bind;

public class EditorsChoiceActivity extends BaseDrawerActivity {

    @Bind(R.id.view_pager)
    ViewPager viewPager;
    @Bind(R.id.tabs)
    PagerSlidingTabStrip tabs;

    private LinearLayout mTabsLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editors_choice);
        initViews();
    }

    private void initViews() {
        // Initialize the ViewPager and set an adapter
        viewPager.setAdapter(new EditorsChoicePagerAdapter(getSupportFragmentManager()));

        // Bind the tabs to the ViewPager
        tabs.setViewPager(viewPager);
        mTabsLinearLayout = (LinearLayout) tabs.getChildAt(0);
        for(int i=0; i < mTabsLinearLayout.getChildCount(); i++){
            TextView tv = (TextView) mTabsLinearLayout.getChildAt(i);

            if(i == 0){
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
                for(int i=0; i < mTabsLinearLayout.getChildCount(); i++){
                    TextView tv = (TextView) mTabsLinearLayout.getChildAt(i);
                    if(i == position){
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
        viewPager.setCurrentItem(0);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return false;
    }

}
