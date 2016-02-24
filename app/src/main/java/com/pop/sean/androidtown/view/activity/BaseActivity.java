package com.pop.sean.androidtown.view.activity;

import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.pop.sean.androidtown.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class BaseActivity extends AppCompatActivity {


    @Bind(R.id.toolbar)
    protected Toolbar toolbar;

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        bindViews();
    }

    protected void bindViews(){
        ButterKnife.bind(this);
        setUpToolBar();
    }

    protected void setUpToolBar(){
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
    }


    public Toolbar getToolbar() {
        return toolbar;
    }

}
