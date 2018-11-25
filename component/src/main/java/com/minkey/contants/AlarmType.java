package com.minkey.contants;

/**
 * 告警类型
 */
public interface AlarmType {
        /**
         * 网络不通
         */
        public static int wangluobutong = 1;
        /**
         * 设备性能
         */
        public static int shebeixingneng = 2;
        /**
         * 设备服务
         */
        public static int shebeifuwu = 3;

        /**
         * 数据源不存在
         */
        public static int no_source = 4;

        /**
         * 没有配置探针
         */
        public static int no_detector =5;

    }