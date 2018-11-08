package com.didi.theonebts.minecraft.car.compare;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * 分割线
 *
 * @Author hanbo
 * @Since 2018/11/6
 */
public class McComparetemDecoration extends RecyclerView.ItemDecoration {
    private int divWidth = McCompareCalculate.dP2px(0.5f);
    private boolean mIsVerticalList = true;
    private int startPadding, endPadding;
    private Rect lineRect = new Rect();
    private Paint mPaint = new Paint();

    public McComparetemDecoration(boolean isVerticalList) {
        mPaint.setColor(Color.parseColor("#18000000"));
        this.mIsVerticalList = isVerticalList;
    }

    public McComparetemDecoration(boolean isVerticalList, int startPadding, int endPadding) {
        this(isVerticalList);
        this.startPadding = startPadding;
        this.endPadding = endPadding;
    }

    public McComparetemDecoration(int color, boolean isVerticalList, int startPadding, int endPadding) {
        this(isVerticalList, startPadding, endPadding);
        mPaint.setColor(color);
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        if (mIsVerticalList) {
            drawHDiv(c, parent);
        } else {
            drawVDiv(c, parent);
        }
    }

    //画竖条
    private void drawVDiv(Canvas c, RecyclerView parent) {
        int top = parent.getPaddingTop() + startPadding;
        int bottom = parent.getHeight() - parent.getPaddingBottom() - endPadding;
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            if (!needDraw(parent, child)) {
                continue;
            }
            //获得child的布局信息
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int left = params.rightMargin + child.getRight();
            final int right = left + divWidth;
            lineRect.set(left, top, right, bottom);
            c.drawRect(lineRect, mPaint);
        }
    }

    // 画横条
    private void drawHDiv(Canvas c, RecyclerView parent) {
        int left = parent.getPaddingLeft() + startPadding;
        int right = parent.getWidth() - parent.getPaddingRight() - endPadding;
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            if (!needDraw(parent, child)) {
                continue;
            }
            //获得child的布局信息
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int top = child.getBottom() + params.bottomMargin;
            final int bottom = top + divWidth;
            lineRect.set(left, top, right, bottom);
            c.drawRect(lineRect, mPaint);
        }
    }


    /**
     * 判断是否需要绘制分割线
     *
     * @param parent RecyclerView
     * @param child  当前itemview
     * @return
     */
    protected boolean needDraw(RecyclerView parent, View child) {
        return true;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView
            parent, RecyclerView.State state) {
        if (mIsVerticalList) {
            if (needDraw(parent, view)) {
                outRect.set(0, 0, 0, divWidth);
            }
        } else {
            if (needDraw(parent, view)) {
                outRect.set(0, 0, divWidth, 0);
            }
        }
    }
}
