package com.minkey.db.dao;

import com.minkey.contants.CommonContants;
import com.minkey.dto.DBConfigData;
import org.springframework.boot.jdbc.DatabaseDriver;

import java.util.Date;

/**
 * 数据源对象
 *
 * 复用，当为ftp时，super.dbname存的是ftp路径
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
     * 内外网
     * @see com.minkey.contants.CommonContants
     */
    private int netArea;

    /**
     * 资源名称，来自数据源方
     */
    private String sname;

    /**
     * 非数据库为空
     */
    private String dbVersion;

    /**
     * 共三种情况
     * 	1 "无格式文件"  ftp
     * 	2 "数据库"
     * 	3 "格式文件" 视频
     */
    private String sourceType;
    public static final String sourceType_db = "数据库";
    public static final String sourceType_ftp = "无格式文件";
    public static final String sourceType_video = "格式文件";

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

    public int getNetArea() {
        return netArea;
    }

    public void setNetArea(int netArea) {
        this.netArea = netArea;
    }

    public boolean isNetAreaIn(){
        return netArea == CommonContants.NETAREA_IN;
    }

    @Override
    public DatabaseDriver getDatabaseDriver() {
        //重写父类的获取数据库类型方法，根据拿过来的dbVersion字符串判断
        String lowerStr = dbVersion.toLowerCase();
        if(lowerStr.contains("oracle")){
            return DatabaseDriver.ORACLE;
        }else if(lowerStr.contains("sqlserver")){
            return DatabaseDriver.SQLSERVER;
        }else{
            //默认都是mysql
            return DatabaseDriver.MYSQL;
        }
    }

    @Override
    public String toString() {
        return "Source{" +
                "id=" + id +
                ", targetId='" + targetId + '\'' +
                ", linkId=" + linkId +
                ", netArea=" + netArea +
                ", sname='" + sname + '\'' +
                ", dbVersion='" + dbVersion + '\'' +
                ", sourceType='" + sourceType + '\'' +
                ", createTime=" + createTime +
                "} " + super.toString();
    }
}
