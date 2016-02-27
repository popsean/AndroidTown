package com.pop.sean.androidtown.view.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Menu;
import android.view.View;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.pop.sean.androidtown.R;
import com.pop.sean.androidtown.statics.Constant;
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
            setToolBarTitle(Constant.RECOMMEND_PAGE);
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
                                Fragment f1 = EditorsChoiceFragment.newInstance(Constant.RECOMMEND_PAGE);
                                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, f1).commit();
                                setToolBarTitle(Constant.RECOMMEND_PAGE);
                                Log.d("TOWN", "1");
                                break;
                            case 2:
                                Fragment f2 = MomentsFragment.newInstance(Constant.RELATIONSHIP_PAGE);
                                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, f2).commit();
                                setToolBarTitle(Constant.RELATIONSHIP_PAGE);
                                Log.d("TOWN", "2");
                                break;
                            case 3:
                                Log.d("TOWN", "3");
                                Fragment f3 = MomentsFragment.newInstance(Constant.FAVORITE_PAGE);
                                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, f3).commit();
                                setToolBarTitle(Constant.FAVORITE_PAGE);
                                break;
                            case 4:
                                Fragment f4 = MomentsFragment.newInstance(Constant.FEED_PAGE);
                                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, f4).commit();
                                setToolBarTitle(Constant.FEED_PAGE);
                                break;
                            case 5:
                                Fragment f5 = MomentsFragment.newInstance(Constant.CITY_PAGE);
                                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, f5).commit();
                                setToolBarTitle(Constant.CITY_PAGE);
                                break;
                            case 6:
                                Fragment f6 = MomentsFragment.newInstance(Constant.MESSAGE_PAGE);
                                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, f6).commit();
                                setToolBarTitle(Constant.MESSAGE_PAGE);
                                break;
                            case 7:
                                Fragment f7 = MomentsFragment.newInstance(Constant.SETTING_PAGE);
                                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, f7).commit();
                                setToolBarTitle(Constant.SETTING_PAGE);
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
