package com.didi.theonebts.minecraft.car.compare;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.hans.demo.R;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

/**
 * 顶部车型条目
 *
 * @author hanbo
 * @date 2018/11/5
 */
public class McCompareCarItemVH extends RecyclerView.ViewHolder implements View.OnClickListener {
    private TextView mTvCarSeries;
    private TextView mTvCarName;
    private ImageButton mBtnDel;

    private McCompareCarAdapter.IAddCarEvent mAddCarEvent;

    public McCompareCarItemVH(ViewGroup parent, McCompareCarAdapter.IAddCarEvent itemClick) {
        super(createView(parent));
        mAddCarEvent = itemClick;
        init(itemView);
    }

    private static View createView(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.mc_item_car_compare_car_name, parent, false);
        return inflate;
    }

    private void init(View view) {
        mTvCarSeries = (TextView) view.findViewById(R.id.tv_car_series);
        mTvCarName = (TextView) view.findViewById(R.id.tv_car_name);
        mBtnDel = (ImageButton) view.findViewById(R.id.btn_del);
        mBtnDel.setOnClickListener(this);
    }


    private McCarSummary mData;
    private int mPos = -1;

    public void setData(int position, McCarSummary data) {
        if (data == null) {
            mTvCarSeries.setVisibility(GONE);
            mTvCarName.setVisibility(GONE);
            return;
        }
        mData = data;
        mPos = position;
        mTvCarSeries.setVisibility(VISIBLE);
        mTvCarName.setVisibility(VISIBLE);
        mTvCarSeries.setText(data.series_name);
        mTvCarName.setText(data.model_name);
    }


    @Override
    public void onClick(View v) {
        if (v == mBtnDel) {
            if (mAddCarEvent != null) {
                mAddCarEvent.deleteCar(mData, mPos);
            }
        }
    }

    public View getItemView() {
        return itemView;
    }

}
