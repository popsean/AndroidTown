package com.pop.sean.androidtown.view.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Menu;

import com.pop.sean.androidtown.R;
import com.pop.sean.androidtown.statics.Constant;
import com.pop.sean.androidtown.view.fragment.EditorsChoiceFragment;

public class MainActivity extends BaseActivity {

    private static MainActivity instance;

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
        instance = this;
        initViews();
    }

    public static MainActivity getInstance(){
        return instance;
    }

    private void initViews() {
//        if (getDrawer() != null) {
//            getDrawer().setOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
//                @Override
//                public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
//
//                    return false;
//                }
//            });
//        }
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
