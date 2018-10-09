package cn.hellosix.service.admin;

import cn.hellosix.dao.IRestMonitorDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by lzz on 2018/10/9.
 */
@Service
public class MonitorService {
    private static final String TABLE_PREFIX = "monitor";
    @Autowired
    private IRestMonitorDao restMonitorDao;

    public Map getGroupByUserAgent(String database, int startTime, int endTime){
        return restMonitorDao.getGroupByUserAgent(database + "." + TABLE_PREFIX, startTime, endTime);
    }

    public List getGroupByCountUid(String database, int startTime, int endTime, String date){
        String tableStr = database + "." + TABLE_PREFIX;
        return restMonitorDao.getGroupRestMonitor( tableStr, startTime, endTime, "uid", date);
    }

    public List getGroupByCountTotal(String database, int startTime, int endTime, String date){
        String tableStr = database + "." + TABLE_PREFIX;
        return restMonitorDao.getGroupRestMonitor(tableStr, startTime, endTime, "", date);
    }

    public Map getTotalCount(String database, int startTime, int endTime){
        String tableStr = database + "." + TABLE_PREFIX;
        return restMonitorDao.getTotal(tableStr, startTime, endTime);
    }

    public List getMonitorDetail(String database){
        return restMonitorDao.getRestMonitorDetail(database + TABLE_PREFIX);
    }

}
