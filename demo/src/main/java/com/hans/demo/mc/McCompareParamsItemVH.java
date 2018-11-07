package com.hans.demo.mc;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static android.widget.LinearLayout.VERTICAL;

/**
 * 参数单元格视图框架
 *
 * @author hanbo
 * @date 2018/11/5
 */
public class McCompareParamsItemVH extends RecyclerView.ViewHolder {
    private McCompareTextPool mPool;
    private LinearLayout mContainer;

    public McCompareParamsItemVH(ViewGroup parent, McCompareTextPool pool) {
        this(parent, pool, McCompareCalculate.DEFAULT_WIDTH, ViewGroup.LayoutParams.MATCH_PARENT);
    }

    public McCompareParamsItemVH(ViewGroup parent, McCompareTextPool pool, int width, int height) {
        super(createView(parent, width, height));
        mPool = pool;
        mContainer = (LinearLayout) ((FrameLayout) itemView).getChildAt(0);
    }

    private static View createView(ViewGroup parent, int width, int height) {
        FrameLayout frame = new FrameLayout(parent.getContext());
        frame.setBackground(new ColorDrawable(Color.TRANSPARENT));
        LinearLayout mContainer = new LinearLayout(parent.getContext());
        mContainer.setOrientation(VERTICAL);
        mContainer.setGravity(Gravity.CENTER_HORIZONTAL);
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.gravity = Gravity.CENTER;
        frame.addView(mContainer, lp);
        int padding = McCompareCalculate.dP2px(10);
        frame.setPadding(padding, padding, padding, padding);
        frame.setLayoutParams(new RecyclerView.LayoutParams(width, height));
        return frame;
    }


    private List<String> mData;
    private List<TextView> childs = new ArrayList<>(8);

    public void setData(List<String> data, int position) {
        mData = data;
        if (mContainer.getChildCount() != 0 || childs.size() != 0) {
            //TODO REMOVE
            throw new RuntimeException("not recycle");
        }
        if (data.isEmpty()) {
            return;
        }
        for (int i = 0, len = data.size(); i < len; i++) {
            TextView tv = mPool.get();
            tv.setText(data.get(i));
            mContainer.addView(tv);
            childs.add(tv);
        }
    }


    public View getItemView() {
        return itemView;
    }

    public void recycle() {
        if (childs.isEmpty()) {
            return;
        }
        mContainer.removeAllViews();
        mPool.putAll(childs);
        childs.clear();
    }
}
