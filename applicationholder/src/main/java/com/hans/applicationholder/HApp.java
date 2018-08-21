package com.hans.applicationholder;

import android.app.Application;
import android.util.DisplayMetrics;
import android.util.TypedValue;

/**
 * Library 从这里取App用
 * <p>
 * Author hanbo
 * Date 2018/8/20
 */
public class HApp extends Application {


    private volatile static HApp mInstance;
    private Application mApp;
    private boolean mDebug;
    private DisplayMetrics mDisplayMetrics;


    public static Application getApplication() {
        return checkNotNull(mInstance).mApp;
    }

    private HApp(Application app, boolean debug) {
        mApp = app;
        mDebug = debug;
        mDisplayMetrics = app.getResources().getDisplayMetrics();
    }

    /**
     * 绑定-初始化
     *
     * @param application Application
     * @param debug       debug
     * @param force       是否强制替换
     */
    public static synchronized void bind(Application application, boolean debug, boolean force) {
        if (mInstance == null || force) {
            mInstance = new HApp(application, debug);
        }
    }

    /**
     * 解绑
     */
    public synchronized static void unbind() {
        mInstance._unbind();
        mInstance = null;
    }


    /**
     * 是否可用
     *
     * @return 是否可用
     */
    public static boolean isOK() {
        return mInstance != null;
    }


    public static int dp2px(float dp) {
        checkNotNull(mInstance);
        return mInstance._dp2px(dp);
    }

    public static int sp2px(float sp) {
        checkNotNull(mInstance);
        return mInstance._sp2px(sp);
    }


    public static DisplayMetrics getDisplayMetrics() {
        return checkNotNull(mInstance).mDisplayMetrics;
    }

    private void _unbind() {
        mApp = null;
        mDisplayMetrics = null;
    }


    private int _dp2px(float dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, mDisplayMetrics);
    }

    private int _sp2px(float sp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, mDisplayMetrics);
    }


    private static <T> T checkNotNull(T reference) {
        if (reference == null) {
            throw new NullPointerException();
        }
        return reference;
    }
}
