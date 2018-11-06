package com.hans.demo.mc;


import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.FrameLayout;

import com.hans.demo.A1;
import com.hans.demo.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by sudi on 2017/10/20.
 * Email：sudi@yiche.com
 */

public class ParamView implements View.OnClickListener {

    private FrameLayout topCarFrame;                            //顶部车型一行
    private CheckBox mHideSame;                                 //隐藏相同
    private McCompareHeaderRecyclerView mHeaderContentView;     //车型的recyclerview
    private DispatchFrameLayout mListParent;                    //分发事件的父容器

    private RecyclerView mContentView;                          //下面参数的纵向适配器

    private LinesAdapter mContentAdapter;                      //内容的纵向适配器
    private TitleAdapter mTitleAdapter;                 //车型的适配器

    private CellsScrollHandler mScrollHandler;          //控制参数条目跟随滑动

    private Activity mActivity;
    private McCompareController mController;

    public ParamView(Activity activity, View view) {
        mActivity = activity;
        prepare(view);
    }

    public void setController(McCompareController controller) {
        mController = controller;
    }

    private void prepare(View view) {

        mScrollHandler = new CellsScrollHandler();

        topCarFrame = view.findViewById(R.id.top_line);
        mListParent = (DispatchFrameLayout) view.findViewById(R.id.df_frame);
        mContentView = (RecyclerView) view.findViewById(R.id.rv_params);
        mHideSame = (CheckBox) view.findViewById(R.id.cb_hide_same);


        mHideSame.setOnClickListener(this);

        mHeaderContentView = (McCompareHeaderRecyclerView) view.findViewById(R.id.rv_car);
        mHeaderContentView.setScrollHandler(mScrollHandler);


        mContentView.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false));
        if (mContentAdapter == null) {
            mContentAdapter = new LinesAdapter(mActivity, mScrollHandler);
        }
        mContentView.setAdapter(mContentAdapter);

        mContentView.addItemDecoration(new ItemDecoration(ItemDecoration.ORI_H));

        mHeaderContentView.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.HORIZONTAL, false));
        if (mTitleAdapter == null) {
            mTitleAdapter = new TitleAdapter(new TitleAdapter.IAddCarEvent() {
                @Override
                public void deleteCar(McCarSummary data) {
                    mController.deleteCar(data);

                }

                @Override
                public void addCar(int position) {
                    mController.addCar();
                }
            });
        }

        ItemTouchHelper.Callback callback = new ItemTouchHelper.Callback() {
            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                int dragFlag = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
                int swipeFlag = ItemTouchHelper.START | ItemTouchHelper.END;
                return makeMovementFlags(dragFlag, 0);
            }

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                if (mTitleAdapter == null) {
                    return false;
                }
                int targetPos = target.getAdapterPosition();
                if (targetPos == mTitleAdapter.getItemCount() - 1) {
                    targetPos = mTitleAdapter.getItemCount() - 2;
                }
                mTitleAdapter.onMove(viewHolder.getAdapterPosition(), targetPos);
                return true;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

            }
        };
        mHeaderContentView.setAdapter(mTitleAdapter);
        ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(mHeaderContentView);

        mListParent.setDispatchTouchEventListener(new DispatchFrameLayout.DispatchTouchEventListener() {
            @Override
            public boolean dispatchTouchEvent(MotionEvent ev) {
                mHeaderContentView.onFakeTouchEvent(ev);
                return true;
            }
        });
    }


    public McCompareHeaderRecyclerView getScrollView() {
        return mHeaderContentView;
    }


    public void setData(List<McCarSummary> model_infos, List<McParamsModel.McLineBean> lines, SparseArray<McParamsModel> heads) {
        mTitleAdapter.setData(model_infos);
        mContentAdapter.setData(lines);
    }

    public void recycle() {
        mScrollHandler.clear();
    }

    @Override
    public void onClick(View v) {
        if (v == mHideSame) {
            mController.hideSameTo(mHideSame.isChecked());
        }
    }


    public static class ItemDecoration extends RecyclerView.ItemDecoration {
        int divHeight = 1;
        private Drawable mDivider;
        public static final int ORI_H = 0;
        public static final int ORI_V = 1;
        private int oritation = ORI_H;

        public ItemDecoration(int oritation) {
            mDivider = new ColorDrawable(Color.BLACK);
            this.oritation = oritation;
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
                final int right = left + divHeight;
                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(c);
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
                final int bottom = top + divHeight;
                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(c);
            }
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView
                parent, RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            if (oritation == ORI_H) {
                outRect.set(0, 0, 0, divHeight);
            } else if (oritation == ORI_V) {
                outRect.set(0, 0, divHeight, 0);
            }

        }
    }
}
