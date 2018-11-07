package com.hans.demo.mc;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hans.demo.R;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersAdapter;

import java.util.ArrayList;
import java.util.List;


public class LinesAdapter extends RecyclerView.Adapter implements StickyRecyclerHeadersAdapter<LinesAdapter.HeadVH> {

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
        McCarParamsLineVH viewHolder = new McCarParamsLineVH(parent, mPool, mRecycledViewPool, mScrollhandler);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof McCarParamsLineVH) {
            ((McCarParamsLineVH) holder).setData(position, mLines.get(position));
        }
    }


    @Override
    public long getHeaderId(int position) {
        if (mHeads == null || mHeads.size() == 0) {
            return 0;
        }
        int index = mHeads.indexOfKey(position);
        if (index < 0) {
            index = ~index;
        }
        Log.i("hh", "LinesAdapter  : getHeaderId: position:" + position + "  index:" + index);
        return index;
    }

    @Override
    public HeadVH onCreateHeaderViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mc_item_compare_head, parent, false);
        return new HeadVH(view);
    }

    @Override
    public void onBindHeaderViewHolder(HeadVH holder, int position) {
        if (mHeads == null || mHeads.size() == 0) {
            return;
        }
        int index = (int) getHeaderId(position);
        McParamsModel model = mHeads.valueAt(index);
        holder.setData(model.name, model.sub_title);
    }

    @Override
    public int getItemCount() {
        return mLines.size();
    }

    private SparseArray<McParamsModel> mHeads;

    public void setData(List<McParamsModel.McLineBean> lines, SparseArray<McParamsModel> heads) {
        mLines = lines;
        mHeads = heads;
        notifyDataSetChanged();
    }

//
//    /**
//     * 行ViewHolder
//     */
//    private static class LineViewHolder extends RecyclerView.ViewHolder {
//
//        McCarParamsLine mLineView;
//
//        public LineViewHolder(Context context, McCompareTextPool pool, RecyclerView.RecycledViewPool recyclerViewPool, McCellsScrollHandler scrollhandler) {
//            super(McCarParamsLine.createForRecyclerView(context, pool, recyclerViewPool, scrollhandler));
//            mLineView = (McCarParamsLine) itemView;
//
//        }
//
//        public void bindData(int position, McParamsModel.McLineBean lineData) {
//            mLineView.setData(lineData);
//        }
//
//    }


    static class HeadVH extends RecyclerView.ViewHolder {

        TextView title;
        TextView subTitle;

        public HeadVH(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_title);
            subTitle = itemView.findViewById(R.id.tv_sub_title);
        }


        public void setData(String t, String st) {
            title.setText(t);
            subTitle.setText(st);

        }
    }

}
