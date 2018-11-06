package com.hans.demo.mc;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * 单元格视图框架
 *
 * @author hanbo
 * @date 2018/11/5
 */
public class McCompareParamsItem extends LinearLayout {
    private McCompareTextPool mPool;

    public McCompareParamsItem(@NonNull Context context, McCompareTextPool pool) {
        super(context);
        mPool = pool;
        init();
    }

    public McCompareParamsItem(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        setBackground(new ColorDrawable(Color.TRANSPARENT));
        setOrientation(VERTICAL);
        int padding = McCompareCalculate.dP2px(10);
        setPadding(padding, 0, padding, padding);
        setGravity(Gravity.CENTER_HORIZONTAL);
    }


    private List<String> mData;
    private List<TextView> childs = new ArrayList<>(8);

    public void setData(List<String> data, int position) {
        mData = data;
        if (getChildCount() != 0) {
            throw new RuntimeException("not recycle");
        }
        if (data.isEmpty()) {
            return;
        }
        childs.clear();
        for (int i = 0, len = data.size(); i < len; i++) {
            TextView tv = mPool.get();
            tv.setText(data.get(i));
            addView(tv);
            childs.add(tv);
        }
    }


    public void recycleAll() {
        if (childs.isEmpty()) {
            return;
        }
        removeAllViews();
        mPool.putAll(childs);
        childs.clear();
    }


    public static McCompareParamsItem createForRecyclerView(Context context, McCompareTextPool pool) {
        McCompareParamsItem data = new McCompareParamsItem(context, pool);
        data.setLayoutParams(new RecyclerView.LayoutParams(McCompareCalculate.DEFAULT_WIDTH, ViewGroup.LayoutParams.WRAP_CONTENT));
        return data;
    }


}
