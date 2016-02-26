package com.pop.sean.androidtown.view.activity;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.pop.sean.androidtown.R;
import com.pop.sean.androidtown.util.ScreenUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {


    @Bind(R.id.toolbar)
    protected Toolbar toolbar;
    protected Drawer drawer;
    protected AccountHeader headerResult = null;
    protected IProfile profile;

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        bindViews();

    }

    protected void bindViews() {
        ButterKnife.bind(this);
        setUpToolBar();
    }

    protected void setUpToolBar() {
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            setToolBarTitle("town");
        }
    }

    protected void onCreateDrawer(Bundle savedInstanceState) {
        PrimaryDrawerItem item1 = new PrimaryDrawerItem().withName("item1");
        SecondaryDrawerItem item2 = new SecondaryDrawerItem().withName("item2");

        //create profile
        profile = new ProfileDrawerItem().withName("Sean Zhao").withIcon(getResources().getDrawable(R.mipmap.ic_launcher));

        //create header
        buildHeader(false, savedInstanceState);

        //create drawer
        drawer = new DrawerBuilder()
                .withActivity(this)
                .withAccountHeader(headerResult)
                .withToolbar(toolbar)
                .withFullscreen(true)
                .withDrawerWidthPx(ScreenUtils.getScreenRealWidth(this))
                .addDrawerItems(
                        new PrimaryDrawerItem().withName("EDITORS' CHOICE"),
                        new PrimaryDrawerItem().withName("FOLLOW"),
                        new PrimaryDrawerItem().withName("FAVORITE"),
                        new PrimaryDrawerItem().withName("FEED"),
                        new PrimaryDrawerItem().withName("CITY"),
                        new PrimaryDrawerItem().withName("MESSAGE"),
                        new PrimaryDrawerItem().withName("SETTING")
                ).build();

    }

    public Drawer getDrawer() {
        return drawer;
    }

    public Toolbar getToolbar() {
        return toolbar;
    }

    public void setToolBarTitle(String title) {
        getSupportActionBar().setTitle(title);
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
}
