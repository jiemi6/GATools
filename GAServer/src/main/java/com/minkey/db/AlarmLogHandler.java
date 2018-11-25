package com.minkey.db;

import com.minkey.contants.AlarmType;
import com.minkey.db.dao.AlarmLog;
import com.minkey.db.dao.DeviceLog;
import com.minkey.dto.Page;
import com.minkey.dto.SeachParam;
import com.minkey.util.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
public class AlarmLogHandler {
    private final String tableName = "t_alarmLog";
    @Autowired
    JdbcTemplate jdbcTemplate;



    public int queryCount(int bType) {
        Integer count = jdbcTemplate.queryForObject("select count(*) from "+tableName+" WHERE bType = ?" ,new Object[]{bType},Integer.class);
        if(count == null){
            return 0;
        }
        return count;
    }

    public Page<AlarmLog> query8page(int bType, Page<AlarmLog> page, SeachParam seachParam, Long bid) {
        StringBuffer whereStr = new StringBuffer(" where bType=" + bType);
        if(bid != null && bid >0){
            whereStr.append(" AND bid = "+bid);
        }
        if(seachParam.hasDataParam()){
            whereStr.append(" AND createTime " + seachParam.buildDateBetweenSql());
        }

        if(seachParam.getLevel() != null ){
            whereStr.append(" AND level = "+ seachParam.getLevel());
        }

        if(seachParam.getType() != null ){
            whereStr.append(" AND type = "+ seachParam.getType());
        }

        if(StringUtils.isNotEmpty(seachParam.getKeyword())){
            whereStr.append(" AND msg LIKE '%"+ seachParam.getKeyword()+"%'");
        }

        List<AlarmLog> devices = jdbcTemplate.query("select * from "+tableName + whereStr.toString()+" ORDER BY logId desc limit ?,?",
                new Object[]{page.startNum(),page.getPageSize()},new BeanPropertyRowMapper<>(AlarmLog.class));

        page.setData(devices);

        Integer total = jdbcTemplate.queryForObject("select count(*) from "+tableName + whereStr.toString(),Integer.class);
        page.setTotal(total);

        return page;
    }


    public void insert(AlarmLog alarmLog) {
        int num = jdbcTemplate.update("insert into "+tableName+" (bid,bType,type, level,msg,createTime) VALUES (?,?,?,?,?,?)"
                ,new Object[]{alarmLog.getBid(),alarmLog.getbType(),alarmLog.getType(),alarmLog.getLevel(),alarmLog.getMsg(),alarmLog.getCreateTime()});

    }


    public void insertAll(Set<AlarmLog> alarmLogs) {
        if(CollectionUtils.isEmpty(alarmLogs)){
            return;
        }

        int[][] num = jdbcTemplate.batchUpdate("insert into "+tableName+" (bid,bType,type,level,msg) VALUES (?,?,?,?,?)",
                alarmLogs,alarmLogs.size(), new ParameterizedPreparedStatementSetter<AlarmLog>() {
                    @Override
                    public void setValues(PreparedStatement ps, AlarmLog argument) throws SQLException {
                        ps.setLong(1,argument.getBid());
                        ps.setInt(2,argument.getbType());
                        ps.setInt(3, argument.getType());
                        ps.setInt(4,argument.getLevel());
                        ps.setString(5,argument.getMsg());
                    }
                });
    }

    public List queryAllBid8btype(int btype, Date startDate,Date endDate) {
        String startDateStr = DateUtil.dateFormatStr(startDate,DateUtil.format_all);
        String endDateStr = DateUtil.dateFormatStr(endDate,DateUtil.format_all);
        List allBid = jdbcTemplate.queryForList("select bid from "+tableName+" where btype="+btype+" AND createTime between ? AND ? GROUP BY bid ",
                new Object[]{startDateStr,endDateStr},Integer.class);
        return allBid;
    }


    public List queryRinking(int btype, Date startDate, Date endDate) {
        String startDateStr = DateUtil.dateFormatStr(startDate,DateUtil.format_all);
        String endDateStr = DateUtil.dateFormatStr(endDate,DateUtil.format_all);

        List allBid = jdbcTemplate.queryForList("select bid,COUNT(0) as alarmNum from t_alarmlog where bType=? AND createTime between ? AND ? GROUP BY bid ORDER BY COUNT(0) desc limit 0,5 ",
                new Object[]{btype,startDateStr,endDateStr});
        return allBid;


    }

