package com.minkey.db.dao;

/**
 * 任务对象，数据来自别的数据库
 */
public class Task {

    /**
     * 任务id，主键
     */
    private long taskId;

    /**
     * 链路id
     */
    private long linkId;

    /**
     * 任务名称
     */
    private String taskName;


    /**
     * 从数据交换系统获取的数据本身
     */
    private String data;



}
