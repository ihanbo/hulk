package com.hans.demo.mc;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hans.demo.R;

import java.util.List;

/**
 * @author hanbo
 * @date 2018/11/5
 */
public class McCompareLineAdapter extends RecyclerView.Adapter<McCompareLineAdapter.VH> {


    private RecyclerView.RecycledViewPool mRecycledViewPool;
    public List<McParamsModel.McLineBean> items;

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.mc_item_compare_line, parent, false);
        return new VH(itemView, mRecycledViewPool);
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        holder.setData(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }

    static class VH extends RecyclerView.ViewHolder {
        private TextView mTvKey;
        private RecyclerView mRv;
        private TextView mTvSame;

        public VH(View itemView, RecyclerView.RecycledViewPool pool) {
            super(itemView);
            mTvKey = (TextView) itemView.findViewById(R.id.tv_key);
            mRv = (RecyclerView) itemView.findViewById(R.id.rv);
            mTvSame = (TextView) itemView.findViewById(R.id.tv_same);
            mRv.setRecycledViewPool(pool);
            mRv.setLayoutManager(new LinearLayoutManager(itemView.getContext(),
                    LinearLayoutManager.HORIZONTAL, false));
        }


        private McParamsModel.McLineBean mLine;

        public void setData(McParamsModel.McLineBean lineBean) {
            mLine = lineBean;
            setNewHeight(lineBean.measureHeight);
            mTvKey.setText(lineBean.name);

        }

        public void setNewHeight(int height) {
            ViewGroup.LayoutParams layoutParams = itemView.getLayoutParams();
            layoutParams.height = height;
            itemView.setLayoutParams(layoutParams);
        }
    }


}
