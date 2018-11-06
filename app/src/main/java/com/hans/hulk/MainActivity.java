package com.hans.hulk;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mB1;
    private Button mB2;
    private Button mB3;
    private Button mB4;
    private Button mB5;
    private Button mB6;
    private Button mB7;
    private Button mB8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
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
                test1();
                break;
            case R.id.b2:
                break;
            case R.id.b3:
                break;
            case R.id.b4:
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
}
