package com.hans.demo.mc;

import android.app.Activity;
import android.app.Application;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hans.demo.App;

import java.util.List;

/**
 * 计算用
 *
 * @author hanbo
 * @date 2018/11/5
 */
public class McCompareCalculate {

    private static DisplayMetrics displayMetrics = getApp().getResources().getDisplayMetrics();

    public static final int DEFAULT_HEIGHT = dP2px(60);
    public static final int DEFAULT_WIDTH = dP2px(145);
    public static final int KEY_WIDTH = dP2px(95);


    public static final int LINE_END_PADDING = dP2px(10);


    private TextView mKey;                      //计算key的高度
    private McCompareParamsItemVH paramsItem;   //参数单元格
    private McCompareCarItemVH carItem;         //顶部车型的条目

    private static int keyWidthMeasureSpec = View.MeasureSpec.makeMeasureSpec(KEY_WIDTH, View.MeasureSpec.EXACTLY);         //键的宽度测量spec
    private static int paramsWidthMeasureSpec = View.MeasureSpec.makeMeasureSpec(DEFAULT_WIDTH, View.MeasureSpec.EXACTLY);  //参数单元格的宽度测量spec
    private static int atMostMeasureSpec = View.MeasureSpec.makeMeasureSpec((1 << 30) - 1, View.MeasureSpec.AT_MOST);

    public McCompareCalculate(Activity activity) {
        mKey = new TextView(getApp());
        displayMetrics = getApp().getResources().getDisplayMetrics();
        int dp10 = dP2px(10);
        mKey.setPadding(dp10, dp10, dp10, dp10);
        mKey.setTextSize(12, TypedValue.COMPLEX_UNIT_DIP);
        mKey.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        paramsItem = new McCompareParamsItemVH(new LinearLayout(getApp()), new McCompareTextPool(activity));
        paramsItem.getItemView().setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        carItem = new McCompareCarItemVH(new LinearLayout(getApp()), null);
        carItem.getItemView().setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

    }


    //计算行高
    public void preMeasureLineHeight(McParamsModel.McLineBean line) {
        line.measureHeight = preMeasureLineHeight(line.name, line.values);
    }

    private int preMeasureLineHeight(String key, List<List<String>> datas) {
        if (key == null && (datas == null || datas.isEmpty())) {
            return DEFAULT_HEIGHT;
        }

        mKey.setText(key);
        mKey.measure(keyWidthMeasureSpec, atMostMeasureSpec);

        int height = mKey.getMeasuredHeight();

        if (datas == null || datas.isEmpty()) {
            return height;
        }

        paramsItem.recycle();
        for (int i = 0, len = datas.size(); i < len; i++) {
            paramsItem.setData(datas.get(i), -1);
            paramsItem.getItemView().measure(paramsWidthMeasureSpec, atMostMeasureSpec);
            int i1 = paramsItem.getItemView().getMeasuredHeight();
            if (i1 > height) {
                height = i1;
            }
            paramsItem.recycle();
        }
        return height;
    }


    //计算顶部车型的高度
    public int preMeasureCarHeight(List<McCarSummary> datas) {
        if (datas == null || datas.isEmpty()) {
            return DEFAULT_HEIGHT;
        }

        int height = 0;
        for (int i = 0, len = datas.size(); i < len; i++) {
            carItem.setData(-1, datas.get(i));
            carItem.getItemView().measure(paramsWidthMeasureSpec, atMostMeasureSpec);
            int tempHeight = carItem.getItemView().getMeasuredHeight();
            if (tempHeight > height) {
                height = tempHeight;
            }
        }
        if (height == 0) {
            height = DEFAULT_HEIGHT;
        }
        return height;
    }

    static Application getApp() {
        return App.mApp;
    }


    public static int dP2px(float dp) {
        return (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, displayMetrics) + 0.5f);
    }


}
