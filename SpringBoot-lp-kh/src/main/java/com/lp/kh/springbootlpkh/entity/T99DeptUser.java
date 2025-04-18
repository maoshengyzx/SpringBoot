package com.lp.kh.springbootlpkh.entity;

import java.io.Serializable;

/**
 * 部门用户关系表(T99DeptUser)实体类
 *
 * @author makejava
 * @since 2025-01-03 11:08:59
 */
public class T99DeptUser implements Serializable {
    private static final long serialVersionUID = -59796699209144246L;
    /**
     * 部门ID
     */
    private String deptId;
    /**
     * 用户ID
     */
    private String userId;


    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}

