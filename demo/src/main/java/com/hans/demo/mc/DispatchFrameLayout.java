package com.hans.demo.mc;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.FrameLayout;

/**
 * 可以提前分发事件的FrameLayout
 * Created by ihanb on 2017/10/19.
 */

public class DispatchFrameLayout extends FrameLayout {
    private int mTouchSlop;

    private DispatchTouchEventListener mDispatchTouchEventListener = new DispatchTouchEventListener() {
        @Override
        public boolean dispatchTouchEvent(MotionEvent ev) {
            return true;
        }
    };
    private boolean mIsBegainDraging;

    public DispatchFrameLayout(Context context) {
        super(context);
        init();

    }

    public DispatchFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DispatchFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        ViewConfiguration configuration = ViewConfiguration.get(getContext());
        mTouchSlop = configuration.getScaledTouchSlop();
    }


    @Override
    public void requestDisallowInterceptTouchEvent(boolean disallowIntercept) {
    }

    int mOldX;
    int mOldY;

    boolean customProcess;
    boolean hasProcessIng = false;

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        boolean result;
        int x = (int) ev.getX();
        int y = (int) ev.getY();

        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            mOldX = x;
            mOldY = y;
            hasProcessIng = false;
            mDispatchTouchEventListener.dispatchTouchEvent(ev);
            result = super.dispatchTouchEvent(ev);
        } else if (ev.getAction() == MotionEvent.ACTION_MOVE) {
            if (hasProcessIng) {
                if (customProcess) {
                    result = mDispatchTouchEventListener.dispatchTouchEvent(ev);
                } else {
                    result = super.dispatchTouchEvent(ev);
                }
            } else {
                int deltaX = x - mOldX;
                int deltaY = y - mOldY;
                int abx = Math.abs(deltaX);
                int aby = Math.abs(deltaY);
                if (abx < mTouchSlop && aby < mTouchSlop) {
                    return true;
                }

                customProcess = abx > mTouchSlop && abx > Math.abs(deltaY);
                Log.i("hh", "DispatchFrameLayout  : dispatchTouchEvent: oldx:" + mOldX + " oldy:" + mOldY + " x:" + x + " y:" + y
                        + " deltaX:" + deltaX + " deltaY:" + deltaY + "" + customProcess);
                hasProcessIng = true;
                int action = ev.getAction();
                if (customProcess) {
                    ev.setAction(MotionEvent.ACTION_CANCEL);
                    super.dispatchTouchEvent(ev);
                    ev.setAction(action);
                    result = mDispatchTouchEventListener.dispatchTouchEvent(ev);
                } else {
                    ev.setAction(MotionEvent.ACTION_CANCEL);
                    mDispatchTouchEventListener.dispatchTouchEvent(ev);
                    ev.setAction(action);
                    result = super.dispatchTouchEvent(ev);
                }
            }
        } else if (ev.getAction() == MotionEvent.ACTION_UP || ev.getAction() == MotionEvent.ACTION_CANCEL) {
            hasProcessIng = false;
            mDispatchTouchEventListener.dispatchTouchEvent(ev);
            result = super.dispatchTouchEvent(ev);
        } else {
            Log.i("hh", "DispatchFrameLayout  : dispatchTouchEvent: else");
            result = super.dispatchTouchEvent(ev);
        }

        mOldX = x;
        mOldY = y;
        return true;
    }


    public DispatchTouchEventListener getDispatchTouchEventListener() {
        return mDispatchTouchEventListener;
    }

    public void setDispatchTouchEventListener(DispatchTouchEventListener dispatchTouchEventListener) {
        mDispatchTouchEventListener = dispatchTouchEventListener;
    }


    /**
     * 只做分发
     */
    public interface DispatchTouchEventListener {
        boolean dispatchTouchEvent(MotionEvent ev);
    }
}
