package cn.hellosix.dao;

import cn.hellosix.model.RestMonitorModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by lzz on 2018/10/8.
 */
@Repository
public interface IRestMonitorDao {
    boolean addRestMonitorModel(@Param("tableName") String table, @Param("q") RestMonitorModel monitor);
    List getRestMonitorDetail(@Param("tableName") String table);
    Map getTotal(@Param("tableName") String table, @Param("startTime") int startTime, @Param("endTime") int endTime);
    List getGroupRestMonitor(@Param("tableName") String table, @Param("startTime") int startTime, @Param("endTime") int endTime, @Param("type") String type, @Param("date") String date);
    Map getGroupByUserAgent(@Param("tableName") String table, @Param("startTime") int startTime, @Param("endTime") int endTime);
}
