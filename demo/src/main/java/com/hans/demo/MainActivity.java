package com.hans.demo;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends FragmentActivity {

    private RadioButton mR1;
    private RadioButton mR2;
    private RadioButton mR3;
    private RadioGroup mRg1;
    private CheckBox mC1;

    private TextView mTextView;

    private static int keyWidthMeasureSpec;         //键的宽度测量spec
    private static int paramsWidthMeasureSpec;  //参数单元格的宽度测量spec
    private static int atMostMeasureSpec;
    LinearLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_2);
        findViews();
        frameLayout = new LinearLayout(this);
        frameLayout.setOrientation(LinearLayout.VERTICAL);

        mTextView = new TextView(this);
        mTextView.setTextSize(12, TypedValue.COMPLEX_UNIT_DIP);


        frameLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        keyWidthMeasureSpec = View.MeasureSpec.makeMeasureSpec(240, View.MeasureSpec.EXACTLY);         //键的宽度测量spec
        atMostMeasureSpec = View.MeasureSpec.makeMeasureSpec((1 << 30) - 1, View.MeasureSpec.AT_MOST);


    }


    private void findViews() {
        mR1 = (RadioButton) findViewById(R.id.r1);
        mR2 = (RadioButton) findViewById(R.id.r2);
        mR3 = (RadioButton) findViewById(R.id.r3);
        mRg1 = (RadioGroup) findViewById(R.id.rg1);
        mC1 = (CheckBox) findViewById(R.id.c1);


        mC1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Log.i("hh", "MainActivity  : CheckBox onClick: " + mC1.isChecked());
                A1.open(MainActivity.this);
            }
        });

        mC1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //Log.i("hh", "MainActivity  : CheckBox onCheckedChanged: " + isChecked);
            }
        });


        mRg1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                //Log.i("hh", "MainActivity  : RadioGroup onCheckedChanged: " + getCheckBtn(checkedId));
            }
        });

        mR1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //Log.i("hh", "MainActivity  : RadioButton1 onCheckedChanged: " + isChecked);
                if (mTextView == null) {
                    return;
                }
                if (isChecked) {
                    mTextView.setText("凤凰大师傅的哈佛会尽快的哈借凤凰大师傅的哈佛会尽快的哈借款方大富豪的金卡是款方大富豪的金卡是");
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    frameLayout.addView(mTextView, layoutParams);
                    frameLayout.measure(keyWidthMeasureSpec, atMostMeasureSpec);
                    int h = frameLayout.getMeasuredHeight();
                    frameLayout.removeViewAt(0);
                    Log.i("hh", "MainActivity  : onCheckedChanged: " + h);
                } else {
                    mTextView.setText("凤凰大师傅的");
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    frameLayout.addView(mTextView, layoutParams);
                    frameLayout.measure(keyWidthMeasureSpec, atMostMeasureSpec);
                    int h = frameLayout.getMeasuredHeight();
                    frameLayout.removeViewAt(0);
                    Log.i("hh", "MainActivity  : onCheckedChanged: " + h);
                }
            }
        });

        mR1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Log.i("hh", "MainActivity  :RadioButton1 onClick: " + mR1.isChecked());
            }
        });

        mR2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.i("hh", "MainActivity  : RadioButton2 onCheckedChanged: " + isChecked);
            }
        });

        mR2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("hh", "MainActivity  :RadioButton2 onClick: " + mR2.isChecked());
            }
        });

        mR3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.i("hh", "MainActivity  : RadioButton3 onCheckedChanged: " + isChecked);
            }
        });

        mR3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("hh", "MainActivity  :RadioButton3 onClick: " + mR3.isChecked());
            }
        });

        mR1.setChecked(false);
    }

    private String getCheckBtn(int checkedId) {
        switch (checkedId) {
            case R.id.r1:
                return "btn1";
            case R.id.r2:
                return "btn2";
            case R.id.r3:
                return "btn3";
        }
        return "n/a";
    }


}
