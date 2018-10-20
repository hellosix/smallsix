package cn.hellosix.service.supper;

import cn.hellosix.dao.ISystemAdviseDao;
import cn.hellosix.model.SystemAdvise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lzz on 2018/10/20.
 */
@Service
public class SystemAdviseService {
    @Autowired
    private ISystemAdviseDao systemAdviseDao;

    public List<SystemAdvise> getSystemAdviseList(){
        return systemAdviseDao.getSystemAdviseList();
    }

    public void addSystemAdvise(SystemAdvise systemAdvise){
        systemAdviseDao.addSystemAdvise(systemAdvise);
    }

    public void updateSystemAdvise(SystemAdvise systemAdvise){
        if( null == systemAdvise.getId() ){
            systemAdviseDao.addSystemAdvise(systemAdvise);
            return;
        }
        SystemAdvise systemAdvise1 = systemAdviseDao.getSystemAdvise( systemAdvise.getId() );
        if( null == systemAdvise1 ){
            systemAdviseDao.addSystemAdvise(systemAdvise);
        }else{
            systemAdviseDao.updateSystemAdvise(systemAdvise);
        }
    }

    public SystemAdvise getSystemAdvise(int id){
        return systemAdviseDao.getSystemAdvise(id);
    }

    public void removeSystemAdvise(int id){
        systemAdviseDao.removeSystemAdvise(id);
    }
}
