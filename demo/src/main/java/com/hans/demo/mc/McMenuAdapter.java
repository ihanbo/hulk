package com.hans.demo.mc;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.hans.demo.R;

import java.util.List;

/**
 * @author hanbo
 * @date 2018/11/7
 */
public class McMenuAdapter extends BaseAdapter {
    private List<McParamsModel> items;
    private FilterCallBack mCallBack;
    private int mSelectPos = 0;

    public McMenuAdapter(List<McParamsModel> items, final FilterCallBack callBack) {
        this.items = items;
        mCallBack = new FilterCallBack() {
            @Override
            public void select(McParamsModel data, int pos) {
                mSelectPos = pos;
                notifyDataSetChanged();
                if (callBack != null) {
                    callBack.select(data, pos);
                }
            }

            @Override
            public void dismissMenu() {
                if (callBack != null) {
                    callBack.dismissMenu();
                }
            }
        };
    }

    public void setData(final List<McParamsModel> configurations) {
        items = configurations;
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return items == null ? 0 : items.size();
    }


    @Override
    public McParamsModel getItem(int position) {
        if (items == null || items.isEmpty() || position >= items.size() || position < 0) {
            return null;
        }
        return items.get(position);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        McParamsModel item = getItem(position);
        if (convertView == null) {
            return new McTextItem(parent.getContext(), mCallBack).setData(item, position == mSelectPos, position);
        } else {
            ((McTextItem) convertView).setData(item, position == mSelectPos, position);
            return convertView;
        }
    }

    public void setSelectPos(int selectPos) {
        if (selectPos != mSelectPos) {
            mSelectPos = selectPos;
            notifyDataSetChanged();
        }
    }

    static class McTextItem extends FrameLayout implements View.OnClickListener {
        private TextView mText;
        private FilterCallBack mCallBack;

        public McTextItem(@NonNull Context context, FilterCallBack callBack) {
            super(context);
            setBackgroundColor(Color.TRANSPARENT);
            LayoutInflater.from(context).inflate(R.layout.mc_view_compare_menu_item, this, true);
            mText = (TextView) findViewById(R.id.tv_text);
            mCallBack = callBack;
            mText.setOnClickListener(this);
        }

        McParamsModel mData;
        boolean mSelect;
        int mPos;

        public McTextItem setData(McParamsModel data, boolean select, int pos) {
            mData = data;
            mSelect = select;
            mPos = pos;
            mText.setText(data.name);
            mText.setSelected(select);
            return this;
        }

        @Override
        public void onClick(View v) {
            if (mCallBack != null && mData != null) {
                if (!mSelect) {
                    mText.setSelected(true);
                    mCallBack.select(mData, mPos);
                } else {
                    mCallBack.dismissMenu();
                }

            }
        }
    }

    public interface FilterCallBack {

        void select(McParamsModel data, int pos);

        void dismissMenu();
    }
}
