package com.hans.demo.mc;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.util.SparseArray;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * @author hanbo
 * @date 2018/11/6
 */
public class McCompareController {


    private Activity mActivity;
    private ParamView mView;
    private McCompareCalculate mCalculate;

    public McCompareController(Activity activity) {
        mActivity = activity;
        mCalculate = new McCompareCalculate(activity);
    }


    public void onViewCreated(ParamView view) {
        mView = view;
        mView.setController(this);

        setData(TempData.getTempData());
    }

    public void deleteCar(McCarSummary data) {

    }

    //添加车型
    public void addCar() {

    }

    public void hideSameTo(boolean checked) {
        if(checked){
            mView.getParamsAdapter().setData();
        }

    }


    /**
     * 0 行11
     * 1 行12
     * 2 行13
     * key        value  index
     * 2(size-1)  块1    0
     * 3 行21
     * 4 行22
     * 4          块2    1
     */
    private SparseArray<McParamsModel> mFullHeads;
    private McCarCompareModel mData;
    private List<McParamsModel.McLineBean> mFullData;
    private List<McParamsModel.McLineBean> linesNoSame;

    private final List COMMON_EMPTY = Collections.EMPTY_LIST;


    public void setData(McCarCompareModel data) {
        if (data == null || data.configurations == null || data.configurations.isEmpty()
                || data.model_infos == null || data.model_infos.isEmpty()) {
            return;
        }

        long start = System.currentTimeMillis();

        //需要在最后添加addcar
        boolean neeadAddExtra = data.model_infos.size() < 9;

        if (neeadAddExtra) {
            data.model_infos.add(new McCarSummary.McAddCar());
        }

        mFullData = new ArrayList<>(128);
        mFullHeads = new SparseArray<>(32);

        //合并块的数据
        for (int i = 0, len = data.configurations.size(); i < len; i++) {
            McParamsModel mcParamsModel = data.configurations.get(i);
            if (mcParamsModel != null && mcParamsModel.items != null) {
                mFullData.addAll(mcParamsModel.items);
            }
            mFullHeads.put(mFullData.size() - 1, mcParamsModel);
        }


        linesNoSame = new ArrayList<>(mFullData.size());


        /*
         * 处理行数据
         * 1.移除无效数据
         * 2.计算行高
         * 3.按需添加行末空项（添加车型）
         * 4.过滤出不相同的项
         * */
        for (Iterator<McParamsModel.McLineBean> iterator = mFullData.iterator(); iterator.hasNext(); ) {
            McParamsModel.McLineBean lineBean = iterator.next();

            boolean same = lineBean.isSame();
            if (lineBean.values == null && !same) {
                iterator.remove();
                continue;
            }

            mCalculate.preMeasureLineHeight(lineBean);

            if (lineBean.values != null && neeadAddExtra) {
                lineBean.values.add(COMMON_EMPTY);
            }

            if (!same) {
                linesNoSame.add(lineBean);
            }
        }

        int carMostHeight = mCalculate.preMeasureCarHeight(data.model_infos);

        long end = System.currentTimeMillis();
        Log.i("hh", "McCompareController  : setData: cost: " + (end - start));
        mView.setData(data.model_infos, mFullData, mFullHeads, data.configurations);
    }

    public void jumpTo(McParamsModel data, int pos) {
        if (pos == 0) {
            mView.scrollTo(0);
        } else {
            mView.scrollTo(mFullHeads.keyAt(pos - 1) + 1);
        }
    }


    public void openMenu() {
        if (mFullHeads == null || mFullHeads.size() == 0) {
            mView.showMenu(0);
            return;
        }
        LinearLayoutManager layoutManager = (LinearLayoutManager) mView.getParamsView().getLayoutManager();
        if (layoutManager != null) {
            int pos = layoutManager.findFirstVisibleItemPosition();
            int index = mFullHeads.indexOfKey(pos);
            if (index < 0) {
                index = ~index;
            }
            mView.showMenu(index);
        }
    }


}