    public void delete8Id(int btype, long bid) {
        int num = jdbcTemplate.update("DELETE FROM "+tableName+" where bid= ? AND bType=?",new Object[]{bid,btype});
    }

    public void deleteTask8LinkId(long linkId) {
        int num = jdbcTemplate.update("DELETE ta FROM t_alarmlog ta,t_task tt where ta.bid=tt.taskId AND ta.btype=? AND tt.linkId=? ",new Object[]{AlarmLog.BTYPE_TASK,linkId});
    }


    /**
     * 报警的设备数量
     * @param deviceIds
     * @param seachParam
     * @return
     */
    public int queryDeviceCount(Set<Long> deviceIds, SeachParam seachParam) {
        StringBuffer whereStr = new StringBuffer(" WHERE btype=? AND ( 1=2 ");
        deviceIds.forEach(deviceId -> {
            whereStr.append(" or bid=" + deviceId);
        });
        whereStr.append(")");

        if(seachParam.hasDataParam()){
            whereStr.append(" AND createTime " + seachParam.buildDateBetweenSql());
        }


        List<Map<String, Object>> count = jdbcTemplate.queryForList("select bid from "+tableName + whereStr +" GROUP BY bid ",new Object[]{AlarmLog.BTYPE_DEVICE});
        if(CollectionUtils.isEmpty(count)){
            return 0;
        }
        return count.size();
    }

    /**
     * 所有设备总共报警的次数
     * @param deviceIds
     * @param seachParam
     * @return
     */
    public int queryTotalCount(Set<Long> deviceIds, SeachParam seachParam) {
        StringBuffer whereStr = new StringBuffer(" WHERE btype=? AND ( 1=2 ");
        deviceIds.forEach(deviceId -> {
            whereStr.append(" or bid=" + deviceId);
        });
        whereStr.append(")");

        if(seachParam.hasDataParam()){
            whereStr.append(" AND createTime " + seachParam.buildDateBetweenSql());
        }


        Integer count = jdbcTemplate.queryForObject("select count(bid) from "+tableName + whereStr ,new Object[]{AlarmLog.BTYPE_DEVICE},Integer.class);
        if(count == null){
            return 0;
        }
        return count;
    }

    /**
     * 根据报警类型 查询所有设备总共报警的次数
     * @param deviceIds
     * @param seachParam
     * @param alarmType
     * @return
     */
    public int queryTotalCount(Set<Long> deviceIds, SeachParam seachParam, int alarmType) {
        StringBuffer whereStr = new StringBuffer(" WHERE btype=? AND ( 1=2 ");
        deviceIds.forEach(deviceId -> {
            whereStr.append(" or bid=" + deviceId);
        });
        whereStr.append(")");

        if(seachParam.hasDataParam()){
            whereStr.append(" AND createTime " + seachParam.buildDateBetweenSql());
        }

        whereStr.append(" AND type=" + alarmType);

        Integer count = jdbcTemplate.queryForObject("select count(bid) from "+tableName + whereStr ,new Object[]{AlarmLog.BTYPE_DEVICE},Integer.class);
        if(count == null){
            return 0;
        }
        return count;
    }

    public Page<Map<String, Object>> queryDevice8Page(Page page, SeachParam seachParam, Set<Long> deviceIds) {
        StringBuffer whereStr = new StringBuffer(" WHERE btype=? AND ( 1=2 ");
        deviceIds.forEach(deviceId -> {
            whereStr.append(" or bid=" + deviceId);
        });
        whereStr.append(")");

        if(seachParam.hasDataParam()){
            whereStr.append(" AND createTime " + seachParam.buildDateBetweenSql());
        }


        List<Map<String, Object>> pageLogs = jdbcTemplate.queryForList("select bid,count(bid) as countNum from "+tableName + whereStr +" GROUP BY bid limit ?,? ",
                new Object[]{AlarmLog.BTYPE_DEVICE,page.startNum(),page.getPageSize()});
        page.setData(pageLogs);

        Integer total = queryDeviceCount(deviceIds,seachParam);
        page.setTotal(total);

        return page;

    }
}
