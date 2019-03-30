package com.minkey.test;

import com.minkey.dto.BaseConfigData;
import com.minkey.entity.ResultInfo;
import com.minkey.executer.SSHExecuter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

/**
 * @Author: mijj
 * @Date: 2019-03-30 11:00
 */
@Slf4j
public class GetNodeNum {

    /**
     * 获取uts设备节点数
     */
    @Test
    public void getNode (){
        BaseConfigData baseConfigData = new BaseConfigData();
        baseConfigData.setIp("119.130.207.239");
        baseConfigData.setPort(22);
        baseConfigData.setName("root");
        baseConfigData.setPwd("TOPwalk@123");

        SSHExecuter sshExecuter = new SSHExecuter(baseConfigData);
        ResultInfo resultInfo = sshExecuter.sendCmd("df -i ");

        String msg = resultInfo.getOutRes();
        String[] lines = msg.split("\n");


        boolean isBig = false;
        String outMsg = "";
        for (String line : lines) {
            String[] oneline = line.split(" ");
            String nodeValue =null;
            String nodeName = oneline[oneline.length - 1];
            if(StringUtils.equals("/",nodeName) || StringUtils.equals("/topdata",nodeName)){
                nodeValue = oneline[oneline.length - 2];
                outMsg = msg + (nodeName+"分区:"+nodeValue)+" ";
                if(isBig70(nodeValue)){
                    isBig = true;
                }
            }
        }

        log.info(outMsg);

    }

    private boolean isBig70(String nodeValue){
        try {
            Integer value = Integer.valueOf(nodeValue.split("%")[0]);
            if(value >= 70){
                return true;
            }else{
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
