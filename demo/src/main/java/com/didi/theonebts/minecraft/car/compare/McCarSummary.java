package com.didi.theonebts.minecraft.car.compare;

import java.io.Serializable;

/**
 * @author hanbo
 * @date 2018/11/6
 */
public class McCarSummary implements Serializable {

    /**
     * model_id : 18
     * model_name : 2004款 1.3L AT
     * series_id : 1582
     * series_name : 千里马
     */

    public String model_id;
    public String model_name;
    public String series_id;
    public String series_name;


    static class McAddCar extends McCarSummary implements Serializable {

    }
}
