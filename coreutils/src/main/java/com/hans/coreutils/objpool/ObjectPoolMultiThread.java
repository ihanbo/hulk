package com.hans.coreutils.objpool;

import java.util.Collection;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 线程安全的对象池
 *
 * @Author hanbo
 * @Since 2018/9/25
 */
public abstract class ObjectPoolMultiThread<T> implements IObjPool<T> {

    private ConcurrentLinkedQueue<T> mLinkedList;
    private int mCreateNewCount;


    public ObjectPoolMultiThread() {
        mLinkedList = new ConcurrentLinkedQueue<>();
        mCreateNewCount = 0;
    }


    public ObjectPoolMultiThread(Collection<? extends T> c) {
        mLinkedList =  new ConcurrentLinkedQueue<>(c);
        mCreateNewCount = 0;
    }

    /**
     * 池子里没有回返回新建的
     *
     * @return T
     */
    public T get() {
        T poll = mLinkedList.poll();
        if (poll == null) {
            poll = createNew();
        }
        return poll;
    }

    /**
     * 出异常返回false
     *
     * @param t
     * @return
     */
    public boolean put(T t) {
        if (t == null) {
            return false;
        }
        return mLinkedList.offer(t);
    }

    @Override
    public T createNew() {
        mCreateNewCount++;
        return createNewObj();
    }

    public abstract T createNewObj();

}
