package com.hans.demo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.hans.demo.mc.McCompareCalculate;
import com.hans.demo.mc.McCompareController;
import com.hans.demo.mc.ParamView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class A1 extends AppCompatActivity {

    public static void open(Context context) {
        Intent starter = new Intent(context, A1.class);
        context.startActivity(starter);
    }

    public List<List<String>> mData;

    McCompareController mController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a1);

        mData = new ArrayList<>();
        mData.add(createData("发大水发动机卡花费很大", "发大水发的撒范德萨范德萨范德萨范德萨"));
        mData.add(createData("发顺丰大范德萨范德萨", "肥嘟嘟多多多"));
        mData.add(createData("范德萨发", "阿范德萨"));
        mData.add(createData("范德萨", "范德萨范德萨发的"));
        mData.add(createData("发生发大水范德萨发房打算范德萨范德萨", "放到"));
        mData.add(createData("放到", "范德萨范德萨范德萨范德萨范德萨范德萨范德萨范德范德萨范德萨范德萨范德萨萨范德萨"));
        mData.add(createData("发生发的撒", "范德萨范德萨范德萨"));
        mData.add(createData("放到", "范德萨范德萨范德萨"));


        mController = new McCompareController(this);
        ParamView view = new ParamView(this, findViewById(R.id.root_view));
        mController.onViewCreated(view);
    }


    public class CarAdapter extends RecyclerView.Adapter<CarAdapter.Holder> implements OnItemCallbackListener {

        public List<List<String>> mData;
        private Context mContext;

        public CarAdapter(Context mContext, List<List<String>> data) {
            this.mContext = mContext;
            mData = data;

        }

        @Override
        public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.mc_item_car_compare_car_name, parent, false);
            return new Holder(view);
        }

        @Override
        public void onBindViewHolder(final Holder holder, int position) {
            holder.setData(mData.get(position));
        }

        @Override
        public int getItemCount() {
            return mData == null ? 0 : mData.size();
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
            /**
             * 原数据移除数据
             */
            mData.remove(position);
            /**
             * 通知移除
             */
            notifyItemRemoved(position);
        }

        class Holder extends RecyclerView.ViewHolder {
            private TextView mTvCarSeries;
            private TextView mTvCarName;
            private ImageButton mBtnDel;


            public Holder(View itemView) {
                super(itemView);
                mTvCarSeries = (TextView) itemView.findViewById(R.id.tv_car_series);
                mTvCarName = (TextView) itemView.findViewById(R.id.tv_car_name);
                mBtnDel = (ImageButton) itemView.findViewById(R.id.btn_del);
            }

            private List<String> mData;

            public void setData(List<String> data) {
                mData = data;
                mTvCarSeries.setText(data.get(0));
                mTvCarName.setText(data.get(1));
            }

        }
    }

    public interface OnItemCallbackListener {
        /**
         * @param fromPosition 起始位置
         * @param toPosition   移动的位置
         */
        void onMove(int fromPosition, int toPosition);

        void onSwipe(int position);
    }

    private class OnItemCallbackHelper extends ItemTouchHelper.Callback {
        CarAdapter mAdapter;

        public OnItemCallbackHelper(CarAdapter adapter) {
            mAdapter = adapter;
        }

        @Override
        public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            int dragFlag = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
            int swipeFlag = ItemTouchHelper.START | ItemTouchHelper.END;

            return makeMovementFlags(dragFlag, 0);

        }

        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            int targetPos = target.getAdapterPosition();
            if (targetPos == mAdapter.getItemCount() - 1) {
                targetPos = mAdapter.getItemCount() - 2;
            }
            mAdapter.onMove(viewHolder.getAdapterPosition(), targetPos);
            return true;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

        }
    }


    public List<String> createData(String seriesName, String carName) {
        List<String> data = new ArrayList<>(2);
        data.add(seriesName);
        data.add(carName);
        return data;

    }
}
