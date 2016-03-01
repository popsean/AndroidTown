package com.pop.sean.androidtown;

import android.content.Context;

import com.pop.sean.androidtown.util.ScreenUtils;

/**
 * Created by sean on 3/1/16.
 */
public class ATownEnv {
    private static ATownEnv instance;
    private static Context context;

    private static int screenWidth;
    private static int screenHeight;

    public ATownEnv() {
    }

    public static ATownEnv getInstace(){
        if (instance == null){
            instance = new ATownEnv();
        }
        return instance;
    }

    public static void init(Context c){
        context = c;

        screenHeight = ScreenUtils.getScreenRealHeight(context);
        screenWidth = ScreenUtils.getScreenRealWidth(context);
    }


    public static int getScreenWidth() {
        return screenWidth;
    }

    public static int getScreenHeight() {
        return screenHeight;
    }
}
