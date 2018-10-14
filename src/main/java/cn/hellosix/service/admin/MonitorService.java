package cn.hellosix.service.admin;

import cn.hellosix.dao.IRestMonitorDao;
import cn.hellosix.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
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

    public List getGroupByUserAgent(String database){
        int endTime = TimeUtil.timeStamp();
        int startTime = TimeUtil.getBeforeHourTime( 24 * 10 );
        return restMonitorDao.getGroupByUserAgent(database + "." + TABLE_PREFIX, startTime, endTime);
    }

    public List getGroupByCountUid(String database, int startTime, int endTime, String date){
        String tableStr = database + "." + TABLE_PREFIX;
        return restMonitorDao.getGroupRestMonitor( tableStr, startTime, endTime, "uid", date);
    }

    public Map<String, List> getGroupByCountTotal(String database){
        String tableStr = database + "." + TABLE_PREFIX;
        int endTime = TimeUtil.timeStamp();
        int startTime = TimeUtil.getBeforeHourTime( 24 * 10 );
        Map<String, List> res = new HashMap<>();
        List listTotal = restMonitorDao.getGroupRestMonitor(tableStr, startTime, endTime, "", "day");
        res.put("total", listTotal);
        List uidList = restMonitorDao.getGroupRestMonitor(tableStr, startTime, endTime, "uid", "day");
        res.put("uid", uidList);
        return res;
    }

    public Map getTotalCount(String database, int beforeDay){
        String tableStr = database + "." + TABLE_PREFIX;
        int endTime = TimeUtil.timeStamp();
        int startTime = TimeUtil.getBeforeHourTime( 24 * beforeDay );
        return restMonitorDao.getTotal(tableStr, startTime, endTime);
    }

    public List getMonitorDetail(String database){
        String tableStr = database + "." + TABLE_PREFIX;
        return restMonitorDao.getRestMonitorDetail(tableStr);
    }

}
