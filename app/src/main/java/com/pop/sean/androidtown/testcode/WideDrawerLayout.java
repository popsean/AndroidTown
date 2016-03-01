package com.pop.sean.androidtown.testcode;

import android.content.Context;
import android.support.v4.widget.DrawerLayout;
import android.util.AttributeSet;

import com.pop.sean.androidtown.util.ScreenUtils;

/**
 * Created by sean on 2/29/16.
 */
public class WideDrawerLayout extends DrawerLayout {
    public WideDrawerLayout(Context context) {
        super(context);
    }

    public WideDrawerLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WideDrawerLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        //set drawerlayout width full screen
        int w_mode = MeasureSpec.getMode(widthMeasureSpec);
        int h_mode = MeasureSpec.getMode(heightMeasureSpec);
        int childNewWidthSpec = MeasureSpec.makeMeasureSpec(ScreenUtils.getScreenRealWidth(getContext()), w_mode);
        int childNewHeightpec = MeasureSpec.makeMeasureSpec(ScreenUtils.getScreenRealHeight(getContext()), h_mode);
        getChildAt(1).measure(childNewWidthSpec, childNewHeightpec);

    }
}
