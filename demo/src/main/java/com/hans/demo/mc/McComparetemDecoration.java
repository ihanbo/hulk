package com.hans.demo.mc;

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
    int divWidth = McCompareCalculate.dP2px(0.5f);
    public static final int ORI_H = 0;
    public static final int ORI_V = 1;
    private int oritation = ORI_H;

    Rect lineRect = new Rect();
    Paint mPaint = new Paint();

    public McComparetemDecoration(boolean isVerticalList) {
        mPaint.setColor(Color.parseColor("#18000000"));
        this.oritation = isVerticalList ? ORI_H : ORI_V;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        if (oritation == ORI_H) {
            drawHDiv(c, parent);
        } else if (oritation == ORI_V) {
            drawVDiv(c, parent);
        }
    }

    private void drawVDiv(Canvas c, RecyclerView parent) {
        int top = parent.getPaddingTop();
        int bottom = parent.getHeight() - parent.getPaddingBottom();
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            //获得child的布局信息
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int left = params.rightMargin + child.getRight();
            final int right = left + divWidth;
            lineRect.set(left, top, right, bottom);
            c.drawRect(lineRect, mPaint);
        }
    }

    private void drawHDiv(Canvas c, RecyclerView parent) {
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            //获得child的布局信息
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int top = child.getBottom() + params.bottomMargin;
            final int bottom = top + divWidth;
            lineRect.set(left, top, right, bottom);
            c.drawRect(lineRect, mPaint);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView
            parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        if (oritation == ORI_H) {
            outRect.set(0, 0, 0, divWidth);
        } else if (oritation == ORI_V) {
            outRect.set(0, 0, divWidth, 0);
        }

    }
}
