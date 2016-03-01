package com.pop.sean.androidtown.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.pop.sean.androidtown.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * TODO: document your custom view class.
 */
public class DrawerMenuView extends LinearLayout {

    @Bind(R.id.iv_message)
    ImageView ivMessage;
    @Bind(R.id.btnAvater)
    ImageButton btnAvater;
    @Bind(R.id.btnSetting)
    ImageButton btnSetting;
    @Bind(R.id.btnFavorite)
    ImageButton btnFavorite;
    @Bind(R.id.btnFollow)
    ImageButton btnFollow;
    @Bind(R.id.btnFeed)
    ImageButton btnFeed;
    @Bind(R.id.btnCity)
    ImageButton btnCity;
    @Bind(R.id.btnEditorsChoice)
    ImageButton btnEditorsChoice;
    @Bind(R.id.btnAdd)
    ImageButton btnAdd;

    private OnDrawerItemClickListener listener;

    public DrawerMenuView(Context context) {
        super(context);
        init();
    }

    public DrawerMenuView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DrawerMenuView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public DrawerMenuView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }


    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_drawer_menu, this, true);
        ButterKnife.bind(this);
//        findViewById(R.id.iv_message).setOnClickListener(this);
//        findViewById(R.id.imageButton).setOnClickListener(this);
//        findViewById(R.id.bt1).setOnClickListener(this);
//        findViewById(R.id.bt2).setOnClickListener(this);
//        findViewById(R.id.bt3).setOnClickListener(this);
    }
    
//    @Override
//    protected void onAttachedToWindow() {
//        super.onAttachedToWindow();
//        ButterKnife.bind(this);
//    }


    @OnClick({R.id.iv_message, R.id.btnAvater, R.id.btnSetting, R.id.btnFavorite, R.id.btnFollow, R.id.btnFeed, R.id.btnCity, R.id.btnEditorsChoice, R.id.btnAdd})
    public void onClick(View view) {
        listener.onItemClick(view);
    }

    public interface OnDrawerItemClickListener{
        boolean onItemClick(View view);
    }

    public void setOnDrawerItemClickListener(OnDrawerItemClickListener onDrawerItemClickListener) {
        this.listener = onDrawerItemClickListener;
    }
}
