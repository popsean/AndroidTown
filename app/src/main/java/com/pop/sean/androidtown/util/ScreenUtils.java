package com.pop.sean.androidtown.util;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

/**
 * Created by sean on 2/26/16.
 */
public class ScreenUtils {

    public static int getScreenRealHeight(Context context){
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        Display d = wm.getDefaultDisplay();

        DisplayMetrics mDisplayMetrics = new DisplayMetrics();
        d.getRealMetrics(mDisplayMetrics);
        return mDisplayMetrics.heightPixels;
    }

    public static int getScreenRealWidth(Context context){
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        Display d = wm.getDefaultDisplay();

        DisplayMetrics mDisplayMetrics = new DisplayMetrics();
        d.getRealMetrics(mDisplayMetrics);
        return mDisplayMetrics.widthPixels;
    }

}
