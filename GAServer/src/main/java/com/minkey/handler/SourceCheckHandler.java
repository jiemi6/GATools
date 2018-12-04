package com.minkey.handler;

import com.minkey.db.dao.DeviceService;
import com.minkey.db.dao.Source;
import com.minkey.dto.FTPConfigData;
import com.minkey.exception.SystemException;
import com.minkey.util.DetectorUtil;
import com.minkey.util.DynamicDB;
import com.minkey.util.FTPUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SourceCheckHandler {

    @Autowired
    private FTPUtil ftpUtil;
    @Autowired
    private DynamicDB dynamicDB;

    public boolean testSource(Source source, DeviceService detectorService) throws SystemException{
        if(StringUtils.equals(source.getSourceType(),Source.sourceType_db)){
            return testSource_db(source,detectorService);
        }else if(StringUtils.equals(source.getSourceType(),Source.sourceType_ftp)){
            return testSource_ftp(source,detectorService);
        }else if(StringUtils.equals(source.getSourceType(),Source.sourceType_video)){
            return testSource_video(source,detectorService);
        }else{
            //未知数据格式
            String logstr = String.format("未知数据源类型[%]",source.getSourceType());
            log.error(logstr);
            return false;
        }
    }

    public boolean testSource_ftp(Source source, DeviceService detectorService)throws SystemException {
        boolean isConnect;
        if(source.isNetAreaIn()){
            isConnect = ftpUtil.testFTPConnect(source, FTPUtil.default_timeout);
        }else{
            if(detectorService == null){
                log.error(String.format("没有部署探针，无法探测外网FTP资源%s",source));
                return false;
            }
            //将source转换为ftpconfigdata
            FTPConfigData ftpConfigData = new FTPConfigData();
            ftpConfigData.setIp(source.getIp());
            ftpConfigData.setPort(source.getPort());
            ftpConfigData.setName(source.getName());
            ftpConfigData.setPwd(source.getPwd());
            ftpConfigData.setRootPath(source.getDbName());
            isConnect = DetectorUtil.testFTP(detectorService.getIp(),detectorService.getConfigData().getPort(), ftpConfigData);
        }

        return isConnect;
    }

    public boolean testSource_db(Source source, DeviceService detectorService)throws SystemException {
        boolean isConnect;
        if(source.isNetAreaIn()){
            isConnect = dynamicDB.testDB(source);
        }else{
            if(detectorService == null){
                log.error(String.format("没有部署探针，无法探测外网DB资源%s",source));
                return false;
            }
            isConnect = DetectorUtil.testDB(detectorService.getIp(),detectorService.getConfigData().getPort(),source);
        }
        return isConnect;

    }

    public boolean testSource_video(Source source, DeviceService detectorService)throws SystemException {
        log.error("暂时不支持video数据源探测");
        return false;
    }

}
