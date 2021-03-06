package com.hans.demo.mc;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;


public class McCompareHeaderRecyclerView extends RecyclerView implements CellsContainer {
    private LinearLayoutManager mLayoutManager;

    private McCellsScrollHandler mHandler;
    private int currentX;

    public McCompareHeaderRecyclerView(Context context) {
        super(context);
    }

    public McCompareHeaderRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public McCompareHeaderRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void onScrolled(int dx, int dy) {
        currentX += dx;
        View topView = getLayoutManager().getChildAt(0);      //获取可视的第一个view
        int offset = topView.getLeft();                             //获取与该view的顶部的偏移量
        int position = getLayoutManager().getPosition(topView);     //得到该View的数组位置
        if (mHandler != null) {
            mHandler.notifyScrollTo(offset, position, this);
            mHandler.recordCurrentPosition(offset, position);
        }
        super.onScrolled(dx, dy);
    }

    public int getCurrentX() {
        return currentX;
    }

    public void setScrollHandler(McCellsScrollHandler handler) {
        mHandler = handler;
    }


    public void onFakeTouchEvent(MotionEvent ev) {
        super.onTouchEvent(ev);
    }

    public int getMaxWidth() {
        return getAdapter().getItemCount() * McCompareCalculate.DEFAULT_WIDTH;
    }

    @Override
    public void setLayoutManager(LayoutManager layout) {
        super.setLayoutManager(layout);
        mLayoutManager = (LinearLayoutManager) layout;
    }

    @Override
    public void dealScrollTo(int offset, int position) {
        if (mLayoutManager == null) {
            return;
        }
        ((LinearLayoutManager) getLayoutManager()).scrollToPositionWithOffset(position, offset);
    }

    @Override
    public void initOffset(int position, int positionOffset) {
        if (mLayoutManager == null) {
            mLayoutManager = (LinearLayoutManager) getLayoutManager();
        }
        mLayoutManager.scrollToPositionWithOffset(position, positionOffset);
    }

}
