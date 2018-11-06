package com.hans.net.retrofit2;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * @author hanbo
 * @date 2018/11/2
 */
public class Net {

    private static volatile Retrofit retrofit;


    public static void init(String baeUrl, int timeout) {
        if (retrofit == null) {
            synchronized (retrofit) {
                if (retrofit == null) {
                    retrofit = _getRetrofit(baeUrl, timeout);
                }
            }
        }
    }


    private static Retrofit _getRetrofit(String baeUrl, int timeout) {
        return new Retrofit.Builder()
                .addConverterFactory(MyGsonConverterFactory.create())
                .baseUrl(baeUrl)
                .addCallAdapterFactory(MyRXCallAdapter.create())
                .addCallAdapterFactory(NormalCallAdapter.create())
                .client(getOkHttpClient(timeout)).build();
    }

    private static OkHttpClient getOkHttpClient(int timeout) {
        new OkHttpClient.Builder().connectTimeout(timeout, TimeUnit.SECONDS)
                .readTimeout(timeout, TimeUnit.SECONDS)
                .writeTimeout(timeout, TimeUnit.SECONDS)
                .build();
        return null;
    }


    public static <T> T getService(Class<T> clazz) {
        return retrofit.create(clazz);
    }

}
