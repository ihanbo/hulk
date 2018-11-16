package com.hans.hulk;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.DynamicLayout;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static android.widget.LinearLayout.VERTICAL;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mB1;
    private Button mB2;
    private Button mB3;
    private Button mB4;
    private Button mB5;
    private Button mB6;
    private Button mB7;
    private Button mB8;


    private TextView mTextView;

    private static int keyWidthMeasureSpec;
    private static int atMostMeasureSpec;

    private FrameLayout mFrameLayout;
    private LinearLayout mContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        textView = new TextView(this);
        textView.setLayoutParams(new LinearLayout.LayoutParams(-2,-2));



        mFrameLayout = createView(new LinearLayout(this));
        mContainer = (LinearLayout) mFrameLayout.getChildAt(0);
        mTextView = new TextView(this);
        mTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP,12);
        mTextView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));


        keyWidthMeasureSpec = View.MeasureSpec.makeMeasureSpec(240, View.MeasureSpec.EXACTLY);         //键的宽度测量spec
        atMostMeasureSpec = View.MeasureSpec.makeMeasureSpec((1 << 30) - 1, View.MeasureSpec.AT_MOST);

    }


    private void findViews() {
        mB1 = (Button) findViewById(R.id.b1);
        mB1.setOnClickListener(this);
        mB2 = (Button) findViewById(R.id.b2);
        mB2.setOnClickListener(this);
        mB3 = (Button) findViewById(R.id.b3);
        mB3.setOnClickListener(this);
        mB4 = (Button) findViewById(R.id.b4);
        mB4.setOnClickListener(this);
        mB5 = (Button) findViewById(R.id.b5);
        mB5.setOnClickListener(this);
        mB6 = (Button) findViewById(R.id.b6);
        mB6.setOnClickListener(this);
        mB7 = (Button) findViewById(R.id.b7);
        mB7.setOnClickListener(this);
        mB8 = (Button) findViewById(R.id.b8);
        mB8.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.b1:
                mTextView.setText("凤凰大师傅的哈佛会尽快的哈借凤凰大师傅的哈佛会尽快的哈借款方大富豪的金卡是款方大富豪的金卡是");
                mContainer.addView(mTextView);
                mFrameLayout.measure(keyWidthMeasureSpec, atMostMeasureSpec);
                int h = mFrameLayout.getMeasuredHeight();
                mContainer.removeAllViews();
                Log.i("hh", "MainActivity  : getMeasuredHeight: " + h);
                break;
            case R.id.b2:
                mTextView.setText("凤凰金卡是");
                mTextView.measure(keyWidthMeasureSpec, atMostMeasureSpec);
                int h2 = mTextView.getMeasuredHeight();
                Log.i("hh", "MainActivity  : getMeasuredHeight: " + h2);
                break;
            case R.id.b3:
                int height = getHeight(this, "地方大师傅打个飞机大嘎达法凤凰大师傅的哈佛会尽快的哈借凤凰大师傅的哈佛会尽快的哈借款方大富豪的金卡是款方大富豪的金卡是国大使馆和法定司法鉴定", 12, 240);
                Log.i("hh", "MainActivity  : onClick: height:" + height);
                break;
            case R.id.b4:
                int height2 = getHeight(this, "地方大师傅打个飞机大嘎达法凤", 12, 240);
                Log.i("hh", "MainActivity  : onClick: height:" + height2);
                break;
            case R.id.b5:
                break;
            case R.id.b6:
                break;
            case R.id.b7:
                break;
            case R.id.b8:
                break;
        }
    }


    public void test1() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                Log.i("hh", "MainActivity  : subscribe: " + Thread.currentThread().getName());

            }
        }).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.i("hh", "MainActivity  : onSubscribe: " + Thread.currentThread().getName());
                    }

                    @Override
                    public void onNext(String s) {
                        Log.i("hh", "MainActivity  : onNext: " + s + "    " + Thread.currentThread().getName());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("hh", "MainActivity  : onError: " + Thread.currentThread().getName());
                    }

                    @Override
                    public void onComplete() {
                        Log.i("hh", "MainActivity  : onComplete: " + Thread.currentThread().getName());
                    }
                });
    }


    private static FrameLayout createView(ViewGroup parent) {
        FrameLayout frame = new FrameLayout(parent.getContext());
        frame.setBackground(new ColorDrawable(Color.TRANSPARENT));
        LinearLayout mContainer = new LinearLayout(parent.getContext());
        mContainer.setOrientation(VERTICAL);
        mContainer.setGravity(Gravity.CENTER_HORIZONTAL);
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.gravity = Gravity.CENTER;
        frame.addView(mContainer, lp);
        int padding = 30;
        frame.setPadding(padding, padding, padding, padding);
        frame.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        return frame;
    }
    TextView textView ;
    public int getHeight(Context context, String text, int textSize, int deviceWidth) {

        textView.setText(text);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP ,textSize);
        int widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(deviceWidth, View.MeasureSpec.EXACTLY);
        int heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        textView.measure(widthMeasureSpec, heightMeasureSpec);
        return textView.getMeasuredHeight();
    }
}
