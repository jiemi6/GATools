package com.minkey.controller;

import com.minkey.cache.DeviceCache;
import com.minkey.contants.MyLevel;
import com.minkey.db.CheckHandler;
import com.minkey.db.CheckItemHandler;
import com.minkey.db.TaskHandler;
import com.minkey.db.dao.*;
import com.minkey.dto.JSONMessage;
import com.minkey.handler.ExamineHandler;
import com.minkey.handler.TaskExamineHandler;
import com.minkey.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * 体检
 */
@Slf4j
@RestController
@RequestMapping("/examine")
public class ExamineController {
    @Autowired
    HttpSession session;

    @Autowired
    CheckHandler checkHandler;

    @Autowired
    ExamineHandler examineHandler;

    @Autowired
    TaskExamineHandler taskExamineHandler;

    @Autowired
    CheckItemHandler checkItemHandler;
    @Autowired
    DeviceCache deviceCache;
    @Autowired
    TaskHandler taskHandler;

    /**
     * 一键体检
     * @return
     */
    @RequestMapping("/allInOne")
    public String allInOne() {
        log.info("start: 执行一键体检");

        User user = (User) session.getAttribute("user");

        Check check = new Check();
        check.setCheckName(String.format("[%s]发起一键体检",user.getuName()));
        check.setCheckType(Check.CHECKTYPE_ALLINONE);
        check.setUid(user.getUid());

        try{
            //存入数据库，获取id
            long checkId = checkHandler.insert(check);
            //开始检查
            examineHandler.doAllInOne(checkId);
            return JSONMessage.createSuccess().addData("checkId",checkId).toString();
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.getMessage()).toString();
        }finally {
            log.info("end:  执行系统自检");
        }
    }

    /**
     * 链路体检
     * @return
     */
    @RequestMapping("/link")
    public String link(Long linkId) {
        log.info("start: 执行链路体检 linkId={} ",linkId);
        if(linkId == null){
            return JSONMessage.createFalied("linkId不能为空").toString();
        }

        User user = (User) session.getAttribute("user");
        try{

            Link link = deviceCache.getLink8Id(linkId);
            if(link == null){
                return JSONMessage.createFalied("体检的链路不存在").toString();
            }

            Check check = new Check();
            check.setCheckName(String.format("[%s]发起链路体检，链路名称[%s]",user.getuName(),link.getLinkName()));
            check.setCheckType(Check.CHECKTYPE_LINK);
            check.setUid(user.getUid());
            //存入数据库，获取id
            long checkId = checkHandler.insert(check);
            //开始检查
            examineHandler.doLinkAynsc(checkId,link);

            return JSONMessage.createSuccess().addData("checkId",checkId).toString();
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.getMessage()).toString();
        }finally {
            log.info("end:  执行链路体检");
        }
    }

    /**
     * 设备体检
     * @return
     */
    @RequestMapping("/device")
    public String device(Long deviceId) {
        log.info("start: 执行设备体检 deviceId={} ",deviceId);
        if(deviceId == null){
            return JSONMessage.createFalied("deviceId不能为空").toString();
        }

        User user = (User) session.getAttribute("user");

        try{
            Device device = deviceCache.getDevice(deviceId);
            if(device == null){
                return JSONMessage.createFalied("体检的设备不存在").toString();
            }

            Check check = new Check();
            check.setCheckName(String.format("[%s]发起设备体检，设备名称[%s]",user.getuName(),device.getDeviceName()));
            check.setCheckType(Check.CHECKTYPE_DEVICE);
            check.setUid(user.getUid());
            //存入数据库，获取id
            long checkId = checkHandler.insert(check);
            //开始检查
            examineHandler.doDeviceAsync(checkId,device);

            return JSONMessage.createSuccess().addData("checkId",checkId).toString();
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.getMessage()).toString();
        }finally {
            log.info("end:  执行设备体检");
        }
    }

    /**
     * 任务体检
     * @return
     */
    @RequestMapping("/task")
    public String task(Long taskId) {
        log.info("start: 执行任务体检 taskId={} ",taskId);
        if(taskId == null){
            return JSONMessage.createFalied("taskId不能为空").toString();
        }

        User user = (User) session.getAttribute("user");

        try{
            Task task = taskHandler.query(taskId);
            if(task == null){
                return JSONMessage.createFalied("体检的任务不存在").toString();
            }

            Check check = new Check();
            check.setCheckName(String.format("[%s]发起任务体检，任务名称[%s]",user.getuName(),task.getTaskName()));
            check.setCheckType(Check.CHECKTYPE_TASK);
            check.setUid(user.getUid());
            //存入数据库，获取id
            long checkId = checkHandler.insert(check);
            //开始检查
            taskExamineHandler.doTaskAsync(checkId,task);

            return JSONMessage.createSuccess().addData("checkId",checkId).toString();
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.getMessage()).toString();
        }finally {
            log.info("end:  执行任务体检");
        }
    }

    /**
     * 体检信息获取，页面不断扫描此接口获取数据
     * @return
     */
    @RequestMapping("/checkResult")
    public String checkResult(Long checkId,Integer index) {
        log.info("start: 获取体检信息 checkId={}，index={}",checkId,index);
        if(checkId == null || checkId <= 0){
            return JSONMessage.createFalied("参数错误").toString();
        }

        if(index == null || index <= 0){
            index = 0;
        }
        try{
            List<CheckItem> checkItems = checkItemHandler.query(checkId,index);

            return JSONMessage.createSuccess().addData("checkItems",checkItems).toString();
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.getMessage()).toString();
        }finally {
            log.info("end:  获取体检信息");
        }
    }

    /**
     * 下载体检结果文件报告
     * @return
     */
    @RequestMapping("/download")
    public String download(Long checkId,HttpServletResponse response) {
        log.info("start: 下载体检结果文件报告 checkId={}",checkId);
        if(checkId == null || checkId <= 0){
            return JSONMessage.createFalied("参数错误").toString();
        }

        try{

            Check  check = checkHandler.query(checkId);
            if(check == null){
                log.error("获取检查项为空checkId="+checkId);
                return JSONMessage.createFalied("检查项不存在").toString();
            }

            String fileName = check.getCheckName()+".xls";
            //excel 里面的sheet名称
            String sheetName = DateUtil.dateFormatStr(check.getCreateTime(),DateUtil.format_all);

            List<CheckItem> checkItems = checkItemHandler.query8CheckId(checkId);
            //创建HSSFWorkbook
            HSSFWorkbook wb = getHSSFWorkbook(sheetName, checkItems);

            response.setCharacterEncoding("utf-8");
            response.setContentType("multipart/form-data");
            //中文转码
            fileName = new String(fileName.getBytes(),"ISO8859-1");
            response.setHeader("Content-Disposition", "attachment;fileName="+fileName);

            try {
                response.getOutputStream().write(wb.getBytes());
            } catch (IOException e) {
                log.error(String.format("生成体检报告[%s]异常",check.getCheckName())+e);
            }finally {
                return null;
            }
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return JSONMessage.createFalied(e.getMessage()).toString();
        }finally {
            log.info("end:  执行体检信息");
        }
    }

    /**
     * 导出Excel
     * @return
     */
    public HSSFWorkbook getHSSFWorkbook(String sheetName, List<CheckItem> checkItems){

        // 第一步，创建一个HSSFWorkbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();

        // 第二步，在workbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet(sheetName);

        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制
        HSSFRow row = sheet.createRow(0);

        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式

        //声明列对象
        HSSFCell cell = null;

        //创建标题1
        cell = row.createCell(0);
        cell.setCellValue("序号");
        cell.setCellStyle(style);
        //创建标题2
        cell = row.createCell(1);
        cell.setCellValue("级别");
        cell.setCellStyle(style);
        //创建标题3
        cell = row.createCell(2);
        cell.setCellValue("项目");
        cell.setCellStyle(style);
        //创建标题4
        cell = row.createCell(3);
        cell.setCellValue("错误类型");
        cell.setCellStyle(style);
        //创建标题5
        cell = row.createCell(4);
        cell.setCellValue("报警内容");
        cell.setCellStyle(style);


        if(!CollectionUtils.isEmpty(checkItems)){
            int rowNum = 1;
            //创建内容
            for(CheckItem checkItem : checkItems){
                //将内容按顺序赋给对应的列对象
                row.createCell(0).setCellValue(rowNum);
                row.createCell(1).setCellValue(MyLevel.getString8level(checkItem.getResultLevel()));
                row.createCell(2).setCellValue(checkItem.getItemType());
                row.createCell(3).setCellValue(checkItem.getErrorType());
                row.createCell(4).setCellValue(checkItem.getResultMsg());

                rowNum++;
            }
        }
        return wb;
    }

}