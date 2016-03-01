package com.pop.sean.androidtown.view.activity;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.pop.sean.androidtown.R;
import com.pop.sean.androidtown.sensor.GravitySensorFragment;
import com.pop.sean.androidtown.statics.Constant;
import com.pop.sean.androidtown.testcode.BallFragment;
import com.pop.sean.androidtown.testcode.GravitySensorFragment2;
import com.pop.sean.androidtown.testcode.RotationFragment;
import com.pop.sean.androidtown.testcode.SensorsFragment;
import com.pop.sean.androidtown.testcode.TestFragment;
import com.pop.sean.androidtown.testcode.WideDrawerLayout;
import com.pop.sean.androidtown.view.DrawerMenuView;
import com.pop.sean.androidtown.view.fragment.EditorsChoiceFragment;
import com.pop.sean.androidtown.view.fragment.MomentsListFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {


    @Bind(R.id.toolbar)
    protected Toolbar toolbar;
    @Bind(R.id.toolbar_title)
    protected TextView tvTitle;

    @Bind(R.id.drawerLayout)
    WideDrawerLayout drawerLayout;
    @Bind(R.id.drawer_menu)
    DrawerMenuView drawerMenuView;
    @Bind(R.id.drawer)
    LinearLayout drawer;

//    @Bind(R.id.vNavigation)
//    NavigationView vNavigation;

//    protected Drawer drawer;
    protected AccountHeader headerResult = null;
    protected IProfile profile;

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(R.layout.activity_drawer);
        ViewGroup viewGroup = (ViewGroup) findViewById(R.id.flContentRoot);
        LayoutInflater.from(this).inflate(layoutResID, viewGroup, true);
        bindViews();

    }

    protected void bindViews() {
        ButterKnife.bind(this);
        setUpToolBar();
        setUpDrawer();
    }

    protected void setUpToolBar() {
        if (toolbar != null) {
            toolbar.setTitle("");
            setSupportActionBar(toolbar);
            toolbar.setNavigationIcon(R.mipmap.ic_menu_white);
            setToolBarTitle("town");
            getToolbar().setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    drawerLayout.openDrawer(Gravity.LEFT);
                }
            });
        }
    }

    protected void onCreateDrawer(Bundle savedInstanceState) {

    }

    public WideDrawerLayout getDrawer() {
        return drawerLayout;
    }

    public Toolbar getToolbar() {
        return toolbar;
    }

    public void setToolBarTitle(String title) {
        tvTitle.setText(title);
    }

    private void buildHeader(boolean compact, Bundle savedInstanceState) {
        // Create the AccountHeader
        headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.color.material_drawer_dark_background)
                .withCompactStyle(compact)
                .addProfiles(profile)
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean current) {
                        //sample usage of the onProfileChanged listener
                        //if the clicked item has the identifier 1 add a new profile ;)
                        //false if you have not consumed the event and it should close the drawer
                        return false;
                    }
                })
                .withSavedInstance(savedInstanceState)
                .build();
    }

    private void setUpDrawer(){
        if (drawerMenuView!= null){
            drawerMenuView.setOnDrawerItemClickListener(new DrawerMenuView.OnDrawerItemClickListener() {
                @Override
                public boolean onItemClick(View view) {
                    if (view != null) {
//R.id.iv_message, R.id.btnAvater, R.id.btnSetting, R.id.btnFavorite, R.id.btnFollow, R.id.btnFeed, R.id.btnCity, R.id.btnEditorsChoice, R.id.btnAdd
                        switch (view.getId()) {
                            case R.id.btnEditorsChoice:
                                Fragment f1 = EditorsChoiceFragment.newInstance(Constant.RECOMMEND_PAGE);
                                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, f1).commit();
                                setToolBarTitle(Constant.RECOMMEND_PAGE);
                                Log.d("TOWN", "btnEditorsChoice");
                                break;
                            case R.id.btnFollow:
                                Fragment f2 = MomentsListFragment.newInstance(Constant.RELATIONSHIP_PAGE);
                                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, f2).commit();
                                setToolBarTitle(Constant.RELATIONSHIP_PAGE);
                                Log.d("TOWN", "2");
                                break;
                            case R.id.iv_message:
                                Log.d("TOWN", "MESSAGE");
                                Fragment f3 = BallFragment.newInstance();
                                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, f3).commit();
                                setToolBarTitle("BALL");
                                break;
                            case R.id.btnAvater:
                                Fragment f4 = SensorsFragment.newInstance();
                                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, f4).commit();
                                setToolBarTitle("senors");
                                break;
                            case R.id.btnSetting:
                                Fragment f5 = GravitySensorFragment2.newInstance();
                                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, f5).commit();
                                setToolBarTitle("SETTING");
                                break;
                            case R.id.btnFavorite:
                                Fragment f6 = TestFragment.newInstance();
                                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, f6).commit();
                                setToolBarTitle("btnFavorite");
                                break;
                            case R.id.btnFeed:
                                Fragment f7 = RotationFragment.newInstance();
                                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, f7).commit();
                                setToolBarTitle("Rotation");
                                break;
                            case R.id.btnCity:
                                Fragment f8 = GravitySensorFragment.newInstance();
                                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, f8).commit();
                                setToolBarTitle("G sensor");
                                break;
                        }
                        if (drawerLayout.isDrawerOpen(drawer)) {
                            drawerLayout.closeDrawer(drawer);
                        }

                    }
                    return false;
                }
            });
        }

    }

}
