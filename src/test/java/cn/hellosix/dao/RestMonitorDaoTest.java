package cn.hellosix.dao;

import cn.hellosix.Application;
import cn.hellosix.model.RestMonitorModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Map;

/**
 * Created by lzz on 2018/10/9.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class RestMonitorDaoTest {
    @Autowired
    private IRestMonitorDao restMonitorDao;

    @Test
    public void testAddRestMonitorModel(){
        RestMonitorModel restMonitorModel = new RestMonitorModel();
        restMonitorModel.setDay(7);
        restMonitorModel.setHour(5);
        restMonitorModel.setMinute(4);
        restMonitorModel.setUid("fdsafd4");
        restMonitorModel.setMsgType("INFO");
        restMonitorModel.setUserAgent("web");
        restMonitorDao.addRestMonitorModel("finebi.monitor", restMonitorModel);
    }

    @Test
    public void testGetTotal(){
        Map res = restMonitorDao.getTotal("test.monitor",-1,122212121);
        System.out.println( res );
    }

    @Test
    public void testGroupByUserAgentl(){
        Map res = restMonitorDao.getGroupByUserAgent("test.monitor",-1,122212121);
        System.out.println( res );
    }

    @Test
    public void testGetGroupRestMonitor(){
        List res = restMonitorDao.getGroupRestMonitor("test.monitor",-1,122212121, "uid", "hour");
        System.out.println( res );
    }

    @Test
    public void testGetCountRestMonitor(){
        List res = restMonitorDao.getGroupRestMonitor("test.monitor",-1,122212121, "", "minute");
        System.out.println( res );
    }


}
