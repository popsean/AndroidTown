package com.pop.sean.androidtown.view.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Menu;
import android.view.View;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.pop.sean.androidtown.R;
import com.pop.sean.androidtown.view.fragment.EditorsChoiceFragment;
import com.pop.sean.androidtown.view.fragment.MomentsFragment;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        onCreateDrawer(savedInstanceState);
        if (savedInstanceState == null) {
            Fragment f = EditorsChoiceFragment.newInstance("EDITORS' CHOICE");
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, f).commit();
        }

        initViews();
    }

    private void initViews() {
        if (getDrawer() != null) {
            getDrawer().setOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                @Override
                public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                    if (drawerItem != null) {

                        switch (position) {
                            case 1:
                                Fragment f1 = EditorsChoiceFragment.newInstance("EDITORS' CHOICE");
                                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, f1).commit();
                                Log.d("TOWN", "1");
                                break;
                            case 2:
                                Fragment f2 = MomentsFragment.newInstance("FOLLOW");
                                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, f2).commit();
                                Log.d("TOWN", "2");
                                break;
                            case 3:
                                Log.d("TOWN", "3");
                                Fragment f3 = MomentsFragment.newInstance("FAVORITE");
                                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, f3).commit();
                                break;
                            case 4:
                                Fragment f4 = MomentsFragment.newInstance("FEED");
                                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, f4).commit();
                                break;
                            case 5:
                                Fragment f5 = MomentsFragment.newInstance("CITY");
                                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, f5).commit();
                                break;
                            case 6:
                                Fragment f6 = MomentsFragment.newInstance("MESSAGE");
                                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, f6).commit();
                                break;
                            case 7:
                                Fragment f7 = MomentsFragment.newInstance("SETTING");
                                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, f7).commit();
                                break;
                        }
                        if (getDrawer().isDrawerOpen()) {
                            getDrawer().closeDrawer();
                        }

                    }
                    return false;
                }
            });
        }
    }




//    private void initViews() {
//        // Initialize the ViewPager and set an adapter
//        viewPager.setAdapter(new EditorsChoicePagerAdapter(getSupportFragmentManager()));
//
//        // Bind the tabs to the ViewPager
//        tabs.setViewPager(viewPager);
//        mTabsLinearLayout = (LinearLayout) tabs.getChildAt(0);
//        for(int i=0; i < mTabsLinearLayout.getChildCount(); i++){
//            TextView tv = (TextView) mTabsLinearLayout.getChildAt(i);
//
//            if(i == 0){
//                tv.setTextColor(Color.BLACK);
//            } else {
//                tv.setTextColor(Color.GRAY);
//            }
//        }
//        tabs.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//                for(int i=0; i < mTabsLinearLayout.getChildCount(); i++){
//                    TextView tv = (TextView) mTabsLinearLayout.getChildAt(i);
//                    if(i == position){
//                        tv.setTextColor(Color.BLACK);
//                    } else {
//                        tv.setTextColor(Color.GRAY);
//                    }
//                }
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });
//        viewPager.setCurrentItem(0);
//
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return false;
    }

}
