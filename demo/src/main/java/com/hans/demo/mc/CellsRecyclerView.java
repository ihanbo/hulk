package com.hans.demo.mc;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * 参数条目横向的recyclerview
 * 隐藏了触摸事件处理，所有动作由外部调动
 */

public class CellsRecyclerView extends RecyclerView implements CellsContainer {

    private LinearLayoutManager mLayoutManager;

    public CellsRecyclerView(Context context) {
        super(context);
        init();
    }

    public CellsRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CellsRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
    }

    @Override
    public void setLayoutManager(LayoutManager layoutManager) {
        super.setLayoutManager(layoutManager);
        mLayoutManager = (LinearLayoutManager) layoutManager;
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


    @Override
    public boolean onTouchEvent(MotionEvent e) {
        return true;
    }
}
