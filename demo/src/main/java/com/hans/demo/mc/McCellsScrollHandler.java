package com.hans.demo.mc;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.view.MotionEvent;

import java.util.HashSet;
import java.util.Set;


public class McCellsScrollHandler extends OnScrollListener {

    private Set<CellsContainer> mCellsContainer;

    private int offset, position;

    public McCellsScrollHandler() {
        mCellsContainer = new HashSet<>();
    }

    public void regist(CellsContainer cellsContainer) {
        cellsContainer.initOffset(position, offset);
        mCellsContainer.add(cellsContainer);
    }

    public void unregistContainer(CellsContainer cellsContainer) {
        mCellsContainer.remove(mCellsContainer);
    }

    public void clear() {
        mCellsContainer.clear();
    }

    public void notifyScrollTo(int offset, int position) {
        for (CellsContainer item : mCellsContainer) {
            item.dealScrollTo(offset, position);
        }
    }

    public void recordCurrentPosition(int offset, int position) {
        this.offset = offset;
        this.position = position;

    }

    public boolean onTouchEvent(MotionEvent e) {
        if (mHeaderRecyclerView != null) {
            mHeaderRecyclerView.dispatchTouchEvent(e);
        }
        return true;
    }

    McCompareHeaderRecyclerView mHeaderRecyclerView;

    public void setTouchEventRecieve(McCompareHeaderRecyclerView mcCompareHeaderRecyclerView) {
        mHeaderRecyclerView = mcCompareHeaderRecyclerView;
    }
}
