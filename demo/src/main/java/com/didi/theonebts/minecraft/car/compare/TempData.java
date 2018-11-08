package com.didi.theonebts.minecraft.car.compare;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author hanbo
 * @date 2018/11/7
 */
public class TempData {


    public static McCarCompareModel getTempData() {
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
    private static McParamsModel createData2(String title, String subTitle) {

        McParamsModel model = new McParamsModel();
        model.name = title;
        model.sub_title = subTitle;

        model.items = new ArrayList<>(8);
        model.items.add(createData3("车型配置"));
        model.items.add(createData3("大灯配置"));
        model.items.add(createData33("座椅配置"));
        model.items.add(createData3("安全配置"));
        model.items.add(createData333("高级配置"));
        return model;
    }

    //创建行数据
    private static McParamsModel.McLineBean createData3(String name) {
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

    //创建行数据
    private static McParamsModel.McLineBean createData33(String name) {
        McParamsModel.McLineBean line = new McParamsModel.McLineBean();
        line.name = name;
        line.colspan = "1";
        line.values = new ArrayList<>(1);
        line.values.add(createData4("发达范德萨 范德萨范德萨发发的说说的说说 "));
        return line;
    }

    //创建行数据
    private static McParamsModel.McLineBean createData333(String name) {
        McParamsModel.McLineBean line = new McParamsModel.McLineBean();
        line.name = name;
        line.colspan = "1";
        line.values = new ArrayList<>(1);
        line.values.add(createData4("发达范德萨 范德萨范德萨发发的说说的说说 ", "fdafdsadsas", "发大厦发的发的"));
        return line;
    }

    //创建单元格数据，多行
    private static List<String> createData4(String... s) {
        return Arrays.asList(s);
    }


    public static McCarSummary createData(String seriesName, String carName) {
        McCarSummary carSummary = new McCarSummary();
        carSummary.series_name = seriesName;
        carSummary.model_name = carName;
        return carSummary;

    }

}
