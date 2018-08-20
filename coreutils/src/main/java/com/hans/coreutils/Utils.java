package com.hans.coreutils;

import android.app.Application;

/**
 * @Author hanbo
 * @Since 2018/8/20
 */
public class Utils {


    private static Utils mInstance;

    public static Utils getInstance() {
        if (mInstance == null) {
            mInstance = new Utils();
        }
        return mInstance;
    }

    private Application mApp;

    private Utils() {
    }


    public static void init(Application app) {
        getInstance().mApp = app;
    }


    public static Application getApp() {
        return getInstance().mApp;
    }
}
