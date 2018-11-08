package com.hans.demo.mc;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hans.demo.R;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 参数纵向列表适配器
 *
 * @author hanbo
 * @date 2018/11/6
 */
public class McParamsLinesAdapter extends RecyclerView.Adapter implements StickyRecyclerHeadersAdapter<McParamsLinesAdapter.HeadVH> {

    private List<McParamsModel.McLineBean> mLines;
    private SparseArray<McParamsModel> mHeads;      //吸顶head数据
    private McCellsScrollHandler mScrollhandler;
    private McCompareTextPool mPool;
    private RecyclerView.RecycledViewPool mRecycledViewPool;    //公用的复用池

    public McParamsLinesAdapter(Activity activity, McCellsScrollHandler scrollhandler) {
        mLines = new ArrayList<>();
        mScrollhandler = scrollhandler;
        mPool = new McCompareTextPool(activity);
        mRecycledViewPool = new RecyclerView.RecycledViewPool();
        mRecycledViewPool.setMaxRecycledViews(0, 64);
    }


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
        return mLines == null ? 0 : mLines.size();
    }


    public void setData(List<McParamsModel.McLineBean> lines, SparseArray<McParamsModel> heads) {
        mLines = lines;
        mHeads = heads;
        notifyDataSetChanged();
    }

    //吸顶头部
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
