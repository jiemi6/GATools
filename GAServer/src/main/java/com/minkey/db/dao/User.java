package com.minkey.db.dao;

import java.util.Date;

/**
 * 用户
 */
public class User {

    /**
     * 用户id，自增主键
     */
    private Long uid;

    /**
     * 用户名称
     */
    private String uName;

    /**
     * 密码
     */
    private String pwd;

    /**
     * 创建人
     */
    private Long createUid;

    /**
     * 用户状态：
     * <br> 1 : 正常
     * <br> 2 : 锁定，密码输入多次后，锁定
     */
    private Integer status = STATUS_NORMAL;

    /**
     * 用户状态：正常
     */
    public static final int  STATUS_NORMAL = 1;
    /**
     * 用户状态：锁定
     */
    public static final int  STATUS_LOCK = 2;

    /**
     * 用户密码输入错误次数
     */
    private Integer wrongPwdNum = 0;

    /**
     * 用户密码输入错误最大次数
     */
    public static final int MAX_WRONGPWDNUM = 5;

    /**
     * 创建时间
     */
    private Date createTime;


    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 用户权限
     * <br> 1 : 只读
     * <br> 2 : 读写
     */
    private Integer auth = AUTH_R;


    /**
     * 用户权限:只读
     */
    public static final int  AUTH_R = 1;
    /**
     * 用户权限： 读写
     */
    public static final int  AUTH_WR = 2;

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Long getCreateUid() {
        return createUid;
    }

    public void setCreateUid(Long createUid) {
        this.createUid = createUid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getWrongPwdNum() {
        return wrongPwdNum;
    }

    public void setWrongPwdNum(Integer wrongPwdNum) {
        this.wrongPwdNum = wrongPwdNum;
    }

    public Integer getAuth() {
        return auth;
    }

    public void setAuth(Integer auth) {
        this.auth = auth;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
