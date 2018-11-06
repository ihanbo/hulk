//package com.hans.demo.mc;
//
//import android.content.Context;
//import android.support.annotation.NonNull;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.widget.FrameLayout;
//import android.widget.ImageButton;
//import android.widget.TextView;
//
//import com.hans.demo.R;
//
///**
// * 顶部车型条目
// *
// * @author hanbo
// * @date 2018/11/5
// */
//public class McCompareCarItem extends FrameLayout implements View.OnClickListener {
//    public static final int DEFAULT_WIDTH = 145;
//    private TextView mTvCarSeries;
//    private TextView mTvCarName;
//    private ImageButton mBtnDel;
//
//    private McCompareCarAdapter.IAddCarEvent mAddCarEvent;
//
//    public McCompareCarItem(@NonNull Context context, McCompareCarAdapter.IAddCarEvent itemClick) {
//        super(context);
//        mAddCarEvent = itemClick;
//        LayoutInflater.from(getContext()).inflate(R.layout.mc_item_car_compare_car_name, this, true);
//        init(this);
//    }
//
//
//    private void init(View view) {
//        mTvCarSeries = (TextView) view.findViewById(R.id.tv_car_series);
//        mTvCarName = (TextView) view.findViewById(R.id.tv_car_name);
//        mBtnDel = (ImageButton) view.findViewById(R.id.btn_del);
//        mBtnDel.setOnClickListener(this);
//    }
//
//
//    private McCarSummary mData;
//
//    public void setData(McCarSummary data) {
//        mData = data;
//        if (data == null) {
//            mTvCarSeries.setVisibility(GONE);
//            mTvCarName.setVisibility(GONE);
//            return;
//        }
//        mTvCarSeries.setVisibility(VISIBLE);
//        mTvCarName.setVisibility(VISIBLE);
//        mTvCarSeries.setText(data.series_name);
//        mTvCarName.setText(data.model_name);
//    }
//
//
//    @Override
//    public void onClick(View v) {
//        if (v == mBtnDel) {
//            if (mAddCarEvent != null) {
//                mAddCarEvent.deleteCar(mData);
//            }
//        }
//    }
//
//
//}
