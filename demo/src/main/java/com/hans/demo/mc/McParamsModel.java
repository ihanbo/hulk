package com.hans.demo.mc;

import java.io.Serializable;
import java.util.List;

/**
 * @author hanbo
 * @date 2018/11/5
 */
public class McParamsModel {


    /**
     * name : 底盘转向
     * configure_id : 100050000
     * sub_title :
     * items : [{"name":"驱动方式","configure_id":"100060001","field":"drive_type","values":[["前置前驱"],["前置前驱"]],"colspan":""}]
     */

    public String name;
    public String configure_id;
    public String sub_title;
    public List<McLineBean> items;


    //行数据
    public static class McLineBean implements Serializable{
        /**
         * name : 驱动方式
         * configure_id : 100060001
         * field : drive_type
         * values : [["前置前驱"],["前置前驱"]]
         * colspan :
         */

        public String name;
        public String configure_id;
        public String field;
        public boolean colspan;
        public List<List<String>> values;

        public int height;
    }
}
