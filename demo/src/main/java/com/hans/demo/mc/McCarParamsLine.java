package com.hans.demo.mc;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.hans.demo.R;

/**
 * 一行参数
 *
 * @author hanbo
 * @date 2018/11/5
 */
public class McCarParamsLine extends FrameLayout {
    private TextView mTvKey;
    private McCompareHeaderRecyclerView mRv;
    private TextView mTvSame;

    protected McCompareCellsAdapter mCellAdapter;
    private McCompareTextPool mPool;
    private RecyclerView.RecycledViewPool mRecyclerViewPool;
    private CellsScrollHandler mCellsScrollHandler;

    public McCarParamsLine(@NonNull Context context, McCompareTextPool pool, RecyclerView.RecycledViewPool recyclerViewPool,
                           CellsScrollHandler scrollhandler) {
        super(context);
        mPool = pool;
        mRecyclerViewPool = recyclerViewPool;
        mCellsScrollHandler = scrollhandler;
        init();

    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.mc_item_compare_line, this, true);
        mTvKey = (TextView) findViewById(R.id.tv_key);
        mRv = (McCompareHeaderRecyclerView) findViewById(R.id.rv);
        mTvSame = (TextView) findViewById(R.id.tv_same);
        mRv.addItemDecoration(new ParamView.McComparetemDecoration(false));
        mRv.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        mRv.setScrollHandler(mCellsScrollHandler);
        mCellsScrollHandler.regist(mRv);
        mCellAdapter = new McCompareCellsAdapter(mPool);
        mRv.setAdapter(mCellAdapter);
        mRv.setRecycledViewPool(mRecyclerViewPool);
        mRv.setRecyclerListener(new RecyclerView.RecyclerListener() {
            @Override
            public void onViewRecycled(RecyclerView.ViewHolder holder) {
                ((McCompareCellsAdapter.McCompareCellVH) holder).recycle();
            }
        });
    }


    public void setData(McParamsModel.McLineBean lineData) {
        mTvKey.setText(lineData.name);
        if (lineData.colspan) {
            mRv.setVisibility(INVISIBLE);
            mTvSame.setVisibility(VISIBLE);
        } else {
            mRv.setVisibility(VISIBLE);
            mTvSame.setVisibility(INVISIBLE);
            mCellAdapter.setData(lineData.values);
        }
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        layoutParams.height = lineData.height;
        setLayoutParams(layoutParams);

    }


    public CellsContainer getRecyclerView() {
        return mRv;
    }


    public static McCarParamsLine createForRecyclerView(Context context, McCompareTextPool pool,
                                                        RecyclerView.RecycledViewPool recyclerViewPool, CellsScrollHandler scrollhandler) {
        McCarParamsLine line = new McCarParamsLine(context, pool, recyclerViewPool, scrollhandler);
        line.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        return line;
    }
}
