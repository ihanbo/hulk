package com.hans.demo.mc;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hans.demo.A1;
import com.hans.demo.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 顶部车型的adapter
 */

public class TitleAdapter extends RecyclerView.Adapter implements A1.OnItemCallbackListener {
    private List<McCarSummary> mData;
    private IAddCarEvent itemClick;

    private final static int TYPE_CAR_INFO = 0;
    private final static int TYPE_ADD_BTN = 1;

    public TitleAdapter(IAddCarEvent l) {
        this.itemClick = l;
        mData = new ArrayList<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_CAR_INFO) {
            McCompareCarItem item = new McCompareCarItem(parent.getContext(), itemClick);
            item.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
            CarInfoHolder holder = new CarInfoHolder(item);
            return holder;
        } else {
            View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.mc_item_compare_add_car, parent, false);
            AddCarHolder holder = new AddCarHolder(item, itemClick);
            return holder;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (mData == null || mData.isEmpty()) {
            return TYPE_ADD_BTN;
        } else {
            return mData.get(position) instanceof McCarSummary.McAddCar ? TYPE_ADD_BTN : TYPE_CAR_INFO;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof CarInfoHolder) {
            ((CarInfoHolder) holder).bindData(position, mData.get(position));
        } else {
            ((AddCarHolder) holder).bindData(position, mData.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public void setData(List<McCarSummary> titles) {
        mData = titles;
        notifyDataSetChanged();
    }

    @Override
    public void onMove(int fromPosition, int toPosition) {
        /**
         * 在这里进行给原数组数据的移动
         */
        Collections.swap(mData, fromPosition, toPosition);
        /**
         * 通知数据移动
         */
        notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public void onSwipe(int position) {
    }


    private static class CarInfoHolder extends RecyclerView.ViewHolder {

        private McCompareCarItem contentView;

        public CarInfoHolder(View itemView) {
            super(itemView);
            contentView = (McCompareCarItem) itemView;
        }

        public void bindData(int position, McCarSummary carInfo) {
            contentView.setData(carInfo);
        }
    }

    private static class AddCarHolder extends RecyclerView.ViewHolder {

        private int position;

        public AddCarHolder(View item, final IAddCarEvent itemClick) {
            super(item);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (itemClick != null) itemClick.addCar(position);
                }
            });
        }

        public void bindData(int position, McCarSummary carInfo) {
            this.position = position;
        }
    }


    public interface IAddCarEvent {

        void deleteCar(McCarSummary data);

        void addCar(int position);
    }
}
