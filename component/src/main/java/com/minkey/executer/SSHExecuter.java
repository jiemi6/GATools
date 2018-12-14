package com.minkey.executer;


import com.jcraft.jsch.*;
import com.minkey.contants.CommonContants;
import com.minkey.dto.BaseConfigData;
import com.minkey.entity.ResultInfo;
import com.minkey.exception.SystemException;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * ssh执行工具
 *
 * @author minkey
 * @version V1.0
 */
@Slf4j
public class SSHExecuter {
    private String charset = Charset.defaultCharset().toString();

    private Session session;

    public SSHExecuter(String host, Integer port, String user, String password,int timeout) throws JSchException {
        connect(host, port, user, password,timeout);
    }

    public SSHExecuter(BaseConfigData baseConfigData) throws JSchException {
        this(baseConfigData, CommonContants.DEFAULT_TIMEOUT);
    }

    public SSHExecuter(BaseConfigData baseConfigData,int timeout) throws JSchException {
        this(baseConfigData.getIp(),baseConfigData.getPort(), baseConfigData.getName(), baseConfigData.getPwd(), timeout);
    }

    /**
     * 测试是否能连上ssh
     * @param baseConfigData
     * @return
     */
    public static boolean testConnect(BaseConfigData baseConfigData){
        SSHExecuter sshExecuter = null;
        try {
            sshExecuter = new SSHExecuter(baseConfigData);

            return true;
        } catch (Exception e) {
            log.error("Test SSH "+e.getMessage());
            return false;
        }finally {
            if(sshExecuter != null){
                sshExecuter.close();
            }
        }
    }

    /**
     * 连接sftp服务器
     *
     * @param ip     远程主机ip地址
     * @param port     sftp连接端口，null 时为默认端口
     * @param user     用户名
     * @param pwd 密码
     * @return
     * @throws JSchException
     */
    private Session connect(String ip, Integer port, String user, String pwd,int timeout) throws SystemException {
        try {
            JSch jsch = new JSch();
            if (port != null) {
                session = jsch.getSession(user, ip, port.intValue());
            } else {
                session = jsch.getSession(user, ip);
            }
            session.setPassword(pwd);
            //设置第一次登陆的时候提示，可选值:(ask | yes | no)
            session.setConfig("StrictHostKeyChecking", "no");
            //连接超时设置
            session.connect(timeout);
            session.isConnected();
        } catch (JSchException e) {
            //Connection refused 网络不通
            throw new SystemException(String.format("SSH 异常，ip=%s,port=%s,user=%s,pwd=%s,msg=%s",ip,port,user,pwd,e.getMessage()));
        }
        return session;
    }

    public ResultInfo sendCmd(String command) throws SystemException {
        return sendCmd(command, 200);
    }

    /**
     * 执行命令，返回执行结果
     *
     * @param command 命令
     * @param delay   估计shell命令执行时间
     * @return String 执行命令后的返回
     * @throws JSchException
     */
    public ResultInfo sendCmd(String command, int delay) throws SystemException {
        if (delay < 50) {
            delay = 50;
        }

        byte[] tmp = new byte[1024];
        //执行SSH返回的结果
        StringBuffer strBuffer = new StringBuffer();

        StringBuffer errResult = new StringBuffer();

        Channel channel = null;
        try {
            channel = session.openChannel("exec");
        } catch (JSchException e) {
            throw new SystemException("打开ssh会话异常",e);
        }
        ChannelExec ssh = (ChannelExec) channel;
        //返回的结果可能是标准信息,也可能是错误信息,所以两种输出都要获取
        //一般情况下只会有一种输出.
        //但并不是说错误信息就是执行命令出错的信息,如获得远程java JDK版本就以
        //ErrStream来获得.
        InputStream stdStream = null;
        InputStream errStream = null;
        try {
            stdStream = ssh.getInputStream();
            errStream = ssh.getErrStream();
        } catch (IOException e) {
            throw new SystemException("构造ssh结果接收流异常",e);
        }

        ssh.setCommand(command);
        try {
            ssh.connect();
        } catch (JSchException e) {
            throw new SystemException("建立ssh连接异常",e);
        }

        try {
            //开始获得SSH命令的结果
            while (true) {
                //获得错误输出
                while (errStream.available() > 0) {
                    int i = errStream.read(tmp, 0, 1024);
                    if (i < 0) {
                        break;
                    }
                    errResult.append(new String(tmp, 0, i));
                }

                //获得标准输出
                while (stdStream.available() > 0) {
                    int i = stdStream.read(tmp, 0, 1024);
                    if (i < 0) {
                        break;
                    }
                    strBuffer.append(new String(tmp, 0, i));
                }
                if (ssh.isClosed()) {
                    int code = ssh.getExitStatus();
                    log.info("exit-status: " + code);
                    ResultInfo result = new ResultInfo(code, strBuffer.toString(), errResult.toString());
                    return result;
                }
                try {
                    Thread.sleep(delay);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            throw new SystemException("读取ssh执行结果异常",e);
        } finally {
            channel.disconnect();
        }

    }

    /**
     * @param in
     * @param charset
     * @return
     */
    private String processStream(InputStream in, String charset) throws Exception {
        byte[] buf = new byte[1024];
        StringBuilder sb = new StringBuilder();
        while (in.read(buf) != -1) {
            sb.append(new String(buf, charset));
        }
        return sb.toString();
    }

    public boolean deleteRemoteFileOrDir(String remoteFile) {
        ChannelSftp channel = null;
        try {
            channel = (ChannelSftp) session.openChannel("sftp");
            channel.connect();
            SftpATTRS sftpATTRS = channel.lstat(remoteFile);
            if (sftpATTRS.isDir()) {
                //目录
                log.debug("remote File:dir");
                channel.rmdir(remoteFile);
                return true;
            } else if (sftpATTRS.isReg()) {
                //文件
                log.debug("remote File:file");
                channel.rm(remoteFile);
                return true;
            } else {
                log.debug("remote File:unkown");
                return false;
            }
        } catch (JSchException e) {
            if (channel != null) {
                channel.disconnect();
                session.disconnect();
            }
            log.error("error", e);
            return false;
        } catch (SftpException e) {
            log.info("meg" + e.getMessage());
            log.error("SftpException", e);
            return false;
        }

    }

    /**
     * 判断linux下 某文件是否存在
     */
    public boolean detectedFileExist(String remoteFile) {

        ChannelSftp channel = null;
        try {
            channel = (ChannelSftp) session.openChannel("sftp");
            channel.connect();
            SftpATTRS sftpATTRS = channel.lstat(remoteFile);
            if (sftpATTRS.isDir() || sftpATTRS.isReg()) {
                //目录 和文件
                log.info("remote File:dir");
                return true;
            } else {
                log.info("remote File:unkown");
                return false;
            }
        } catch (JSchException e) {
            if (channel != null) {
                channel.disconnect();
                session.disconnect();
            }
            return false;
        } catch (SftpException e) {
            log.error(e.getMessage());
        }
        return false;
    }


    /**
     * 用完记得关闭，否则连接一直存在，程序不会退出
     */
    public void close() {
        session.disconnect();
    }


}
