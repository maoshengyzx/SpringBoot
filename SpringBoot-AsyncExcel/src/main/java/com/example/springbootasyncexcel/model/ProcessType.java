package com.example.springbootasyncexcel.model;

/**
 * @author Administrator
 */

public enum ProcessType {

    EXCEL_TYPE("Excel类型", ProcessTypeEnum.EXCEL_TYPE);

    private final String value;

    private final ProcessTypeEnum type;

    ProcessType(String value, ProcessTypeEnum type) {
        this.value = value;
        this.type = type;
    }

    public String getValue() {
        return value;
    }
    public ProcessTypeEnum getType() {
        return type;
    }

    public enum ProcessTypeEnum {
        /**
         * 进程类型
         */
        EXCEL_TYPE("Excel类型", "EXCEL_TYPE");

        private final String desc;
        private final String type;

        ProcessTypeEnum(String desc, String type) {
            this.desc = desc;
            this.type = type;
        }
        public String getDesc() {
            return desc;
        }
        public String getType() {
            return type;
        }
    }
}
