package com.hans.demo.mc;

/**
 * Created by sudi on 2017/10/20.
 * Email：sudi@yiche.com
 */

public interface CellsContainer {

    void dealScrollTo(int x, int y);

    /**
     * 指定到某位置
     *
     * @param position
     * @param positionOffset
     */
    void initOffset(int position, int positionOffset);
}
