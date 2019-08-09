-- MySQL dump 10.13  Distrib 5.7.23, for Linux (x86_64)
--
-- Host: localhost    Database: gatools
-- ------------------------------------------------------
-- Server version	5.7.23

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `t_alarmdaylog`
--

DROP TABLE IF EXISTS `t_alarmDayLog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_alarmDayLog` (
  `dayLogId` bigint(20) NOT NULL AUTO_INCREMENT,
  `totalLinkNum` int(11) NOT NULL DEFAULT '0',
  `alarmLinkNum` int(11) NOT NULL DEFAULT '0',
  `totalDeviceNum` int(11) NOT NULL,
  `alarmDeviceNum` int(11) NOT NULL DEFAULT '0',
  `totalTaskNum` int(11) NOT NULL DEFAULT '0',
  `alarmTaskNum` int(11) NOT NULL,
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`dayLogId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_alarmlog`
--

DROP TABLE IF EXISTS `t_alarmLog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_alarmLog` (
  `logId` bigint(20) NOT NULL AUTO_INCREMENT,
  `bid` bigint(20) NOT NULL,
  `bType` smallint(6) NOT NULL,
  `type` smallint(6) NOT NULL,
  `level` smallint(6) NOT NULL,
  `msg` varchar(1000) NOT NULL,
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`logId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_check`
--

DROP TABLE IF EXISTS `t_check`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_check` (
  `checkId` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '检查id',
  `checkName` varchar(100) NOT NULL COMMENT '检查名称，代码拼装',
  `checkType` tinyint(4) NOT NULL COMMENT '检查类型，',
  `uid` bigint(20) NOT NULL COMMENT '触发人，系统自动触发时为 -1',
  `createTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updateTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`checkId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_checkitem`
--

DROP TABLE IF EXISTS `t_checkItem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_checkItem` (
  `itemId` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '项目id',
  `checkId` bigint(20) NOT NULL COMMENT '检查id',
  `resultMsg` varchar(1000) NOT NULL,
  `step` int(8) NOT NULL,
  `totalStep` int(8) NOT NULL,
  `resultLevel` tinyint(4) NOT NULL COMMENT '结果级别',
  `itemType` tinyint(4) NOT NULL COMMENT '检查项目类型',
  `errorType` tinyint(4) NOT NULL COMMENT '错误类型',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`itemId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_config`
--

DROP TABLE IF EXISTS `t_config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_config` (
  `configKey` varchar(100) NOT NULL COMMENT '配置key ，主键',
  `configData` varchar(1000) NOT NULL COMMENT '配置数据',
  PRIMARY KEY (`configKey`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_device`
--

DROP TABLE IF EXISTS `t_device`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_device` (
  `deviceId` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `deviceName` varchar(100) NOT NULL COMMENT '设备名称',
  `ip` varchar(50) NOT NULL,
  `deviceType` tinyint(4) NOT NULL COMMENT '设备类型',
  `area` tinyint(4) NOT NULL COMMENT '所属区域',
  `netArea` tinyint(4) NOT NULL COMMENT '网络区域',
  `icon` varchar(10) NOT NULL COMMENT '图标',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`deviceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_deviceservice`
--

DROP TABLE IF EXISTS `t_deviceService`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_deviceService` (
  `serviceId` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `deviceId` bigint(20) NOT NULL COMMENT '设备id，1对N',
  `ip` varchar(50) NOT NULL,
  `serviceName` varchar(255) NOT NULL COMMENT '服务名称',
  `serviceType` tinyint(4) NOT NULL COMMENT '服务类型',
  `configData` varchar(1000) DEFAULT NULL COMMENT '配置数据包',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`serviceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_knowledge`
--

DROP TABLE IF EXISTS `t_knowledge`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_knowledge` (
  `knowledgeId` bigint(20) NOT NULL AUTO_INCREMENT,
  `alarmType` int(11) NOT NULL,
  `knowledgeDesc` varchar(1000) NOT NULL,
  `uid` bigint(20) NOT NULL,
  `upNum` int(11) NOT NULL DEFAULT '1',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`knowledgeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_link`
--

DROP TABLE IF EXISTS `t_link`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_link` (
  `linkId` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `linkName` varchar(50) NOT NULL COMMENT '链路名称',
  `linkType` tinyint(4) NOT NULL COMMENT '链路类型',
  `dbConfigData` varchar(500) NOT NULL COMMENT '数据库配置json',
  `topologyNodes` varchar(2000) DEFAULT NULL,
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '0:正常；-1:删除',
  PRIMARY KEY (`linkId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_source`
--

DROP TABLE IF EXISTS `t_source`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_source` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `targetId` varchar(255) NOT NULL,
  `linkId` bigint(20) NOT NULL,
  `sname` varchar(255) NOT NULL,
  `ip` varchar(255) NOT NULL,
  `port` smallint(6) NOT NULL,
  `netArea` tinyint(4) NOT NULL DEFAULT '1' COMMENT '网络区域：内网，外网',
  `dbName` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `pwd` varchar(255) NOT NULL,
  `dbVersion` varchar(255) DEFAULT NULL,
  `sourceType` varchar(255) NOT NULL,
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_syslog`
--

DROP TABLE IF EXISTS `t_syslog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_syslog` (
  `logId` bigint(20) NOT NULL AUTO_INCREMENT,
  `host` varchar(50) DEFAULT NULL,
  `level` tinyint(4) NOT NULL,
  `facility` tinyint(4) NOT NULL,
  `msg` varchar(2000) NOT NULL,
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`logId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_task`
--

DROP TABLE IF EXISTS `t_task`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_task` (
  `taskId` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '无意义主键',
  `targetTaskId` varchar(20) NOT NULL COMMENT '任务id，来自不同数据库，可能重复，不能做主键',
  `taskName` varchar(50) NOT NULL,
  `linkId` bigint(20) NOT NULL,
  `status` smallint(6) DEFAULT '0',
  `taskType` tinyint(4) NOT NULL DEFAULT '-1' COMMENT '任务类型',
  `level` tinyint(4) NOT NULL DEFAULT '1' COMMENT '1正常',
  PRIMARY KEY (`taskId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_taskdaylog`
--

DROP TABLE IF EXISTS `t_taskDayLog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_taskDayLog` (
  `logId` bigint(20) NOT NULL AUTO_INCREMENT,
  `targetTaskId` varchar(20) NOT NULL,
  `targetLogId` varchar(20) NOT NULL,
  `linkId` bigint(20) NOT NULL,
  `successNum` bigint(20) NOT NULL DEFAULT '0',
  `successFlow` bigint(20) NOT NULL DEFAULT '0',
  `errorNum` bigint(20) NOT NULL DEFAULT '0',
  `errorFlow` bigint(20) NOT NULL DEFAULT '0',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`logId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `t_tasksource`
--

DROP TABLE IF EXISTS `t_taskSource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_taskSource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `targetId` varchar(50) NOT NULL,
  `linkId` bigint(20) NOT NULL,
  `taskId` varchar(20) NOT NULL,
  `fromResourceId` varchar(20) NOT NULL,
  `toResourceId` varchar(20) NOT NULL,
  `createTime` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_user`
--

DROP TABLE IF EXISTS `t_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_user` (
  `uid` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL COMMENT '真名',
  `iconId` varchar(20) DEFAULT NULL,
  `uName` varchar(100) NOT NULL COMMENT '登陆名称',
  `phone` varchar(20) NOT NULL,
  `email` varchar(20) NOT NULL,
  `pwd` varchar(100) NOT NULL,
  `createUid` bigint(20) NOT NULL,
  `status` tinyint(4) NOT NULL,
  `wrongPwdNum` tinyint(4) NOT NULL,
  `auth` tinyint(4) NOT NULL,
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `loginIpStart` varchar(50) DEFAULT NULL,
  `loginIpEnd` varchar(50) DEFAULT NULL,
  `loginTimeStart` varchar(20) DEFAULT NULL,
  `loginTimeEnd` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;


INSERT INTO `t_user` VALUES (-1, '系统', '1', '系统', '13888888888', '13888888888@mail.com', 'E10ADC3949BA59ABBE56E057F20F883E', 0, 1, 0, 2, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_user` VALUES (1, 'admin', '1', 'admin', '18888888888', '18888888888@mail.com', 'E10ADC3949BA59ABBE56E057F20F883E', 0, 1, 0, 2, NULL, NULL, NULL, NULL, NULL, NULL);


--
-- Table structure for table `t_userlog`
--

DROP TABLE IF EXISTS `t_userLog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_userLog` (
  `userLogId` bigint(20) NOT NULL AUTO_INCREMENT,
  `uid` bigint(20) NOT NULL COMMENT '登陆用户id',
  `uName` varchar(255) NOT NULL COMMENT '用户名称',
  `loginIp` varchar(20) NOT NULL,
  `moduleName` varchar(20) NOT NULL COMMENT '模块名称',
  `msg` varchar(200) NOT NULL COMMENT '日志内容',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '日志时间',
  PRIMARY KEY (`userLogId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-01-14 15:57:08
