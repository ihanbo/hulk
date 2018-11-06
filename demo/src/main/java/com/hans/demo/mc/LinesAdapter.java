package com.hans.demo.mc;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


public class LinesAdapter extends RecyclerView.Adapter {

    private List<McParamsModel.McLineBean> mLines;
    private McCellsScrollHandler mScrollhandler;
    private McCompareTextPool mPool;
    private RecyclerView.RecycledViewPool mRecycledViewPool;

    public LinesAdapter(Activity activity, McCellsScrollHandler scrollhandler) {
        mLines = new ArrayList<>();
        mScrollhandler = scrollhandler;
        mPool = new McCompareTextPool(activity);
        mRecycledViewPool = new RecyclerView.RecycledViewPool();
        mRecycledViewPool.setMaxRecycledViews(0, 64);
    }

    //-----------------section部分----start-------------
//    @Override
//    public long getHeaderId(int position) {
//        try {
//            return mLines.get(position).groupId;
//        } catch (Exception e) {
//            return -1;
//        }
//    }
//
//    @Override
//    public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
//        View view = LayoutInflater.from(mContext).inflate(R.layout.pinned_header_listview_header, parent, false);
//        LineGroupTitleHolder holder = new LineGroupTitleHolder(view);
//        return holder;
//    }
//
//    @Override
//    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {
//        if (holder instanceof LineGroupTitleHolder) {
//            ((LineGroupTitleHolder) holder).bindData(position, mLines.get(position));
//        }
//    }
    //-----------------section部分----end---------------

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LineViewHolder viewHolder = new LineViewHolder(parent.getContext(), mPool, mRecycledViewPool, mScrollhandler);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof LineViewHolder) {
            ((LineViewHolder) holder).bindData(position, mLines.get(position));
        }
    }


    @Override
    public int getItemCount() {
        return mLines.size();
    }

    public void setData(List<McParamsModel.McLineBean> lines) {
        mLines = lines;
        notifyDataSetChanged();
    }


    /**
     * 行ViewHolder
     */
    private static class LineViewHolder extends RecyclerView.ViewHolder {

        McCarParamsLine mLineView;

        public LineViewHolder(Context context, McCompareTextPool pool, RecyclerView.RecycledViewPool recyclerViewPool, McCellsScrollHandler scrollhandler) {
            super(McCarParamsLine.createForRecyclerView(context, pool, recyclerViewPool, scrollhandler));
            mLineView = (McCarParamsLine) itemView;

        }

        public void bindData(int position, McParamsModel.McLineBean lineData) {
            mLineView.setData(lineData);
        }

    }

}
