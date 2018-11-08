package com.hans.demo.mc;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.util.SparseArray;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author hanbo
 * @date 2018/11/6
 */
public class McCompareController {


    private Activity mActivity;
    private McParamView mView;
    private McCompareCalculate mCalculate;

    public McCompareController(Activity activity) {
        mActivity = activity;
        mCalculate = new McCompareCalculate(activity);
    }


    public void onViewCreated(McParamView view) {
        mView = view;
        mView.setController(this);

        setData(TempData.getTempData());
    }

    public void deleteCar(McCarSummary data) {
        //TODO 删车
    }

    //添加车型
    public void addCar() {
        //TODO 加车
    }

    private boolean mHideSame = false;

    public void hideSameTo(boolean checked) {
        if (mHideSame == checked) {
            return;
        }
        mHideSame = checked;
        if (checked) {
            mCurrentData = mNoSameData;
            mCurrentHeads = mNoSameHeads;
        } else {
            mCurrentData = mFullData2;
            mCurrentHeads = mFullHeads2;
        }

        mView.setDataWithoutCar(mCurrentData, mCurrentHeads);
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
    private SparseArray<McParamsModel> mCurrentHeads;
    private List<McParamsModel.McLineBean> mCurrentData;

    private SparseArray<McParamsModel> mFullHeads2;
    private List<McParamsModel.McLineBean> mFullData2;

    private List<McParamsModel.McLineBean> mNoSameData;
    private SparseArray<McParamsModel> mNoSameHeads;

    private final List COMMON_EMPTY = Collections.EMPTY_LIST;
    private McCarCompareModel mData;

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

        mFullData2 = new ArrayList<>(128);
        mFullHeads2 = new SparseArray<>(32);

        mNoSameHeads = new SparseArray<>(32);
        mNoSameData = new ArrayList<>(mFullData2.size());


        /*
         * 合并块的数据
         * 1.块非空，且有数据
         * 3.生成全量数据
         * 3.生成非相同列表
         * */
        for (int i = 0, len = data.configurations.size(); i < len; i++) {
            McParamsModel mcParamsModel = data.configurations.get(i);
            if (mcParamsModel != null && mcParamsModel.items != null) {
                mFullData2.addAll(mcParamsModel.items);
                mFullHeads2.put(mFullData2.size() - 1, mcParamsModel);

                List<McParamsModel.McLineBean> noSame = mcParamsModel.getNoSame();
                if (noSame != null && !noSame.isEmpty()) {
                    mNoSameData.addAll(noSame);
                    mNoSameHeads.put(mNoSameData.size() - 1, mcParamsModel);
                }
            }
        }

        /*
         * 处理行数据
         * 1.计算行高
         * 3.按需添加行末空项（添加车型）
         * */
        for (int i = 0; i < mFullData2.size(); i++) {
            McParamsModel.McLineBean lineBean = mFullData2.get(i);
            mCalculate.preMeasureLineHeight(lineBean);

            if (lineBean.values != null && neeadAddExtra) {
                lineBean.values.add(COMMON_EMPTY);
            }
        }


        int carMostHeight = mCalculate.preMeasureCarHeight(data.model_infos);

        long end = System.currentTimeMillis();
        Log.i("hh", "McCompareController  : setData: cost: " + (end - start));

        mCurrentData = mHideSame ? mNoSameData : mFullData2;
        mCurrentHeads = mHideSame ? mNoSameHeads : mFullHeads2;


        mView.setData(data.model_infos, mCurrentData, mCurrentHeads, carMostHeight);
    }

    //跳转指定位置
    public void jumpTo(McParamsModel data, int pos) {
        if (pos == 0) {
            mView.scrollTo(0);
        } else {
            int to = mCurrentHeads.keyAt(pos - 1) + 1;
            Log.i("hh", "McCompareController  : jumpTo: " + to);
            mView.scrollTo(to);
        }
    }


    //打开菜单
    public void openMenu() {
        if (mCurrentHeads == null || mCurrentHeads.size() == 0) {
            mView.showMenu(0);
            return;
        }
        LinearLayoutManager layoutManager = (LinearLayoutManager) mView.getParamsView().getLayoutManager();
        if (layoutManager != null) {
            int pos = layoutManager.findFirstVisibleItemPosition();
            int index = mCurrentHeads.indexOfKey(pos);
            if (index < 0) {
                index = ~index;
            }
            mView.showMenu(index);
        }
    }


}
