package com.hans.demo.mc;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hans.demo.R;

import java.util.List;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

/**
 * 一行参数
 *
 * @author hanbo
 * @date 2018/11/5
 */
public class McCarParamsLineVH extends RecyclerView.ViewHolder {
    private TextView mTvKey;
    private CellsRecyclerView mRv;
    private TextView mTvSame;

    protected McCompareCellsAdapter mCellAdapter;
    private McCompareTextPool mPool;
    private RecyclerView.RecycledViewPool mRecyclerViewPool;
    private McCellsScrollHandler mMcCellsScrollHandler;

    public McCarParamsLineVH(ViewGroup parent, McCompareTextPool pool, RecyclerView.RecycledViewPool recyclerViewPool,
                             McCellsScrollHandler scrollhandler) {
        super(createView(parent));
        mPool = pool;
        mRecyclerViewPool = recyclerViewPool;
        mMcCellsScrollHandler = scrollhandler;
        init();
    }


    private static View createView(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.mc_item_compare_line, parent, false);
        return inflate;
    }

    private void init() {
        mTvKey = (TextView) itemView.findViewById(R.id.tv_key);
        mRv = (CellsRecyclerView) itemView.findViewById(R.id.rv);
        mTvSame = (TextView) itemView.findViewById(R.id.tv_same);
        mRv.addItemDecoration(new McComparetemDecoration(false));
        mRv.addItemDecoration(new ParamView.McStartEndMarginDecoration());
        mRv.setLayoutManager(new LinearLayoutManager(itemView.getContext(), LinearLayoutManager.HORIZONTAL, false));
        mMcCellsScrollHandler.regist(mRv);
        mCellAdapter = new McCompareCellsAdapter(mPool);
        mRv.setAdapter(mCellAdapter);
        mRv.setRecycledViewPool(mRecyclerViewPool);
        mRv.setRecyclerListener(new RecyclerView.RecyclerListener() {
            @Override
            public void onViewRecycled(RecyclerView.ViewHolder holder) {
                ((McCompareParamsItemVH) holder).recycle();
            }
        });
    }


    public void setData(int position, McParamsModel.McLineBean lineData) {
        mTvKey.setText(lineData.name);
        if (!lineData.isSame()) {
            mRv.setVisibility(VISIBLE);
            mTvSame.setVisibility(GONE);
            mCellAdapter.setData(lineData.values);
        } else {
            mRv.setVisibility(GONE);
            mTvSame.setVisibility(VISIBLE);
            mTvSame.setText(getSameText(lineData));
        }
        if (lineData.measureHeight > 0) {
            ViewGroup.LayoutParams layoutParams = itemView.getLayoutParams();
            layoutParams.height = lineData.measureHeight;
            itemView.setLayoutParams(layoutParams);
        }
    }


    //获取合并后的一行内容
    public String getSameText(McParamsModel.McLineBean lineData) {
        if (lineData.values != null && !lineData.values.isEmpty()
                && lineData.values.get(0) != null && !lineData.values.get(0).isEmpty()) {

            List<String> strings = lineData.values.get(0);
            int len = strings.size();

            if (len == 1) {
                return strings.get(0);
            } else {
                StringBuilder sb = new StringBuilder();
                for (int i = 0, size = strings.size(); i < size; i++) {
                    sb.append(strings.get(i));
                    if (i != size - 1) {
                        sb.append("\n");
                    }
                }
                return sb.toString();
            }
        } else {
            return "";
        }
    }

}
