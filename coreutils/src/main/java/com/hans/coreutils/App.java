package com.hans.coreutils;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

/**
 * @Author hanbo
 * @Since 2018/8/28
 */
public class App extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
