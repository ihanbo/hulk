package com.hans.demo.mc;

import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;

/**
 * 非线程安全的对象池
 *
 * @Author hanbo
 * @Since 2018/9/25
 */
public abstract class McObjectPoolSingleThread<T> {

    private LinkedList<T> mLinkedList;
    private int mCreateNewCount;


    public McObjectPoolSingleThread() {
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
     * 失败返回false
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

    public void putAll(List<T> ts) {
        if (ts == null || ts.isEmpty()) {
            return;
        }
        mLinkedList.addAll(ts);

    }

    public T createNew() {
        mCreateNewCount++;
        return createNewObj();
    }


    public void initNew(int num) {
        for (int i = 0; i < num; i++) {
            put(createNew());
        }
    }

    public abstract T createNewObj();

}
