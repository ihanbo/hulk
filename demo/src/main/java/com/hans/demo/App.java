package com.hans.demo;

import android.app.Application;

/**
 * @author hanbo
 * @date 2018/11/6
 */
public class App extends Application {
    public static App mApp;

    @Override
    public void onCreate() {
        super.onCreate();
        mApp = this;
    }
}
