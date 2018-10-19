package com.minkey.db.dao;

import java.util.Date;

/**
 * 用户操作日志
 */
public class UserLog {

    /**
     * 日志id，自增主键
     */
    private Long userLogId;

    /**
     * 用户id
     */
    private long uid;

    /**
     * 登陆ip
     */
    private String loginIp;

    /**
     * 用户名称
     */
    private String uName;

    /**
     * 模块名称
     */
    private String moduleName;

    /**
     * 日志内容
     */
    private String msg;

    private Date createTime;

    public Long getUserLogId() {
        return userLogId;
    }

    public void setUserLogId(Long userLogId) {
        this.userLogId = userLogId;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
