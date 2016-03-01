package com.pop.sean.androidtown;

import android.app.Application;

/**
 * Created by sean on 2/24/16.
 */
public class ATownApplication extends Application {

    private static ATownApplication instace;

    public static ATownApplication getInstance() {
        return instace;
    }

    @Override
    public void onCreate() {
        super.onCreate();
//        Fresco.initialize(getApplicationContext());
        instace = this;

        ATownEnv.getInstace().init(this);
    }
}
