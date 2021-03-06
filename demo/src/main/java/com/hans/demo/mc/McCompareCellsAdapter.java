package com.hans.demo.mc;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * @author hanbo
 * @date 2018/11/5
 */
public class McCompareCellsAdapter extends RecyclerView.Adapter<McCompareParamsItemVH> {

    public List<List<String>> values;
    private McCompareTextPool mPool;

    public McCompareCellsAdapter(List<List<String>> values, McCompareTextPool pool) {
        this(pool);
        this.values = values;
    }

    public McCompareCellsAdapter(McCompareTextPool pool) {
        mPool = pool;
    }

    @Override
    public McCompareParamsItemVH onCreateViewHolder(ViewGroup parent, int viewType) {
        return new McCompareParamsItemVH(parent, mPool);
    }

    @Override
    public void onBindViewHolder(McCompareParamsItemVH holder, int position) {
        holder.setData(values.get(position), position);
    }


    @Override
    public int getItemCount() {
        return values == null ? 0 : values.size();
    }

    public void setData(List<List<String>> values) {
        this.values = values;
        notifyItemRangeInserted(0, values.size());
    }

    /*public static class McCompareCellVH extends RecyclerView.ViewHolder {

        public McCompareCellVH(View itemView) {
            super(itemView);
        }

        public void setData(List<String> lineBean, int position) {
            ((McCompareParamsItem) itemView).setData(lineBean, position);
        }

        public void recycle() {
            ((McCompareParamsItem) itemView).recycleAll();
        }
    }*/

}
