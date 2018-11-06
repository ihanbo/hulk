package com.hans.coreutils.objpool;

import java.util.LinkedList;

/**
 * 非线程安全的对象池
 *
 * @Author hanbo
 * @Since 2018/9/25
 */
public abstract class ObjectPoolSingleThread<T> implements IObjPool<T> {

    private LinkedList<T> mLinkedList;
    private int mCreateNewCount;


    public ObjectPoolSingleThread() {
        mLinkedList = new LinkedList<>();
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
        try {
            return mLinkedList.offer(t);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public T createNew() {
        mCreateNewCount++;
        return createNewObj();
    }

    public abstract T createNewObj();

}
