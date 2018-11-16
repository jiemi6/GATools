package com.minkey.contants;

public final class MyLevel {

    public final static int LEVEL_NORMAL = 1;

    public final static int LEVEL_WARN = 2;

    public final static int LEVEL_ERROR = 3;

    public final static String getString8level(int level){
        switch (level){
            case  LEVEL_ERROR:
                return "错误";
            case  LEVEL_WARN:
                return "警告";
            case  LEVEL_NORMAL:
                return "正常";
            default:
                return "未知";
        }
    }
}
