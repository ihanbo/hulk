package com.hans.coreutils.objpool;

/**
 * @Author hanbo
 * @Since 2018/9/25
 */
public interface IObjPool<T> {
    T get();

    boolean put(T t);

    T createNew();
}
