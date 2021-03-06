package com.hans.demo.mc;

import android.content.Context;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hans.demo.R;

import java.util.List;

/**
 * @author hanbo
 * @date 2018/11/5
 */
class McCompareTextPool extends McObjectPoolSingleThread<TextView> {

    Context mContext;

    public McCompareTextPool(Context context) {
        mContext = context;
        initNew(5);
    }

    @Override
    public TextView createNewObj() {
        TextView tv = new TextView(mContext);
        tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 12);
        ViewGroup.MarginLayoutParams lp = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.topMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, mContext.getResources().getDisplayMetrics());
        tv.setLayoutParams(lp);
        tv.setTextColor(mContext.getResources().getColor(R.color.mc_color_192132));
        return tv;
    }

}
