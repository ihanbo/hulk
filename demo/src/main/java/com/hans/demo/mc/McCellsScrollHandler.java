package com.hans.demo.mc;

import java.util.HashSet;
import java.util.Set;


/**
 * 滑动同步控制
 *
 * @author hanbo
 * @date 2018/11/5
 */
public class McCellsScrollHandler {

    private Set<McCellsContainer> mMcCellsContainer;

    private int offset, position;

    public McCellsScrollHandler() {
        mMcCellsContainer = new HashSet<>();
    }

    public void regist(McCellsContainer mcCellsContainer) {
        mcCellsContainer.initOffset(position, offset);
        mMcCellsContainer.add(mcCellsContainer);
    }

    public void unregistContainer(McCellsContainer mcCellsContainer) {
        mMcCellsContainer.remove(mMcCellsContainer);
    }

    public void clear() {
        mMcCellsContainer.clear();
    }

    public void notifyScrollTo(int offset, int position) {
        for (McCellsContainer item : mMcCellsContainer) {
            item.dealScrollTo(offset, position);
        }
    }

    public void recordCurrentPosition(int offset, int position) {
        this.offset = offset;
        this.position = position;

    }
}
