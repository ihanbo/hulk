package com.hans.demo.mc;


import android.app.Activity;
import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.Toast;

import com.hans.demo.R;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersDecoration;

import java.util.List;

import static com.hans.demo.mc.McCompareCalculate.LINE_END_PADDING;


/**
 * Created by sudi on 2017/10/20.
 * Email：sudi@yiche.com
 */

public class McParamView implements View.OnClickListener {


    private View mBackBtn;
    private View mMenuBtn;

    private FrameLayout mMenuFrame;
    private GridView mMenu;                                    //菜单
    private FrameLayout topCarFrame;                            //顶部车型一行
    private CheckBox mHideSame;                                 //隐藏相同
    private McCompareHeaderRecyclerView mHeaderContentView;     //车型的recyclerview


    private DispatchFrameLayout mListParent;                    //分发事件的父容器
    private RecyclerView mParamsView;                          //下面参数的纵向适配器


    private McParamsLinesAdapter mParamsAdapter;                        //内容的纵向适配器
    private McCompareCarAdapter mCarAdapter;                    //车型的适配器
    private McMenuAdapter mMenuAdapter;

    private McCellsScrollHandler mScrollHandler;                //控制参数条目跟随滑动

    private Activity mActivity;
    private McCompareController mController;

    public McParamView(Activity activity, View view) {
        mActivity = activity;
        prepare(view);
    }

    public void setController(McCompareController controller) {
        mController = controller;
    }

