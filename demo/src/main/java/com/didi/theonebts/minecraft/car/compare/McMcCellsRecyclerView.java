package com.didi.theonebts.minecraft.car.compare;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * 参数条目横向的recyclerview
 * 隐藏了触摸事件处理，所有动作由外部调动
 *
 * @author hanbo
 * @date 2018/11/5
 */

public class McMcCellsRecyclerView extends RecyclerView implements McCellsContainer {

    private LinearLayoutManager mLayoutManager;

    public McMcCellsRecyclerView(Context context) {
        super(context);
    }

    public McMcCellsRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public McMcCellsRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
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
        if (mLayoutManager != null) {
            mLayoutManager.scrollToPositionWithOffset(position, positionOffset);
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent e) {
        return true;
    }
}
