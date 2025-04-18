package com.lp.kh.springbootlpkh.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * 用户表(T99User)实体类
 *
 * @author makejava
 * @since 2025-01-03 11:09:00
 */
public class T99User implements Serializable {
    private static final long serialVersionUID = -47461006623771357L;
    /**
     * （主键）用户ID
     */
    private String userId;
    /**
     * 姓名
     */
    private String userName;
    /**
     * 密码
     */
    private String password;
    /**
     * 性别
     */
    private String sex;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 移动电话
     */
    private String mobilePhone;
    /**
     * 工作电话
     */
    private String workPhone;
    /**
     * 创建人ID
     */
    private String creatorId;
    /**
     * 创建人姓名
     */
    private String creatorName;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 最后修改人ID
     */
    private String lastModifierId;
    /**
     * 最后修改人姓名
     */
    private String lastModifierName;
    /**
     * 最后修改时间
     */
    private Date lastModifyTime;
    /**
     * 有效标识(用户是否有效：1，有效；0，无效)，无效用户不允许登录
     */
    private String validFlag;
    /**
     * 失效时间
     */
    private Date expireTime;
    /**
     * 是否是管理员, Y:是， N: 否
     */
    private String admin;
    /**
     * 如内置用户不能删除、不能修改。
     */
    private String builtIn;
    /**
     * 描述
     */
    private String description;
    /**
     * 认证源;1:本地认证;2:LDAP
     */
    private String verifySource;
    /**
     * 生日
     */
    private Date birthday;
    /**
     * 真实姓名
     */
    private String realName;
    /**
     * 用户头像附件id
     */
    private Long avatarAttachId;
    /**
     * 微信号
     */
    private String weixin;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getWorkPhone() {
        return workPhone;
    }

    public void setWorkPhone(String workPhone) {
        this.workPhone = workPhone;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getLastModifierId() {
        return lastModifierId;
    }

    public void setLastModifierId(String lastModifierId) {
        this.lastModifierId = lastModifierId;
    }

    public String getLastModifierName() {
        return lastModifierName;
    }

    public void setLastModifierName(String lastModifierName) {
        this.lastModifierName = lastModifierName;
    }

    public Date getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    public String getValidFlag() {
        return validFlag;
    }

    public void setValidFlag(String validFlag) {
        this.validFlag = validFlag;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public String getBuiltIn() {
        return builtIn;
    }

    public void setBuiltIn(String builtIn) {
        this.builtIn = builtIn;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVerifySource() {
        return verifySource;
    }

    public void setVerifySource(String verifySource) {
        this.verifySource = verifySource;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Long getAvatarAttachId() {
        return avatarAttachId;
    }

    public void setAvatarAttachId(Long avatarAttachId) {
        this.avatarAttachId = avatarAttachId;
    }

    public String getWeixin() {
        return weixin;
    }

    public void setWeixin(String weixin) {
        this.weixin = weixin;
    }

}

