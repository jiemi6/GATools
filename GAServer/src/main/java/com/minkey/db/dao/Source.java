package com.minkey.db.dao;

import com.minkey.dto.DBConfigData;

import java.util.Date;

/**
 * 数据源对象
 *
 * 复用，当为ftp时，dbname存的是ftp路径
 */
public class Source extends  DBConfigData{

    /**
     * 主键
     */
    private long id;

    /**
     * 来源方主键
     */
    private String targetId;

    /**
     * 冗余链路id
     */
    private long linkId;

    /**
     * 名称
     */
    private String sname;

    /**
     * 非数据库为空
     */
    private String dbVersion;

    /**
     * 	无格式文件  ftp
     * 	数据库
     * 	格式文件 视频
     */
    private String sourceType;

    /**
     * 创建时间
     */
    private Date createTime;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public long getLinkId() {
        return linkId;
    }

    public void setLinkId(long linkId) {
        this.linkId = linkId;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getDbVersion() {
        return dbVersion;
    }

    public void setDbVersion(String dbVersion) {
        this.dbVersion = dbVersion;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Source{" +
                "id=" + id +
                ", targetId='" + targetId + '\'' +
                ", linkId=" + linkId +
                ", sname='" + sname + '\'' +
                ", dbVersion='" + dbVersion + '\'' +
                ", sourceType='" + sourceType + '\'' +
                ", createTime=" + createTime +
                "} " + super.toString();
    }
}
