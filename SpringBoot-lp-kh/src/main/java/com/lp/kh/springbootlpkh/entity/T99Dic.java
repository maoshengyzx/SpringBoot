package com.lp.kh.springbootlpkh.entity;

import java.io.Serializable;

/**
 * 字典表(T99Dic)实体类
 *
 * @author makejava
 * @since 2025-01-03 11:09:00
 */
public class T99Dic implements Serializable {
    private static final long serialVersionUID = -64734524352882927L;
    /**
     * 代码
     */
    private String dictCode;
    /**
     * 代码描述
     */
    private String dictCodeDesc;
    /**
     * 代码项
     */
    private String codeItem;
    /**
     * 代码项名
     */
    private String codeItemName;
    /**
     * 显示顺序
     */
    private Object showOrder;


    public String getDictCode() {
        return dictCode;
    }

    public void setDictCode(String dictCode) {
        this.dictCode = dictCode;
    }

    public String getDictCodeDesc() {
        return dictCodeDesc;
    }

    public void setDictCodeDesc(String dictCodeDesc) {
        this.dictCodeDesc = dictCodeDesc;
    }

    public String getCodeItem() {
        return codeItem;
    }

    public void setCodeItem(String codeItem) {
        this.codeItem = codeItem;
    }

    public String getCodeItemName() {
        return codeItemName;
    }

    public void setCodeItemName(String codeItemName) {
        this.codeItemName = codeItemName;
    }

    public Object getShowOrder() {
        return showOrder;
    }

    public void setShowOrder(Object showOrder) {
        this.showOrder = showOrder;
    }

}