    private void prepare(View view) {
        mScrollHandler = new McCellsScrollHandler();

        mBackBtn = view.findViewById(R.id.iv_back);
        mMenuBtn = view.findViewById(R.id.iv_right);
        mBackBtn.setOnClickListener(this);
        mMenuBtn.setOnClickListener(this);

        //菜单视图
        mMenuFrame = view.findViewById(R.id.menu_frame);
        mMenu = mMenuFrame.findViewById(R.id.grid);
        mMenuFrame.setOnClickListener(this);
        mMenuAdapter = new McMenuAdapter(null, new McMenuAdapter.FilterCallBack() {
            @Override
            public void select(McParamsModel data, int pos) {
                mController.jumpTo(data, pos);
            }

            @Override
            public void dismissMenu() {
                mMenuFrame.setVisibility(View.GONE);
            }
        });
        mMenu.setAdapter(mMenuAdapter);

        //隐藏相同
        mHideSame = (CheckBox) view.findViewById(R.id.cb_hide_same);
        mHideSame.setOnClickListener(this);

        //车型行
        topCarFrame = view.findViewById(R.id.top_line);
        mHeaderContentView = (McCompareHeaderRecyclerView) view.findViewById(R.id.rv_car);
        mHeaderContentView.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.HORIZONTAL, false));
        mHeaderContentView.addItemDecoration(new McStartEndMarginDecoration());
        mHeaderContentView.setScrollHandler(mScrollHandler);
        if (mCarAdapter == null) {
            mCarAdapter = new McCompareCarAdapter(new McCompareCarAdapter.IAddCarEvent() {
                @Override
                public void deleteCar(McCarSummary data, int pos) {
                    mController.deleteCar(data);

                }

                @Override
                public void addCar(int position) {
                    mController.addCar();
                }
            });
        }
        mHeaderContentView.setAdapter(mCarAdapter);

        //拖动处理
        ItemTouchHelper helper = new ItemTouchHelper(new McDragCallBack(mCarAdapter));
        helper.attachToRecyclerView(mHeaderContentView);


        //事件分发
        mListParent = (DispatchFrameLayout) view.findViewById(R.id.df_frame);
        mListParent.setDispatchTouchEventListener(new DispatchFrameLayout.DispatchTouchEventListener() {
            @Override
            public boolean dispatchTouchEvent(MotionEvent ev) {
                mHeaderContentView.onFakeTouchEvent(ev);
                return true;
            }
        });

        //参数
        mParamsView = (RecyclerView) view.findViewById(R.id.rv_params);
        if (mParamsAdapter == null) {
            mParamsAdapter = new McParamsLinesAdapter(mActivity, mScrollHandler);
        }
        mParamsView.setAdapter(mParamsAdapter);
        mParamsView.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false));
        mParamsView.addItemDecoration(new McComparetemDecoration(true ));
        mParamsView.addItemDecoration(new StickyRecyclerHeadersDecoration(mParamsAdapter));
    }


    /**
     * @param model_infos   车型数据
     * @param lines         参数数据
     * @param heads         吸顶头部数据
     * @param carMostHeight 车型栏的测量高度
     */
    public void setData(List<McCarSummary> model_infos, List<McParamsModel.McLineBean> lines,
                        SparseArray<McParamsModel> heads, int carMostHeight) {
        mCarAdapter.setData(model_infos);
        mParamsAdapter.setData(lines, heads);
        mMenuAdapter.setData(heads);
        ViewGroup.LayoutParams layoutParams = topCarFrame.getLayoutParams();
        layoutParams.height = carMostHeight;
        topCarFrame.setLayoutParams(layoutParams);
    }

    /**
     * @param lines 参数数据
     * @param heads 吸顶头部数据
     */
    public void setDataWithoutCar(List<McParamsModel.McLineBean> lines, SparseArray<McParamsModel> heads) {
        mParamsAdapter.setData(lines, heads);
        mMenuAdapter.setData(heads);
    }

    public void recycle() {
        mScrollHandler.clear();
    }

    @Override
    public void onClick(View v) {
        if (v == mHideSame) {
            mController.hideSameTo(mHideSame.isChecked());
        } else if (v == mBackBtn) {
            mActivity.onBackPressed();
        } else if (v == mMenuBtn) {
            mController.openMenu();
        } else if (v == mMenuFrame) {
            mMenuFrame.setVisibility(View.GONE);
        }
    }


    public void scrollTo(int pos) {
        LinearLayoutManager manager = (LinearLayoutManager) mParamsView.getLayoutManager();
        if (manager == null) {
            Toast.makeText(mActivity, "跳转出错", Toast.LENGTH_SHORT).show();
            return;
        }
        manager.scrollToPositionWithOffset(pos, 0);
    }

    public RecyclerView getParamsView() {
        return mParamsView;
    }

    public void showMenu(int selectpos) {
        mMenuAdapter.setSelectPos(selectpos);
        mMenuFrame.setVisibility(mMenuFrame.isShown() ? View.GONE : View.VISIBLE);
    }

    public McParamsLinesAdapter getParamsAdapter() {
        return mParamsAdapter;
    }


    //拖拽的处理
    static class McDragCallBack extends ItemTouchHelper.Callback {

        McCompareCarAdapter mCarAdapter;

        public McDragCallBack(McCompareCarAdapter carAdapter) {
            mCarAdapter = carAdapter;
        }

        @Override
        public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            int dragFlag = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
            int swipeFlag = ItemTouchHelper.START | ItemTouchHelper.END;
            return makeMovementFlags(dragFlag, 0);
        }

        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            if (mCarAdapter == null) {
                return false;
            }
            int targetPos = target.getAdapterPosition();
            if (targetPos == mCarAdapter.getItemCount() - 1) {
                targetPos = mCarAdapter.getItemCount() - 2;
            }
            mCarAdapter.onMove(viewHolder.getAdapterPosition(), targetPos);
            return true;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

        }
    }


    //设置margin
    public static class McStartEndMarginDecoration extends RecyclerView.ItemDecoration {

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView
                parent, RecyclerView.State state) {

            int pos = parent.getChildAdapterPosition(view);
            if (pos == 0) {
                outRect.left = 0;
            } else if (parent.getChildAdapterPosition(view) == parent.getAdapter().getItemCount() - 1) {
                outRect.right = LINE_END_PADDING;
            } else {
                outRect.set(0, 0, 0, 0);
            }

        }
    }
}
