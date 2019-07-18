package com.minkey.util;


import com.alibaba.fastjson.JSONObject;
import com.minkey.contants.AlarmEnum;
import com.minkey.dto.FTPConfigData;
import com.minkey.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.nio.charset.Charset;

@Slf4j
@Component
public class FTPUtil {
    public static final int default_timeout = 1000;

    public boolean testFTPConnect(FTPConfigData ftpConfigData,int timeout) throws SystemException{
        return testFTPConnect(ftpConfigData.getIp(),ftpConfigData.getPort(),
                ftpConfigData.getName(),ftpConfigData.getPwd(),ftpConfigData.getRootPath(),timeout);
    }

    /**
     * 只是检查ftp连接情况
     * @param ip
     * @param port
     * @param user
     * @param pwd
     * @param rootPath
     * @param timeout
     * @return
     * @throws SystemException
     */
    private boolean testFTPConnect(String ip,int port,String user,String pwd,String rootPath,int timeout) throws SystemException{
        FTPClient ftpClient = new FTPClient();
        try {

            ftpClient.setCharset(Charset.forName("UTF-8"));
            ftpClient.setControlEncoding("UTF-8");
            ftpClient.setConnectTimeout(timeout);
            //先联网
            ftpClient.connect(ip, port);
            boolean isConnect =  ftpClient.isConnected();

            // 检测连接是否成功
            int reply = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                throw new SystemException(AlarmEnum.port_notConnect,String.format("ftp网络连接失败%s:%s/%s:%s",ip,port,user,pwd));
            }
            //再登陆
            boolean login = ftpClient.login(user, pwd);

            if (!login) {
                throw new SystemException(AlarmEnum.ftp_wrongpwd,String.format("ftp连接账号密码错误%s:%s/%s:%s",ip,port,user,pwd));
            }

            return isConnect;
        } catch (SocketTimeoutException e) {
            throw new SystemException(AlarmEnum.port_notConnect,String.format("ftp网络连接超时%s:%s/%s:%s,msg=%s",ip,port,user,pwd,e));
        } catch (SocketException e) {
            //Connection refused 网络不通
            throw new SystemException(AlarmEnum.port_notConnect,String.format("ftp网络连接错误%s:%s/%s:%s,msg=%s",ip,port,user,pwd,e));
        } catch (IOException e) {
            throw new SystemException(AlarmEnum.ftp_io_error,String.format("ftp网络IO异常%s:%s/%s:%s,msg=%s",ip,port,user,pwd,e));
        }finally {
            close(ftpClient);
        }
    }

    /**
     * 测试ftp源
     * @param ftpConfigData
     * @param timeout
     * @return
     * @throws SystemException
     */
    public JSONObject testFTPSource(FTPConfigData ftpConfigData, int timeout) {
        return testFTPSource(ftpConfigData.getIp(),ftpConfigData.getPort(),
                ftpConfigData.getName(),ftpConfigData.getPwd(),ftpConfigData.getRootPath(),timeout);
    }

    /**
     * 测试ftp源
     * @param ip
     * @param port
     * @param user
     * @param pwd
     * @param rootPath
     * @param timeout
     * @return
     * @throws SystemException
     */
    private JSONObject testFTPSource(String ip, int port, String user, String pwd, String rootPath, int timeout){
        FTPClient ftpClient = new FTPClient();
        JSONObject resultObject = new JSONObject();
        maxFloorNum = 0;
        topDirNum = 0;
        try {
            ftpClient.setCharset(Charset.forName("UTF-8"));
            ftpClient.setControlEncoding("UTF-8");
            ftpClient.setConnectTimeout(timeout);
            //先联网
            ftpClient.connect(ip, port);
            boolean isConnect =  ftpClient.isConnected();


            // 检测连接是否成功
            int reply = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                resultObject.put("alarmType",AlarmEnum.port_notConnect.getAlarmType());
            }
            //再登陆
            boolean login = ftpClient.login(user, pwd);

            if (!login) {
                resultObject.put("alarmType",AlarmEnum.ftp_wrongpwd.getAlarmType());
            }

            if(isConnect && login){
                boolean isRootLook = checkRootLook(ftpClient);
                resultObject.put("isRootLook",isRootLook);
                //切换到工作目录
                ftpClient.changeWorkingDirectory(rootPath);

                boolean isPassive = checkMode(ftpClient);
                resultObject.put("isPassive",isPassive);

                String allAuth = checkAuth(ftpClient);
                resultObject.put("allAuth",allAuth);

                if(StringUtils.isEmpty(rootPath)){
                    resultObject.put("totalFileNum",0);
                }else{
                    int totalFileNum = countFileNum(ftpClient,rootPath,0);
                    resultObject.put("totalFileNum",totalFileNum);
                }
                resultObject.put("topDirNum",topDirNum);
                resultObject.put("floorNum",maxFloorNum);
            }

        } catch (SocketTimeoutException e) {
            resultObject.put("alarmType",AlarmEnum.port_notConnect.getAlarmType());
            log.debug(String.format("ftp连接超时%s:%s/%s:%s,msg=%s",ip,port,user,pwd,e));
        } catch (SocketException e) {
            //Connection refused 网络不通
            resultObject.put("alarmType",AlarmEnum.port_notConnect.getAlarmType());
            log.debug(String.format("ftp连接异常%s:%s/%s:%s,msg=%s",ip,port,user,pwd,e));
        } catch (IOException e) {
            resultObject.put("alarmType",AlarmEnum.ftp_io_error.getAlarmType());
            log.debug(String.format("ftp-IO异常%s:%s/%s:%s,msg=%s",ip,port,user,pwd,e));
        }finally {
            close(ftpClient);
        }

        return resultObject;
    }


    /**
     * 关闭FTP服务器
     */
    public void close(FTPClient ftpClient ){
        try {
            if(ftpClient != null){
                if(ftpClient.isConnected()){
                    ftpClient.logout();
                    ftpClient.disconnect();
                }
                ftpClient = null;
            }
        } catch (IOException e) { }
    }


    private boolean checkRootLook(FTPClient ftpClient){
        try {
            boolean result = ftpClient.changeWorkingDirectory("/");

            return !result;
        } catch (IOException e) {
            log.error("切换到ftp主目录异常.");
            return false;
        }

    }

    /**
     * 最大目录层级
     */
    private static int maxFloorNum = 0;
    /**
     * 根目录下文件夹数量
     */
    private static int topDirNum = 0;

    /**
     * 递归遍历出目录下面所有文件
     *
     * @param pathName 需要遍历的目录，必须以"/"开始和结束
     * @throws IOException
     */
    private int countFileNum(FTPClient ftpClient,String pathName,int startFloor) {
        //如果当前层级大于最大层级,则赋值
        if(startFloor > maxFloorNum){
            maxFloorNum = startFloor;
        }
        //层级加1
        startFloor ++;
        int total = 0;
        if (pathName.startsWith("/")  && pathName.endsWith("/")) {
            FTPFile[] files ;
            try {
                //更换目录到当前目录
                ftpClient.changeWorkingDirectory(pathName);

                files = ftpClient.listFiles();
            } catch (IOException e) {
                log.error("遍历ftp文件数量异常"+e.getMessage());
                return total;
            }
            for (FTPFile file : files) {
                if (file.isFile()) {
                    total ++;
                } else if (file.isDirectory()) {
                    // 需要加此判断。否则，ftp默认将‘项目文件所在目录之下的目录（./）’与‘项目文件所在目录向上一级目录下的目录（../）’都纳入递归，这样下去就陷入一个死循环了。需将其过滤掉。
                    if (!".".equals(file.getName()) && !"..".equals(file.getName())) {
                        total = total + countFileNum(ftpClient,pathName + file.getName() + "/",startFloor);

                        //根目录下文件夹数量
                        if(startFloor == 1){
                            topDirNum ++;
                        }
                    }
                }
            }
        }
        return total;
    }


    /**
     * 检查文件夹的 读写删权限
     * @param ftpClient
     * @return A:add ; D:del ; R read
     */
    private String checkAuth(FTPClient ftpClient) {
        StringBuffer auth = new StringBuffer();
        String fileName = "testAuth";
        InputStream inputStream = new ByteArrayInputStream("test".getBytes());
        try {
            //先加文件
            boolean addSuccess = ftpClient.storeFile(fileName,inputStream);
            if(addSuccess){
                auth.append("ADD");
            }
        } catch (IOException e) {
            //没有新增权限
            log.debug("ftp新增文件异常"+e.getMessage());
        }

        try {
            FTPFile[] file =  ftpClient.listFiles();
            if(file == null || file.length == 0){
                log.debug("ftp 读权限为空");
            }else{
                auth.append(",READ");
            }
        } catch (IOException e) {
            //没有读取权限
            log.debug("ftp读取文件异常"+e.getMessage());

        }

        try {
           boolean delSuccess = ftpClient.deleteFile(fileName);
           if(delSuccess){
               auth.append(",DEL");
           }
        } catch (IOException e) {
            //没有删除权限
            log.debug("ftp删除文件异常"+e.getMessage());
        }
        return auth.toString();
    }

    /**
     * 检查ftp服务器端是否为被动模式
     * @param ftpClient
     * @return false : 主动模式, true 被动模式
     */
    private boolean checkMode(FTPClient ftpClient) {
        String fileName = "testMode";
        InputStream inputStream = new ByteArrayInputStream("testMode".getBytes());
        try {
            //设置为主动
            ftpClient.enterLocalActiveMode();
            //加文件,
            boolean addSuccess = ftpClient.storeFile(fileName,inputStream);
            //如果是false
            if(addSuccess){
                return false;
            }else{
                //设置为被动 在试一次
                ftpClient.enterLocalPassiveMode();
                addSuccess = ftpClient.storeFile(fileName,inputStream);
                if(addSuccess){
                    //证明确实 是被动模式
                    return true;
                }else{
                    //主动模式
                    return false;
                }
            }
        } catch (IOException e) {
            //没有新增权限
            log.debug("ftp新增文件异常"+e.getMessage());
            return false;
        }finally {
            try {
                boolean delS =ftpClient.deleteFile(fileName);
            } catch (IOException e) {
            }
        }
    }




}
