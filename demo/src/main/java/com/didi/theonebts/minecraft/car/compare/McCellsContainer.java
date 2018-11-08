package com.didi.theonebts.minecraft.car.compare;

/**
 * 滑动同步回调接口
 *
 * @author hanbo
 * @date 2018/11/5
 */

public interface McCellsContainer {

    void dealScrollTo(int offset, int position);

    /**
     * 指定到某位置
     *
     * @param position
     * @param positionOffset
     */
    void initOffset(int position, int positionOffset);
}
