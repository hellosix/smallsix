package cn.hellosix.component;

import cn.hellosix.dao.IRestMonitorDao;
import cn.hellosix.model.RestMonitorModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by lzz on 2018/10/11.
 */
@Component
public class MonitorManager implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private IRestMonitorDao restMonitorDao;

    private static ExecutorService threadPool = Executors.newSingleThreadExecutor();
    private static LinkedBlockingDeque<RestMonitorModel> monitorQueue = new LinkedBlockingDeque<>(100000);

    public static boolean addQueue(RestMonitorModel restMonitorModel){
        return monitorQueue.offer(restMonitorModel);
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        Thread runnable = new Thread() {
            @Override
            public void run() {
                while (true){
                    try {
                        RestMonitorModel restMonitorModel = monitorQueue.take();
                        restMonitorDao.addRestMonitorModel(restMonitorModel.getTable(), restMonitorModel);
                    } catch (Exception ignore) {
                        ignore.printStackTrace();
                    }
                }
            }
        };
        runnable.setName("monitor-add-thread");
        threadPool.execute(runnable);
    }
}
