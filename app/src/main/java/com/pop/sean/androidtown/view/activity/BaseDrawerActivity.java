package com.pop.sean.androidtown.view.activity;

import android.support.annotation.LayoutRes;
import android.util.Log;
import android.view.View;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

public class BaseDrawerActivity extends BaseActivity {

    protected Drawer drawer;


    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        setUpDrawer();
    }

    protected void setUpDrawer(){
        PrimaryDrawerItem item1 = new PrimaryDrawerItem().withName("item1");
        SecondaryDrawerItem item2  = new SecondaryDrawerItem().withName("item2");

        drawer = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .addDrawerItems(item1,  new DividerDrawerItem(), item2, new SecondaryDrawerItem().withName("setting"))
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        Log.d("Drawer", "onClick:"+ position);
                        return false;
                    }
                }).build();
    }

    public Drawer getDrawer() {
        return drawer;
    }


}
