package com.lp.kh.springbootlpkh.vo;


import lombok.Data;
import lombok.ToString;

public class DimensionGroupVO {
    //@ApiModelProperty("维度名称")
    private String dimensionName;
    //@ApiModelProperty("维度中出现警告和严重的次数")
    private String dimensionCount;


    public String getDimensionName() {
        return dimensionName;
    }

    public void setDimensionName(String dimensionName) {
        this.dimensionName = dimensionName;
    }

    public String getDimensionCount() {
        return dimensionCount;
    }

    public void setDimensionCount(String dimensionCount) {
        this.dimensionCount = dimensionCount;
    }


    @Override
    public String toString() {
        return "DimensionGroupVO{" +
                "dimensionName='" + dimensionName + '\'' +
                ", dimensionCount='" + dimensionCount + '\'' +
                '}';
    }
}