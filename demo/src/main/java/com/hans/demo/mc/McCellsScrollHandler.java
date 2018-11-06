package com.hans.demo.mc;

import java.util.HashSet;
import java.util.Set;


public class McCellsScrollHandler {

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

    public void notifyScrollTo(int offset, int position, CellsContainer self) {
        for (CellsContainer item : mCellsContainer) {
            if (item != self) {
                item.dealScrollTo(offset, position);
            }
        }
    }

    public void recordCurrentPosition(int offset, int position) {
        this.offset = offset;
        this.position = position;

    }
}
