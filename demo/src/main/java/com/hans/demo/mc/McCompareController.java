package com.hans.demo.mc;

import android.app.Activity;
import android.util.Log;
import android.util.SparseArray;

import java.util.ArrayList;
import java.util.Arrays;
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

        setData(getTempData());
    }

    public void deleteCar(McCarSummary data) {

    }

    //添加车型
    public void addCar() {


    }

    public void hideSameTo(boolean checked) {

    }

    public void setData(McCarCompareModel data) {
        if (data == null || data.configurations == null || data.configurations.isEmpty()
                || data.model_infos == null || data.model_infos.isEmpty()) {
            return;
        }

        long start = System.currentTimeMillis();
        List<McParamsModel.McLineBean> lines = new ArrayList<>(128);
        SparseArray<McParamsModel> heads = new SparseArray<>(32);
        for (int i = 0, len = data.configurations.size(); i < len; i++) {
            McParamsModel mcParamsModel = data.configurations.get(i);
            heads.put(lines.size(), mcParamsModel);
            if (mcParamsModel != null && mcParamsModel.items != null) {
                lines.addAll(mcParamsModel.items);
            }
        }

        for (int i = 0, len = lines.size(); i < len; i++) {
            McParamsModel.McLineBean line = lines.get(i);
            mCalculate.preMeasureLineHeight(line);
        }

        int carMostHeight = mCalculate.preMeasureCarHeight(data.model_infos);

        long end = System.currentTimeMillis();
        Log.i("hh", "McCompareController  : setData: cost: " + (end - start));
        mView.setData(data.model_infos, lines, heads);
    }

    public McCarCompareModel getTempData() {
        McCarCompareModel mTempData = new McCarCompareModel();
        mTempData.model_infos = new ArrayList<>(8);
        mTempData.model_infos.add(createData("发大水发动机卡花费很大", "发大水发的撒范德萨范德萨范德萨范德萨"));
        mTempData.model_infos.add(createData("发顺丰大范德萨范德萨", "肥嘟嘟多多多"));
        mTempData.model_infos.add(createData("范德萨发", "阿范德萨"));
        mTempData.model_infos.add(createData("范德萨", "范德萨范德萨发的"));
        mTempData.model_infos.add(createData("发生发大水范德萨发房打算范德萨范德萨", "放到"));
        mTempData.model_infos.add(createData("放到", "范德萨范德萨范德萨范德萨范德萨范德萨范德萨范德范德萨范德萨范德萨范德萨萨范德萨"));
        mTempData.model_infos.add(createData("发生发的撒", "范德萨范德萨范德萨"));
        mTempData.model_infos.add(createData("放到", "范德萨范德萨范德萨"));

        mTempData.configurations = new ArrayList<>(8);

        mTempData.configurations.add(createData2("标题", "副标题"));
        mTempData.configurations.add(createData2("标题2", "副标题2"));
        mTempData.configurations.add(createData2("标题3", "副标题3"));
        mTempData.configurations.add(createData2("标题4", "副标题4"));
        mTempData.configurations.add(createData2("标题5", "副标题5"));


        return mTempData;
    }

    //创建大块
    private McParamsModel createData2(String title, String subTitle) {

        McParamsModel model = new McParamsModel();
        model.name = title;
        model.sub_title = subTitle;

        model.items = new ArrayList<>(8);
        model.items.add(createData3("车型配置"));

        return model;
    }

    //创建行数据
    private McParamsModel.McLineBean createData3(String name) {
        McParamsModel.McLineBean line = new McParamsModel.McLineBean();
        line.name = name;
        line.values = new ArrayList<>(8);
        line.values.add(createData4("发达范德萨", "范德萨范德萨发发的说说的说说 "));
        line.values.add(createData4("范德", "范德萨范德萨看解放军的撒娇 范德萨范德萨范德萨"));
        line.values.add(createData4("范德萨范德萨发", "范德萨", "fsadfdafds", "fdasfdasfadsfds"));
        line.values.add(createData4("ddsafds", "fesafdsafd"));
        line.values.add(createData4("发顺丰的发放的范德萨发撒说法"));
        line.values.add(createData4("地方撒地方萨芬", "fdsafda"));
        line.values.add(createData4("范德萨范德萨发大水发的说法是", "范德萨"));
        line.values.add(createData4("范德萨", "副", "fdsafds", "范德萨发大水发的范德萨范德萨范德萨发的说法"));
        return line;
    }

    //创建单元格数据，多行
    private List<String> createData4(String... s) {
        return Arrays.asList(s);
    }


    public McCarSummary createData(String seriesName, String carName) {
        McCarSummary carSummary = new McCarSummary();
        carSummary.series_name = seriesName;
        carSummary.model_name = carName;
        return carSummary;

    }
}
